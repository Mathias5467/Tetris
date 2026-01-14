import fri.shapesge.Obdlznik;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
/**
 * Definuje miesto, na ktorom sa zobrazí následujúci tvar.
 * 
 * @author Matúš Pytel
 * @version 5.12.2024
 */
public class NasledujuciTvar {
    private Obdlznik pozadie;
    private Tvar dalsiTvar;
    private String[] pismenaTvarov;
    private String dalsiePismenoTvaru;
    private Random randomCislo;
    /**
     * Vytvorí miesto, na ktorom sa zobrazí následujúci tvar
     * a načíta písmená tetramín.
     * 
     * @param pole písmená reprezentujúce typy tetramín
     */
    public NasledujuciTvar(String[] pismenaTvarov) {
        this.pozadie = new Obdlznik(460, 240);
        this.pozadie.zmenFarbu("#333333");
        this.pozadie.zmenStrany(160, 140);
        this.pozadie.zobraz();
        this.dalsiTvar = null;
        this.pismenaTvarov = pismenaTvarov;
        this.randomCislo = new Random();
    }
    
    /**
     * Zobrazí následujúci tvar na základe špecifikovaných vlastností.
     */
    public void zobraz() throws IOException {
        int index = this.randomCislo.nextInt(this.pismenaTvarov.length);
        this.dalsiePismenoTvaru = this.pismenaTvarov[index];
        File subor = new File("res/coords/suradniceNasledujuciTvar.txt");
        Scanner citaj = new Scanner(subor);
        int riadok = 0;
        while (citaj.hasNextLine()) {
            if (riadok == index) {
                this.dalsiTvar = new Tvar(citaj.nextInt(), citaj.nextInt(), this.dalsiePismenoTvaru, 30);
                break;
            }
            riadok++;
            citaj.nextLine();
        }
        citaj.close();
    }
    
    /**
     * Skryje následujúci tvar.
     */
    public void skry() {
        for (Stvorcek stvorec : this.dalsiTvar.getStvorce()) {
            stvorec.skry();
        } 
    }
    
    /**
     * Skryje zobrazovaný následujúci tvar a znovu zobrazí.
     */
    public void zmen() throws IOException {
        this.skry();
        this.zobraz();
    }
    
    /**
     * Vyberie náhodné písmeno ďalšieho tetramina.
     */
    public String getDalsiePismenoTvaru() {
        return this.dalsiePismenoTvaru;
    }
}
