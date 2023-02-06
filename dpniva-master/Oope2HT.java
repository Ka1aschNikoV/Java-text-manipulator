
import java.util.*;
import harjoitustyo.kayttoliittyma.*;

/** 
 * Ajotiedosto Oope2HT, käytetään ohjelman käynnistämiseen
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */
 

public class Oope2HT {
    
    /**
    * Käynnistää ohjelman 
    *
    * @param args komentolinja-argumentit
    */
    
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");
        
        // Luodaan uusi käyttöliittymäolio
        Kayttoliittyma käyttöliittymä = new Kayttoliittyma();
        
        // Kutsutaan käyttöliittymän metodia Käynnistys(args), joka 
        // tarkistaa, että komentolinja-argumentit ovat oikein
        String tekstikokoelmanTyyppi = käyttöliittymä.käynnistys(args);
        
        // Käynnistetään ohjelma menemällä pääsilmukkaan
        käyttöliittymä.Pääsilmukka(tekstikokoelmanTyyppi, args);
    }
}
