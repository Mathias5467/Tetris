import java.io.IOException;
import java.io.File;
import java.util.Scanner;
/**
 * Vytvára tvary v hre Tetris zo 4 štvorcov.
 * 
 * @author  Matúš Pytel
 * @version 5.12.2024
 */
public class Tvar {
    private String pismenoTvaru;
    private Stvorcek[] tvar;
    private OtocenieTvaru otocenie;
    private Stvorcek stvorec1;
    private Stvorcek stvorec2;
    private Stvorcek stvorec3;
    private Stvorcek stvorec4;
    /**
     * Vytvorí tetramino zo 4 štvorčekov na základe písmena tetramina.
     * 
     * @param x-ová súradnica prvého štvorčeka tvoriaceho tetramino
     * @param y-ová súradnica prvého štvorčeka tvoriaceho tetramino
     * @param písmeno ktoré reprezentuje tvar tetramina
     * @param velkosť strany štvorčeka tvoriaceho tetramino
     */
    public Tvar(int x, int y, String znak, int strana) throws IOException {
        this.pismenoTvaru = znak;
        this.otocenie = OtocenieTvaru.HORE;
        String farba = "red";
        File subor = new File("res/coords/iPociatok.txt");
        switch (znak) {
            case "i":
                subor = new File("res/coords/iPociatok.txt");
                farba = "#22d3ee";
                break;
            case "t":
                subor = new File("res/coords/tPociatok.txt");
                farba = "#a855f7";
                break;
            case "l":
                subor = new File("res/coords/lPociatok.txt");
                farba = "#f59e0b";
                break;
            case "j":
                subor = new File("res/coords/jPociatok.txt");
                farba = "#3b82f6";
                break;
            case "o":
                subor = new File("res/coords/oPociatok.txt");
                farba = "#facc15";
                break;
            case "s":
                subor = new File("res/coords/sPociatok.txt");
                farba = "#34d399";
                break;
            case "z":
                subor = new File("res/coords/zPociatok.txt");
                farba = "#f87171";
                break;
        }
        this.stvorec1 = new Stvorcek(x, y, strana, farba);
        this.stvorec2 = new Stvorcek(x, y, strana, farba);
        this.stvorec3 = new Stvorcek(x, y, strana, farba);
        this.stvorec4 = new Stvorcek(x, y, strana, farba);
        this.tvar = new Stvorcek[] {this.stvorec1, this.stvorec2, this.stvorec3, this.stvorec4};
        Scanner citaj = new Scanner(subor);
        int i = 0;
        while (citaj.hasNextLine()) {
            String[] line = citaj.nextLine().split(" ");
            this.tvar[i].posunVodorovne(Integer.parseInt(line[0]));
            this.tvar[i].posunZvisle(Integer.parseInt(line[1]));
            i++;
        }
        citaj.close();
        this.zobrazTvar();
    }
    
    /**
     * Zobrazí tetramino.
     */
    public void zobrazTvar() {
        for (Stvorcek stvorec : this.tvar) {
            stvorec.zobraz();
        }
    }
    
    /**
     * Vráti pole obsahujúce štvorčeky tvoriace tetramino.
     */
    public Stvorcek[] getStvorce() {
        return this.tvar;
    }
    
    /**
     * Vráti písmeno reprezentujúce tetramino.
     */
    public String getPismenoTvaru() {
        return this.pismenoTvaru;
    }
    
    /**
     * Vráti inštanciu enumu OtocenieTvaru.
     */
    public OtocenieTvaru getOtocenie() {
        return this.otocenie;
    }
    
    /**
     * Posunie tetramino smerom dole o dĺžku strany štvorčeka.
     */
    public void posunDole() {
        for (Stvorcek stvorec : this.tvar) {
            stvorec.posunZvisle(stvorec.getStrana());
        }
    }
    
    /**
     * Posunie tetramino smerom vpravo o dĺžku strany štvorčeka.
     */
    public void posunVpravo() {
        for (Stvorcek stvorec : this.tvar) {
            stvorec.posunVodorovne(stvorec.getStrana());
        }
    }
    
    /**
     * Posunie tetramino smerom vľavo o dĺžku strany štvorčeka.
     */
    public void posunVlavo() {
        for (Stvorcek stvorec : this.tvar) {
            stvorec.posunVodorovne(-stvorec.getStrana());
        } 
    }
    
    /**
     * Otočí tetramino na základe jeho aktuálneho otočenia.
     */
    public void otoc() throws IOException {
        File subor = new File("res/coords/" + this.pismenoTvaru + "Otocenie.txt");
        Scanner citaj = new Scanner(subor);
        int pocet = 0;
        int[] posunyVodorovne = new int[4];
        int[] posunyZvisle = new int[4];
        while (citaj.hasNextLine()) {
            if (pocet == this.otocenie.getOtocenie()) {
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
        int i = 0;
        for (Stvorcek stvorcek : this.getStvorce()) {
            stvorcek.posunVodorovne(posunyVodorovne[i]);
            stvorcek.posunZvisle(posunyZvisle[i]);
            i++;
        }
        this.otocenie = this.zmenOtocenieTvaru(this.otocenie);
    }
    
    /**
     * Zmení otočenie tvaru a priradí ho do atribútu.
     * 
     * @param definuje aktuálne otočenie tvaru
     */
    public OtocenieTvaru zmenOtocenieTvaru(OtocenieTvaru otocenie) {
        switch (otocenie) {
            case HORE:
                return OtocenieTvaru.VPRAVO;
            case VPRAVO:
                return OtocenieTvaru.DOLE;
            case DOLE:
                return OtocenieTvaru.VLAVO;
            case VLAVO:
                return OtocenieTvaru.HORE;
        }
        return OtocenieTvaru.HORE;
    }
   
}