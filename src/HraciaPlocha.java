import fri.shapesge.Obdlznik;
/**
 * Táto trieda vytvára pozadie hracej plochy hry
 * a nastavuje jej základné vlastnosti.
 * 
 * @author Matúš Pytel 
 * @version 5.12.2024
 */
public class HraciaPlocha {
    private Obdlznik hraciaPlocha;
    /**
     * Nastavuje vlastnosti hracej plochy.
     * 
     * @param šírka hracej plochy
     * @param výška hracej plochy
     */
    public HraciaPlocha(int sirka, int vyska, String farba) {
        this.hraciaPlocha = new Obdlznik();
        this.hraciaPlocha.zmenStrany(sirka, vyska);
        this.hraciaPlocha.posunZvisle(-50);
        this.hraciaPlocha.posunVodorovne(-60);
        this.hraciaPlocha.zmenFarbu(farba);
        this.hraciaPlocha.zobraz();
    }
}
