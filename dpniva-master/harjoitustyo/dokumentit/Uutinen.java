

package harjoitustyo.dokumentit;
import java.time.*;

/**
 * Uutinen- luokka, jonka kautta luodaan ohjelmalle annetut tekstimuotoiset
 * uutiset
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */

public class Uutinen extends Dokumentti {
    
    /**
    * Uutisen päivämäärä 
    */ 
    
    private LocalDate päivämäärä;
    
    /*
     * Parametrillinen rakentaja
     */ 
    
    public Uutinen(int tTunniste, LocalDate tPäivämäärä, String tTeksti)
    throws IllegalArgumentException {
        super(tTunniste, tTeksti);
        päivämäärä(tPäivämäärä);
    }
    
    /*
     * Aksessorit
     */ 
    
    public LocalDate päivämäärä() {
        return päivämäärä;
    }
    
    public void päivämäärä(LocalDate tPäivämäärä) {
        if (tPäivämäärä == null) {
            throw new IllegalArgumentException("Huono päivämäärä!");
        }
        else {
            päivämäärä = tPäivämäärä;
        }
    }
    
    /**
     * Muuttaa uutisen tiedot merkkijonoksi
     *
     * @return uutisesta tehty merkkijono
     */

    
    public String toString() {
        
        /*
         * toString-metodi, lisää Dokumentti- luokan antamaan merkkijonoon
         * lisättävän päivämäärän.
         */ 
         
        String uutisenTiedot = new String();
        String dokumenttiTeksti = super.toString();
        String lisättäväPäivämäärä = päivämäärä().getDayOfMonth() + "." +
        päivämäärä().getMonthValue() + "." + päivämäärä().getYear();
        
        boolean päivämääräLisätty = false;
        
        for (int i = 0; i < dokumenttiTeksti.length(); i++) {
            
            uutisenTiedot += dokumenttiTeksti.charAt(i);
            
            // Tämä if- lause etsii kohdan, jossa kenoviivat vaihtuvat
            // ensimmäisen kerran toiseksi merkiksi. Tähän kohtaan liitetään
            // lisättävä päivämäärä
            if (dokumenttiTeksti.charAt(i) == '/' &&
            dokumenttiTeksti.charAt(i+1) != '/' &&
            päivämääräLisätty == false) {
                uutisenTiedot += lisättäväPäivämäärä + "///";
                päivämääräLisätty = true;
            }
        }
        return uutisenTiedot;
    }
}