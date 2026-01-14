import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * Trieda obsahuje metódy na vyhodnocovanie kolízií tetramin navzájom
 * a s hracou plochou. 
 * 
 * @author  Matúš Pytel
 * @version 13.11.2024
 */
public class SpadnuteStvorce {
    private Stvorcek[][] spadnuteStvorce;
    private static final int SIRKA_HRACEJ_PLOCHY = 450;
    private static final int VYSKA_HRACEJ_PLOCHY = 600;
    /**
     * Iniciuje pole, ktoré zaznamenáva výskyt tetramin na hracej ploche. 
     */
    public SpadnuteStvorce(int sirka, int vyska) {
        this.spadnuteStvorce = new Stvorcek[20][15];
    }
    
    /**
     * Vráti hodnotu na základe vyhodnotenia
     * či sa aktuálny tvar môže posunúť dole bez kolízie.
     * 
     * @param aktuálny tvar, ktorého pozícia sa vyhodnocuje
     */
    public boolean mozemPosunutDole(Tvar aktualnyTvar) {
        for (Stvorcek stvorcek : aktualnyTvar.getStvorce()) {
            int x = stvorcek.getX();
            int y = stvorcek.getY() + stvorcek.getStrana();
            if (y < 0) {
                return true;
            }
            if (y == this.VYSKA_HRACEJ_PLOCHY || this.spadnuteStvorce[y / stvorcek.getStrana()][x / stvorcek.getStrana()] != null) {
                for (Stvorcek stvorcekPridaj : aktualnyTvar.getStvorce()) {
                    if (stvorcekPridaj.getY() >= 0) {
                        this.spadnuteStvorce[stvorcekPridaj.getY() / stvorcekPridaj.getStrana()][stvorcekPridaj.getX() / stvorcekPridaj.getStrana()] = stvorcekPridaj;
                    }
                }
                return false;
            } 
        }
        return true;
    }
    
    /**
     * Vráti hodnotu na základe vyhodnotenia
     * či sa aktuálny tvar môže posunúť vpravo bez kolízie.
     * 
     * @param aktuálny tvar, ktorého pozícia sa vyhodnocuje
     */
    public boolean mozemPosunutVpravo(Tvar aktualnyTvar) {
        for (Stvorcek stvorcek : aktualnyTvar.getStvorce()) {
            int x = stvorcek.getX() + stvorcek.getStrana();
            int y = stvorcek.getY();
            if (y < 0 || x == this.SIRKA_HRACEJ_PLOCHY || this.spadnuteStvorce[y / stvorcek.getStrana()][x / stvorcek.getStrana()] != null) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Vráti hodnotu na základe vyhodnotenia
     * či sa aktuálny tvar môže posunúť vľavo bez kolízie.
     * 
     * @param aktuálny tvar, ktorého pozícia sa vyhodnocuje
     */
    public boolean mozemPosunutVlavo(Tvar aktualnyTvar) {
        for (Stvorcek stvorcek : aktualnyTvar.getStvorce()) {
            int x = stvorcek.getX();
            int y = stvorcek.getY();
            if (y < 0 || x == 0 || this.spadnuteStvorce[y / stvorcek.getStrana()][(x - stvorcek.getStrana()) / stvorcek.getStrana()] != null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Vráti hodnotu na základe vyhodnotenia
     * či sa aktuálny tvar môže otočiť bez kolízie.
     * 
     * @param aktuálny tvar, ktorého pozícia sa vyhodnocuje
     */
    public boolean mozemOtocit(Tvar aktualnyTvar) throws IOException {
        File subor = new File("res/coords/" + aktualnyTvar.getPismenoTvaru() + "Otocenie.txt");
        Scanner citaj = new Scanner(subor);
        int pocet = 0;
        int[] posunyVodorovne = new int[4];
        int[] posunyZvisle = new int[4];
        while (citaj.hasNextLine()) {
            OtocenieTvaru aktualneOtocenie = aktualnyTvar.getOtocenie();
            if (pocet == aktualneOtocenie.getOtocenie()) {
                for (int i = 0; i < 8; i++) {
                    if (i < 4) {
                        posunyVodorovne[i] = citaj.nextInt();
                    } else {
                        posunyZvisle[i - 4] = citaj.nextInt();
                    }
                }
                citaj.close();
                break;
            }
            citaj.nextLine();
            pocet++;
        }
        int posun = 0;
        for (Stvorcek stvorcek : aktualnyTvar.getStvorce()) {
            int posunuteY = stvorcek.getY() + posunyZvisle[posun];
            int posunuteX = stvorcek.getX() + posunyVodorovne[posun];
            if ( posunuteY < 0 || posunuteY > (this.VYSKA_HRACEJ_PLOCHY - stvorcek.getStrana()) || posunuteX > (this.SIRKA_HRACEJ_PLOCHY - stvorcek.getStrana()) || posunuteX < 0 || this.spadnuteStvorce[posunuteY / stvorcek.getStrana()][posunuteX / stvorcek.getStrana()] != null) {
                return false;
            }
            posun++;
        }
        return true;
    }
    
    /**
     * Vráti číslo definujúce počet riadkov,
     * ktoré sa odstánili pri vykonávaní tejto metódy.
     */
    public int kontrolaPlnehoRiadku(int stranaStvorceka) {
        int pocetBodovNaPripisanie = 0;
        for (int y = 0; y < this.spadnuteStvorce.length; y++) {
            int pocetStvorcekov = 0;
            for (int x = 0; x < this.spadnuteStvorce[0].length; x++) {
                if (this.spadnuteStvorce[y][x] != null) {
                    pocetStvorcekov++;
                }
            }
            if (pocetStvorcekov == (this.SIRKA_HRACEJ_PLOCHY / stranaStvorceka)) {
                pocetBodovNaPripisanie += (this.SIRKA_HRACEJ_PLOCHY / stranaStvorceka);
                for (int i = this.spadnuteStvorce.length - 1; i > -1; i--) {
                    for (int j = this.spadnuteStvorce[i].length - 1; j > -1; j--) {
                        if (i == y) {
                            this.spadnuteStvorce[i][j].skry();
                            this.spadnuteStvorce[i][j] = null;
                        } else if (i < y && this.spadnuteStvorce[i][j] != null) {
                            this.spadnuteStvorce[i][j].posunZvisle(this.spadnuteStvorce[i][j].getStrana());
                            this.spadnuteStvorce[i + 1][j] = this.spadnuteStvorce[i][j];
                            this.spadnuteStvorce[i][j] = null;
                        }
                    }
                }
            }
        }
        return pocetBodovNaPripisanie;
    }
    
    /**
     * Vráti hodnotu, ktorá vyjadruje či je hracia plocha
     * naplnená.
     */
    public boolean naplnenaPlocha() {
        for (int i = 0; i < this.spadnuteStvorce[0].length; i++) {
            if (this.spadnuteStvorce[0][i] != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Vymaže štvorčeky v riadku hry, ktorý bol vyhodnotený ako
     * súvislý sled štvorčekov z prava do ľava hracej plochy.
     */
    public void vymazSpadnuteStvorce() {
        for (int y = 0; y < this.spadnuteStvorce.length; y++) {
            for (int x = 0; x < this.spadnuteStvorce[y].length; x++) {
                if (this.spadnuteStvorce[y][x] != null) {
                    this.spadnuteStvorce[y][x].skry();
                    this.spadnuteStvorce[y][x] = null;
                }
            }
        }
    }
}
