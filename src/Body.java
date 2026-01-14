
/**
 * Miesto kde sa vypíšu body, ktoré hráč získa.
 * 
 * @author  Matúš Pytel
 * @version 1.0  (18 Októbra 2024)
 */

public class Body {
    private final int sirkaDispleja;
    private Displej displejPreTisic;
    private Displej displejPreSto;
    private Displej displejPreDesat;
    private Displej displejPreJedna;

    /**
     * Vytvor pre každú cifru čísla miesto,
     * kde sa bude vykreslovať.
     * 
     * @param x-ová súradnica displeja
     * @param y-ová súradnica displeja
     */
    public Body(int x, int y) {
        this.sirkaDispleja = 35;
        this.displejPreTisic = new Displej(x, y, 0 * this.sirkaDispleja);
        this.displejPreSto = new Displej(x, y, 1 * this.sirkaDispleja);
        this.displejPreDesat = new Displej(x, y, 2 * this.sirkaDispleja);
        this.displejPreJedna = new Displej(x, y, 3 * this.sirkaDispleja);
        this.zmenCislo(0);
    }

    /**
     * Zmení sa zobrazenie bodov na hracej ploche
     * podľa zadaného čísla.
     * 
     * @param číslo, ktoré sa zobrazí v závislosti od svojej dĺžky na viacerých displejoch
     */
    public void zmenCislo(int cislo) {
        this.displejPreTisic.zmenCislicu(cislo / 1000);
        cislo %= 1000;
        this.displejPreSto.zmenCislicu(cislo / 100);
        cislo %= 100;
        this.displejPreDesat.zmenCislicu(cislo / 10);
        cislo %= 10;
        this.displejPreJedna.zmenCislicu(cislo);
    }
}