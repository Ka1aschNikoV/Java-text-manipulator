

package harjoitustyo.omalista;



import java.util.*;
import java.io.*;
import harjoitustyo.apulaiset.*;

/**
 * OmaLista- luokka, johon tallennetaan ohjelmalle annetut dokumentit
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */

public class OmaLista <E> extends LinkedList<E> implements Ooperoiva<E> {
    
    
    
    @SuppressWarnings({"unchecked"})
    
    /**
    * Lisää uuden dokumentin kokoelmaan
    *
    * @param uusi lisättävä dokumentti
    * @throws IllegalArgumentException, jos lisättävä ei toteuta Comparable-rajapintaa.
    */
    
    public void lisää(E uusi)
    throws IllegalArgumentException {
        /*
         * lisää- metodi lisää dokumenttikorpukseen uuden dokumentin
         *
         * Tämä metodi tarkistaa parametrin käyttökelpoisuuden, jonka jälkeen
         * se lisää tämän parametrina tulleen dokumentin dokumenttilistan perään.
         * Tämä dokumentti liikkuu aina yhden pykälän listassa eteenpäin, kunnes se löytää
         * oikean paikkansa suuruusjärjesyksessä
         */ 
        
        if (!(uusi instanceof Comparable)) {
            throw new IllegalArgumentException("Huono lisättävä!");
        }
        else {
            add(uusi);
            
            // järjestys- muuttuja määrää, ovatko dokumentit järjestyksessä
            boolean järjestys;
            
            
            int koko = size();
            
            // Yhden dokumentin lista on automaattisesti järjestyksessä
            if (koko == 1) {
                järjestys = true;
            }
            
            else {
                järjestys = false;
            }
            int i = 1;
            while (järjestys == false) {

                boolean epäonnistui = false;
                int j = 1;
                
                while (j < koko && epäonnistui == false) {
                    
                    // Käydään läpi listaa dokumentti kerralaan ja varmistetaan,
                    // että lisätty dokumentti on suuruusjärjestyksessä muiden aiempien
                    // dokumenttien kanssa.
                    Comparable edellinen = (Comparable)get(koko-j);
                    Comparable seuraava = (Comparable)get(koko-j-1);
                    if (edellinen.compareTo(seuraava) < 0) {
                        
                        // Jos dokumentit menivät lisäyksestä epäjärjetykseen,
                        // vertailu epäonnistuu, ja dokumentti siirretään 
                        // listalla yhden pykälän eteenpäin.
                        epäonnistui = true;

                    }
                    j++;
                    
                }
                if (epäonnistui == false) {
                    
                    // Jos jono on järjestyksessä, nykyiset paikat
                    // säilytetään ja vertailu lopetetaan
                    järjestys = true;
                }
                else {
                    
                    // Jos jono ei ole järjestyksessä, jonon uusinta jäsentä
                    // siirretään pykälä eteenpäin poistamalla se ja lisäämällä
                    // se uudelleen listaan.
                    remove(koko-i);
                    i++;
                    add(koko-i, uusi);
                }                    
            }
                
        }
    }
}