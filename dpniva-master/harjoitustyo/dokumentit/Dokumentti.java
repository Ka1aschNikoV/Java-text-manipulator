
package harjoitustyo.dokumentit;

import java.io.*;
import java.util.*;
import java.lang.*;

import harjoitustyo.apulaiset.*;

/** 
 * Dokumentti- luokka, jonka kautta annetaan uutisille ja vitseille 
 * sen teksti ja tunniste
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */


public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {
    
    /**
    * Dokumentin tunniste
    */
    
    private int tunniste;
    
    
    /**
    * Dokumentin sisältämä teksti
    */
    
    private String teksti;
    
    /*
     * Parametrillinen rakentaja
     */
    
    public Dokumentti(int tTunniste, String tTeksti) {
        tunniste(tTunniste);
        teksti(tTeksti);
    }
    
    /*
     * Aksessorit
     *
     */
    
    
    public int tunniste() {
        return tunniste;
    }
    
    public void tunniste(int tTunniste) {
        if (tTunniste < 1) {
            throw new IllegalArgumentException("Huono tunniste!");
        }
        else {
            tunniste = tTunniste;
        }
    }
    
    public String teksti() {
        return teksti;
    }
    
    public void teksti(String tTeksti) {
        if (tTeksti == null || tTeksti == "") {
            throw new IllegalArgumentException("Huono teksti!");
        }
        else {
            teksti = tTeksti;
        }
    }
    
    /** 
    * Palauttaa dokumentin tunnisteen ja tekstin merkkijonona
    *
    * @return dokumentin tunniste ja teksti erotettuna merkkijonolla "///"
    */
    
    
    public String toString() {
        
        /*
         * toString- metodi, palauttaa tunnisteen, välimerkit ja tekstin
         */
        
        return tunniste() + "///" + teksti();
    }        
    

    /**
    * Vertailee, ovatko oliot samat
    *
    * @param obj Object-tyyppinen verrattava olio
    * @return true, jos sama tunniste, false, jos eri
    * tai jos parametria ei pystytty muuttamaan Dokumentti-typpiseksi
    */
    
    
    public boolean equals(Object obj) {
        
        /*
         * equals-metodi, dokumenttien vertailuun niiden tunnisteiden perusteella
         */
         
        try {
            Dokumentti dokumentti = (Dokumentti)obj;
            return tunniste == dokumentti.tunniste();
        }
        catch (Exception e) {
            return false;
        }
    }
    
    /**
    * Vertaa tunnisteiden kokoa 
    *
    * @param toinenDokumentti verrattava dokumentti
    * @return -1, jos parametrina annettu dokumentin tunniste suurempi,
    * 0, jos dokumentin tunnisteet samat
    * 1, jos dokumentin tunniste pienempi
    */
    
    
    public int compareTo(Dokumentti toinenDokumentti) {
        
        /*
         * compareTo-metodi, jolla vertaillaan dokumenttien tunnisteiden kokoja
         */
     
        if (tunniste < toinenDokumentti.tunniste()) {
            return -1;
        }
        else if (tunniste == toinenDokumentti.tunniste()) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    /**
    * Tarkistaa, ovatko kaikki parametrina annetut sanat tekstissä
    *
    * @param hakusanat etsittävät sanat
    * @return true, jos kaikki sanat löytyvät, false jos jokin sana
    * uupui tekstistä
    */
    
    
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat)
    throws IllegalArgumentException {
        
        /*
         * sanatTäsmäävät-metodi Tietoinen- rajapinnasta
         *
         */
        
        // Metodi tarkastaa hakusanalistan käyttökelpoisuuden ja tämän jälkeen
        // varmistaa, että jokainen hakusana löytyy dokumentin tekstiosiosta.
        // Palauttaa true tai false sen mukaan, oliko etsintä onnistunut vai ei
        
        if (hakusanat == null || hakusanat.isEmpty() == true) {
            throw new IllegalArgumentException("Huonot hakusanat!");
        }
        else {
            int n = 0; // Kohdetekstistä löytyneiden hakusanojen määrä n
            String[] sanat = teksti().split(" ");
            for (int i = 0; i < hakusanat.size(); i++) {
                for (int j = 0; j < sanat.length; j++) {
                    if (hakusanat.get(i).equals(sanat[j])) {
                        n++;
                        j = sanat.length;
                    }
                }
            }
            if (n == hakusanat.size()) {
                return true;  // true
            }
            else {
                return false;  //false
            }
        }   
    }
    
    /**
    * Poistaa parametrina annetut merkit tekstistä, muuttaa kirjaimet pieniksi
    * ja poistaa tekstistä sulkusanat
    *
    * @param sulkusanat sulkusanat, joita poistetaan tekstistä
    * @param välimerkit annetut välimerkit, jotka poistetaan tekstistä
    */
    
    
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit)
    throws IllegalArgumentException {
        
        // Metodi tarkistaa, että sille välitetyt parametrit ovat käyttökelpoisia ja
        // tämän jälkeen poistaa tekstistä ensin välimerkit, muuttaa kirjaimet pieniksi ja 
        // lopuksi kokoaa sulkusanakarsinnan läpäisseet sanat merkkijonoksi. Antaa käsitellyn
        // tekstin tekstisetterille.
        
        if (sulkusanat == null || sulkusanat.isEmpty() == true) {
            throw new IllegalArgumentException("Huonot sulkusanat!");
        }
        
        else if (välimerkit == null || välimerkit == "") {
            throw new IllegalArgumentException("Huonot välimerkit!");
        }
        
        else {
            
            /*
             * Suodatetaan ensimmäisellä silmukkaparilla tekstistä pois
             * String välimerkit- merkkijonon sisältö
             */
            String kokoTeksti = teksti();
            String välimerkitönTeksti = new String();
            for(int i = 0; i < kokoTeksti.length(); i++) {
                boolean lisätään = true;
                for(int j = 0; j < välimerkit.length(); j++) {
                    
                    // Jos tarkasteltava merkki on poistettava välimerkki, sitä ei lisätä
                    if (välimerkit.charAt(j) == kokoTeksti.charAt(i)) {
                        lisätään = false;
                    }
                }
                if (lisätään) {
                    // Jos tarkasteltava merkki ei ollut poistettava, se saa jäädä
                    välimerkitönTeksti += kokoTeksti.charAt(i);
                }
            }
            
            /* 
             * Suodatetaan toisella silmukkaparilla tekstistä pois
             * sulkusanat- listalla annetut poistettavat sanat ja muutetaan
             * kirjaimet pieniksi.
             */
             
            välimerkitönTeksti = välimerkitönTeksti.toLowerCase();
            String[] suodatettavaTeksti = välimerkitönTeksti.split(" ");
            
            String suodatettuTeksti = new String();

            for(int i = 0; i < suodatettavaTeksti.length; i++) {
                boolean lisätään = true;
                for(int j = 0; j < sulkusanat.size(); j++) {
                    
                    // Jos suodatettujen sanojen taulukosta löytyy sulkusana, sitä
                    // ei lisätä lopulliseen tekstiin
                    if (suodatettavaTeksti[i].equals(sulkusanat.get(j))) {
                        lisätään = false;
                    }
                }
                // Jos sana ei ollut sulkusana, se saa jäädä lopulliseen tekstiin
                if (lisätään) {
                    suodatettuTeksti += suodatettavaTeksti[i];
                    suodatettuTeksti = suodatettuTeksti.trim() + " ";
                }
            }
            suodatettuTeksti = suodatettuTeksti.trim();
            teksti(suodatettuTeksti);
           
        }
    }
}
