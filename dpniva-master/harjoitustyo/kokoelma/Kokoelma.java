
package harjoitustyo.kokoelma;

import java.time.LocalDate;
import java.time.format.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import harjoitustyo.apulaiset.*;
import harjoitustyo.omalista.*;
import harjoitustyo.dokumentit.*;


/** 
 * Kokoelma- luokka käsittelee tallennettuja dokumentteja
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */


public class Kokoelma implements Kokoava<Dokumentti> {
    
    /**
    * Sulkusanalista
    */ 
    
    private LinkedList<String> sulkusanat;
    
    /**
    * Dokumenttilista
    */
    
    private OmaLista<Dokumentti> dokumentit;
    
    /**
    * Kokoelman rakentajametodi
    *
    * @param tekstinNimi lisättävän tekstin nimi
    * @param sulut sulkusanatiedoston nimi
    * @param tyyppi kokoelman tyyppi
    */
    
    public Kokoelma(String tekstinNimi, String sulut, String tyyppi) {
        dokumentit = new OmaLista<Dokumentti>();
        kokoelmanLataus(tekstinNimi, sulut, tyyppi);
        
    }
    
    /*
     * Aksessorit
     */
    
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
    
    public LinkedList<String> sulkusanat() {
        return sulkusanat;
    }
    
    public void sulkusanat(String annettuSulkusana) {
        sulkusanat.add(annettuSulkusana);
    }
    
    /**
    * lisää parametrina annetun dokumentin kokoelmaan, jos sitä ei siellä ollut
    *
    * @param uusi lisättävä dokumentti
    */
    
    public void lisää(Dokumentti uusi) 
    throws IllegalArgumentException {
        
        /*
         * lisää- metodi lisää uuden dokumentin dokumenttilistalle
         */
         
        // Jos dokumenttia ei ole listalla ja se ei ole tyhjä, se lisätään 
        // listalle
        
        
        
        if (uusi == null) {
            throw new IllegalArgumentException("Lisättävä on null!");
        }
        for (int i = 0; i < dokumentit().size(); i++) {
            if (uusi.equals(dokumentit().get(i))) {
                throw new IllegalArgumentException("Lisättävä on jo listalla!");
            }
        }
        dokumentit().lisää(uusi);
    }
    
    /**
    * Etsii yksitellen jokaisesta kokoelman dokumentista hakusanoja, 
    * ja tulostaa onnistuneen etsinnän jälkeen dokumentin tunnisteen
    *
    * @param hakusanat hakusanat, joita etsitään dokumenteista
    */
    
    public void sanojenHaku(LinkedList<String> hakusanat) {
        
        // Metodi, joka tulostaa onnistuneen haun jälkeen 
        // dokumentin tunnisteen
        
        for (int i = 0; i < dokumentit().size(); i++) {

            if (dokumentit().get(i).sanatTäsmäävät(hakusanat)) {
                System.out.println(dokumentit().get(i).tunniste());
            }
        }
    }
    
    /**
    * Siivoaa yksitellen kokoelman dokumentit sulkusanoista ja 
    * parametrina annetuista välimerkeistä
    *
    * @param välimerkit välimerkit, jotka poistetaan tekstistä
    */
    
    
    public void kokoelmanSiivous(String välimerkit) {
        
        // Metodi siivoaa yksitellen kokoelman kaikki tiedostot
        
        for (int i = 0; i < dokumentit.size(); i++) {
            dokumentit.get(i).siivoa(sulkusanat(), välimerkit);
        }
    }
    
    
    /**
    * Hakee ja poistaa kokoelmasta dokumentin
    *
    * @param poistettava poistettavan dokumentin tunniste
    */
    
    public void poista(Dokumentti poistettava) 
    throws IllegalArgumentException {
        
        // Metodi etsii ja poistaa dokumentin
        
        if (hae(poistettava.tunniste()) == null) {
            throw new IllegalArgumentException();
        }
        else {
            dokumentit().remove(poistettava);
        }
            
    }
    
    
    /**
    * Hakee dokumentin tunnisteen perusteella dokumenttia kokoelmasta
    *
    * @param tunniste haettavan dokumentin tunniste
    * @return null, jos dokumenttia ei löytynyt, dokumentin, jos dokumentti
    * löytyi kokoelmasta
    */
    
    public Dokumentti hae(int tunniste) {
        
        /*
         * hae-metodi hakee tunnisteen perusteella dokumentin
         */     
         
        // Tämä metodi etsii ensin kaikista dokumenteista sen dokumentin
        // jota sen tunnisteella etsittiin, ja sitten palauttaa sen. Jos 
        // tekstiä ei löytynyt, palautetaan tyhjä-arvo
        
        for(int i = 0; i < dokumentit().size(); i++) {
            if(dokumentit().get(i).tunniste() == tunniste) {
                return dokumentit().get(i);
            }
        }
        return null;          
    }
    
    /**
    * Kasaa sekä sulkusanatiedoston että kokoelmatiedoston sisällön
    * ohjelman tietorakenteisiin
    *
    * @param tiedostonimi kokoelmatiedoston nimi
    * @param sulkutiedosto sulkusanatiedoston nimi
    * @param kokoelmanTyyppi kokoelman tyyppi, joko "Uutinen" tai "Vitsi"
    */
    
    public void kokoelmanLataus(String tiedostonimi, 
    String sulkutiedosto, String kokoelmanTyyppi) {
        
        sulkusanat = new LinkedList<String>();
        Scanner sulkusanalukija = null;
        File sulkusanatiedosto = new File(sulkutiedosto);
        
        try {
            sulkusanalukija = new Scanner(sulkusanatiedosto);
        }
        catch (FileNotFoundException e) {
        }
        
        while (sulkusanalukija.hasNextLine()) {
            String lisättävä = sulkusanalukija.nextLine();
            sulkusanat.add(lisättävä);
        }
        
        
        File tiedosto = new File(tiedostonimi);
        Scanner tiedostolukija = null;
        try {
            tiedosto = new File(tiedostonimi);
            tiedostolukija = new Scanner(tiedosto);
        }
        catch (Exception e) {
        }
        
        while (tiedostolukija.hasNextLine()) {
            String lisättäväTeksti = tiedostolukija.nextLine();
            dokumenttienLuoja(lisättäväTeksti, kokoelmanTyyppi);
        }
        
    }
    
    /**
    * Määrittää ja lisää annetun tekstityyppisen dokumentin kokoelmaan
    *
    * @param teksti dokumentin teksti
    * @param tiedosto dokumentin tyyppi
    */
    
    
    public void dokumenttienLuoja(String teksti, String tiedosto) 
    throws IllegalArgumentException {
        
        String[] tekstinpalat = teksti.split("///");
        try {
            DateTimeFormatter päivämääränMuoto =
            DateTimeFormatter.ofPattern("d.M.yyyy");
            
            LocalDate muunnosPäivämääräksi =
            LocalDate.parse(tekstinpalat[1], päivämääränMuoto);
            
            Uutinen lisättäväUutinen = 
            new Uutinen(Integer.parseInt(tekstinpalat[0]), muunnosPäivämääräksi, tekstinpalat[2]);
            
            // Uutinen lisätään, jos sen tyyppi vastaa jo luodun kokoelman tyyppiä
            if (lisättäväUutinen.getClass().getSimpleName().equals(tiedosto)) {
                lisää(lisättäväUutinen);
            }
            else {
                throw new IllegalArgumentException();
            }
                
            
        }
        
        catch (DateTimeParseException e) {
            Vitsi lisättäväVitsi =
            new Vitsi(Integer.parseInt(tekstinpalat[0]), tekstinpalat[1], tekstinpalat[2]);
            
            // Vitsi lisätään, jos sen tyyppi vastaa jo luodun kokoelman tyyppiä
            if (lisättäväVitsi.getClass().getSimpleName().equals(tiedosto)) {
                lisää(lisättäväVitsi);
            }
            else {
                throw new IllegalArgumentException();
            }
        }    
    }    
}

    