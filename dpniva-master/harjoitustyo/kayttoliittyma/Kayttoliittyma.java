
 
package harjoitustyo.kayttoliittyma; 

import java.util.*;
import java.io.*;
import java.lang.*;
import harjoitustyo.kokoelma.*;
import harjoitustyo.dokumentit.*;


/**
 * Käyttöliittymä- luokka, jonka kautta ohjelman metodeja pyöritetään
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */


public class Kayttoliittyma {
    
    
    // Syötteiden lukemiseen käytettävä Scanner-olio
    public static final Scanner KOMENTOLUKIJA = new Scanner(System.in);
    
    // Komentovakiot
    public static final String KAIKU = "echo";
    public static final String TULOSTA = "print";
    public static final String LISÄÄ = "add";
    public static final String ETSI = "find";
    public static final String POISTA = "remove";
    public static final String ESIKÄSITTELE = "polish";
    public static final String PERUUTA = "reset";
    public static final String LOPETA = "quit";
    
    
    /**
    * Metodi varmistaa, että käynnistys on mahdollista
    * 
    * @param args komentolinja-argumentit
    * @return Uutinen, jos uutinen, Vitsi, jos vitsi, null,
    * jos komentolinja-argumentit olivat virheelliset
    */
    
    public String käynnistys(String[] args) {
        
        /* 
         * Käynnistys-metodi tarkistaa, että ohjelma on käynnistetty
         * oikeilla komentolinja-argumenteilla ja määrittää, onko
         * ohjelmaan ladattava kokoelma vitsi- vai uutiskokoelma
         */
        
        
        try {
            if (args[0] == "" || args[1] == "" || args.length != 2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            
            if ((args[0].contains("jokes") ||
            args[0].contains("news")) &&
            args[1].contains("stop")) {
            }
            else {
                throw new FileNotFoundException();
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong number of command-line arguments!");
            return null;
        }
        catch (FileNotFoundException f) {
            System.out.println("Missing file!");
            return null;
        }
        if (args[0].contains("jokes")) {
            return "Vitsi";
        }
        if (args[0].contains("news")) {
            return "Uutinen";
        }
        return null;
    }
    
    /**
    * Käsittelee käyttjän antaman komennon pääsilmukan ymmärtämään
    * muotoon
    * 
    * @param kokoKomento käyttäjän syöttämä komento merkkijonona
    * @return LinkedList, joka sisältää vaaditut komennon
    * osat LinkedList-tyyppisessä listassa
    *
    */
    
    
    public LinkedList<String> komennonKäsittely(String kokoKomento) {
        
        /*
         * komennonKäsittely käsittelee käyttäjän antaman komennon
         * ja palauttaa siitä tarvittavat tiedot takaisin pääsilmukalle
         */
        
        String[] komentoTaulukossa = kokoKomento.split(" ", 2);
        LinkedList<String> komentoListassa = new LinkedList<String>();
        for (int i = 0; i < komentoTaulukossa.length; i++) {
            komentoListassa.add(komentoTaulukossa[i]);
        }
        
        if ((komentoListassa.get(0).equals(LOPETA) ||
        komentoListassa.get(0).equals(PERUUTA) ||
        komentoListassa.get(0).equals(KAIKU) ||
        komentoListassa.get(0).equals(TULOSTA)) &&
        komentoListassa.size() == 1) {
            return komentoListassa;
        }
            
        else if (komentoListassa.get(0).equals(TULOSTA) ||
        komentoListassa.get(0).equals(POISTA)) {
            try {
                Integer.parseInt(komentoListassa.get(1));
                if (komentoListassa.size() == 2) {
                    return komentoListassa;
                }
            }
            catch (IndexOutOfBoundsException | NumberFormatException e) {
            }
        }
        
        else if (komentoListassa.get(0).equals(ESIKÄSITTELE)) {
          
            if (komentoListassa.size() == 2 &&
            komentoListassa.get(1).split(" ").length == 1) {
                return komentoListassa;
            }
        }
        else if (komentoListassa.get(0).equals(ETSI)) {
            
            if (komentoListassa.size() >= 2) {
                LinkedList<String> hakusanalista = new LinkedList<String>();
                
                hakusanalista.add(komentoListassa.get(0));
                String[] hakusanat = komentoListassa.get(1).split(" ");
                
                for (int j = 0; j < hakusanat.length; j++) {
                    hakusanalista.add(hakusanat[j]);
                }
                return hakusanalista;
            }
        }
        
        else if (komentoListassa.get(0).equals(LISÄÄ)) {
            try {
                
                if (komentoListassa.size() == 2 && 
                komentoListassa.get(1).split("///").length == 3)  {
                    return komentoListassa;
                }
            }
            catch (Exception e) {
            }
        }
        
        return null;
    }
    
    
    /**
    * Ottaa käyttäjältä vastaan komennon ja suorittaa sen mukaisen
    * toiminnon
    *
    * @param tekstienTyyppi tekstikokoelman tyyppi, joko "Uutinen", "Vitsi" tai null
    * @param args komentolinja-argumentit
    */
    
    public void Pääsilmukka(String tekstienTyyppi, String[] args) {
        
        /*
         * Pääsilmukka on vastuussa ohjelman pyörityksestä. Se ottaa
         * käyttäjältä vastaan komennon, käsittelee sen komennon käsittelyn
         * kautta luettavaksi ja tekee komennon mukaisen toiminnon
         */          
        
        
        boolean lippu = false;
        boolean kaiutus = false;
        Kokoelma korpus = null;
        
        // Jos käynnistys oli onnistunut eli teksteillä on tyyppi, 
        // ohjelma käynnistetään ja Kokoelma- olio luodaan
        if (tekstienTyyppi != null) {
            lippu = true;
            korpus = new Kokoelma(args[0], args[1], tekstienTyyppi);
            
        }

        while (lippu == true) {
            
            System.out.println("Please, enter a command:");
            
            // Luetaan käyttäjältä komento ja toimitetaan se komennon käsittelijälle
            String komentoMerkkijono = KOMENTOLUKIJA.nextLine();
            LinkedList<String> komento = komennonKäsittely(komentoMerkkijono);
            
            try {
                
                
                // Kaiutuksen tarkistus
                if (kaiutus == true) {
                    System.out.println(komentoMerkkijono);
                }
                
                // echo, Kaiutus
                if (komento.get(0).equals(KAIKU)) {
                    kaiutus = !kaiutus;
                    if (kaiutus == true) {
                        System.out.println(KAIKU);
                    }
                }
                
                // print, Tulostus
                else if (komento.get(0).equals(TULOSTA)) { 
                    if (komento.size() == 1) {
                        for (int i = 0; i < korpus.dokumentit().size(); i++) {
                            System.out.println(korpus.dokumentit().get(i).toString());                            
                        }
                    }
                    else {
                        Dokumentti tulostettava = korpus.hae(Integer.parseInt(komento.get(1)));
                        System.out.println(tulostettava.toString());
                    }
                }
                
                // add, Dokumentin lisääminen kokoelmaan
                else if (komento.get(0).equals(LISÄÄ)) { 
                    korpus.dokumenttienLuoja(komento.get(1), tekstienTyyppi);
                }
                
                // find, Kokoelmasta haku
                else if (komento.get(0).equals(ETSI)) { 
                    LinkedList<String> hakusanalista = new LinkedList<String>();
                    for (int i = 1; i < komento.size(); i++) {
                        hakusanalista.add(komento.get(i));
                    }
                    korpus.sanojenHaku(hakusanalista);
                }
                
                // remove, Dokumentin poistaminen kokoelmasta
                else if (komento.get(0).equals(POISTA)) { 
                    Dokumentti poistettava = korpus.hae(Integer.parseInt(komento.get(1)));
                    korpus.poista(poistettava);
                }
                
                 // polish, Kokoelman esikäsittely, merkkien ja jonojen karsiminen
                else if (komento.get(0).equals(ESIKÄSITTELE)) { 
                    korpus.kokoelmanSiivous(komento.get(1));
                }
                
                // reset, dokumentin uudelleenlataus
                else if (komento.get(0).equals(PERUUTA)) { 
                    korpus = new Kokoelma(args[0], args[1], tekstienTyyppi);
                }
                
                // quit, lopeta ohjelma
                else if (komento.get(0).equals(LOPETA)) {  
                    lippu = false;
                }
            }
            catch (Exception e) {
                System.out.println("Error!");
            }
        }
        System.out.println("Program terminated.");
    }
}