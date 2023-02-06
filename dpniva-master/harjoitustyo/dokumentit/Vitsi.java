

package harjoitustyo.dokumentit;

/**
 * Vitsi- luokka, jonka kautta luodaan ohjelmalle annetut tekstimuotoiset
 * vitsit
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2020
 * <p>
 * @author Niko Väkiparta (niko.vakiparta@tuni.fi)
 * Tampereen yliopisto
 * 8.5.2020
 */

public class Vitsi extends Dokumentti {
    
    /**
    * Vitsin laji
    */
    
    private String laji;
    
    /*
     * Parametrillinen rakentaja
     */
   
    public Vitsi(int tTunniste, String tLaji, String tTeksti) 
    throws IllegalArgumentException {
        super(tTunniste, tTeksti);
        laji(tLaji);
    }
    
    /*
     * Aksessorit
     */
    
    public String laji() {
        return laji;
    }
    
    public void laji(String tLaji) {
        if (tLaji == null || tLaji == "") {
            throw new IllegalArgumentException("Huono laji!");
        }
        else {
            laji = tLaji;
        }
    }
    
    
    /**
     * Muuttaa vitsin tiedot merkkijonoksi
     *
     * @return vitsistä tehty merkkijono
     */
    
    

    public String toString() {
        
        /*
         * toString-metodi vastaanottaa Dokumentti- luokalta merkkijonon, johon
         * sitten lisätään vielä vitsin lajitieto
         *
         */
    
        
        String vitsinTiedot = new String();
        String dokumenttiTeksti = super.toString();
        String lisättäväLaji = laji();
        boolean lajiLisätty = false;
        
        for (int i = 0; i < dokumenttiTeksti.length(); i++) {
            
            vitsinTiedot += dokumenttiTeksti.charAt(i);
            
            // Tämä if- lause etsii kohdan, jossa kenoviivat vaihtuvat
            // ensimmäisen kerran toiseksi merkiksi. Tähän kohtaan liitetään
            // lisättävä päivämäärä
            if (dokumenttiTeksti.charAt(i) == '/' &&
            dokumenttiTeksti.charAt(i+1) != '/' &&
            lajiLisätty == false) {
                vitsinTiedot += lisättäväLaji + "///";
                lajiLisätty = true;
            }
        }
        return vitsinTiedot;
    }
}
