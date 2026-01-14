import  fri.shapesge.Obdlznik;
/**
 * Vytvorenie segmentu displeja pomocou triedy obdĺžnik.
 * Nápad prevzatý z cvičení doc. Ing. Ján Janech, PhD.
 * 
 * @author  Matúš Pytel
 * @version 1.0  (18 Októbra 2024)
 */
public class Segment {
    private Obdlznik segment;
    /**
     * Vytvorí sa obdĺžnik, ktorý nazveme segment,
     * keď mu priradíme súradnice x, y podľa jeho
     * pozície v cifre.
     * Nastaví sa veľkosť segmentu a jeho farba.
     * 
     * @param x-ová súradnica segmentu
     * @param y-ová súradnica segmentu
     */
    public Segment(int x, int y, boolean jeZvislo) {
        this.segment = new Obdlznik(x, y);
        this.segment.zmenFarbu("#e02504");
        if (jeZvislo) {
            this.segment.zmenStrany(5, 15);
        } else {
            this.segment.zmenStrany(15, 5);
        }

    }

    /**
     * Segment sa zobrazí na hracej ploche.
     */
    public void zapni() {
        this.segment.zobraz();
    }

    /**
     * Segment nie je vidno na hracej ploche. 
     */
    public void vypni() {
        this.segment.skry();
    }
}