import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import fri.shapesge.Manazer;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Kompozit spájajúci všetky funkčné časti do jedného celku.
 * Zahŕňa gameloop.
 * 
 * @author  Matúš Pytel
 * @version 13.11.2024
 */
public class Hra {
    private static final int SIRKA_PLOCHY = 450;
    private static final int VYSKA_PLOCHY = 600;
    private static final int VELKOST_STVORCEKA = 30;
    private static final int START_X_TVAR = 180;
    private static final int START_Y_TVAR = -60;
    private static final String FARBA_HRACEJ_PLOCHY = "#333333";
    private HraciaPlocha hraciaPlocha;
    private Body skore;
    private Body maxSkore;
    private BlokTextu textSkore;
    private BlokTextu textMaxSkore;
    private NasledujuciTvar nasledujuciTvarPlocha;
    private Tvar aktualnyTvar;
    private SpadnuteStvorce spadnuteStvorce;
    private Manazer manazerHry;
    private int pocetBodov;
    private int maxPocetBodov;
    private int zaciatokHry;
    private String[] moznostiNovejHry;
    private String[] pismenaTvarov;
    private Random randomCislo;
    /**
     * Iniciuje hodnoty všetkých atribútov.
     */
    public Hra() throws IOException {
        this.hraciaPlocha = new HraciaPlocha(this.SIRKA_PLOCHY, this.VYSKA_PLOCHY, this.FARBA_HRACEJ_PLOCHY);
        this.skore = new Body(470, 40);
        this.maxSkore = new Body(470, 160);
        this.textSkore = new BlokTextu("Skóre", 505, 35);
        this.textMaxSkore = new BlokTextu("Max skóre", 470, 150);
        this.nastavTexty(this.textMaxSkore, 28);
        this.nastavTexty(this.textSkore, 28);
        this.aktualnyTvar = null;
        this.spadnuteStvorce = new SpadnuteStvorce(this.SIRKA_PLOCHY, this.VYSKA_PLOCHY);
        this.manazerHry = new Manazer();
        this.pocetBodov = 0;
        File maxSkoreSubor = new File("res/maxSkore.txt");
        Scanner citaj = new Scanner(maxSkoreSubor);
        this.maxPocetBodov = citaj.nextInt();
        citaj.close();
        this.pismenaTvarov = new String[] {"t", "l", "j", "o", "z", "s", "i"};
        this.nasledujuciTvarPlocha = new NasledujuciTvar(this.pismenaTvarov);
        this.moznostiNovejHry = new String[] {"Áno", "Nie"};
        this.randomCislo = new Random();
        this.zaciatokHry = JOptionPane.showOptionDialog(null, "Chceš začať novú hru?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, this.moznostiNovejHry, this.moznostiNovejHry[0]);
        if (this.zaciatokHry == ZacatHru.ANO.getHodnota()) {
            this.novaHra();
        } else {
            System.exit(0);
        }
    }

    /**
     * Metóda zisťuje stav hry a vyhodnocuje, čo sa má stať,
     * keď hra skončí.
     */
    public void tik() throws IOException {
        if (this.pocetBodov <= 9990) {
            if (!this.posunTvarDole()) {
                int pripocitajBody = this.spadnuteStvorce.kontrolaPlnehoRiadku(this.VELKOST_STVORCEKA);
                this.pocetBodov += pripocitajBody;
                if (this.maxPocetBodov < this.pocetBodov) {
                    this.maxPocetBodov = this.pocetBodov;
                }
                this.skore.zmenCislo(this.pocetBodov);
                this.maxSkore.zmenCislo(this.maxPocetBodov);
                if (this.spadnuteStvorce.naplnenaPlocha()) {
                    this.koniecHry(false);
                }
                this.aktualnyTvar = new Tvar(this.START_X_TVAR, this.START_Y_TVAR, this.nasledujuciTvarPlocha.getDalsiePismenoTvaru(), this.VELKOST_STVORCEKA);
                this.nasledujuciTvarPlocha.zmen();
            }
        } else {
            this.skore.zmenCislo(9999);
            this.maxSkore.zmenCislo(9999);
            this.koniecHry(true);
        }
    }
    
    /**
     * Vytvorí novú hru, vynuluje body a začne sa nová hra.
     */
    public void novaHra() throws IOException {
        this.spadnuteStvorce.vymazSpadnuteStvorce();
        this.pocetBodov = 0;
        this.skore.zmenCislo(this.pocetBodov);
        File subor = new File("res/maxSkore.txt");
        Scanner maxSkoreCislo = new Scanner(subor);
        this.maxSkore.zmenCislo(maxSkoreCislo.nextInt());
        maxSkoreCislo.close();
        this.aktualnyTvar = new Tvar(this.START_X_TVAR, this.START_Y_TVAR, this.pismenaTvarov[this.pismenaTvarov.length - 1], this.VELKOST_STVORCEKA);
        this.nasledujuciTvarPlocha.zobraz();
        this.manazerHry.spravujObjekt(this);
    }
    
    /**
     * Hra sa zastaví a ukáže sa informačné menu.
     */
    public void koniecHry(boolean vyhral) throws IOException {
        this.nasledujuciTvarPlocha.skry();
        FileWriter zapisMaxSkore = new FileWriter("res/maxSkore.txt", false);
        zapisMaxSkore.write(Integer.toString(this.maxPocetBodov));
        zapisMaxSkore.close();
        this.manazerHry.prestanSpravovatObjekt(this);
        int potvrdenie = 0;
        if (!vyhral) {
            potvrdenie = JOptionPane.showConfirmDialog(null, String.format("Hra skončila! Dosiahol si: %d bodov", this.pocetBodov), "", JOptionPane.DEFAULT_OPTION);
        } else {
            potvrdenie = JOptionPane.showConfirmDialog(null, String.format("Vyhral si!", this.pocetBodov), "", JOptionPane.DEFAULT_OPTION);
        }
        
        if (potvrdenie == 0 || potvrdenie == -1) {
            int novaHra = JOptionPane.showOptionDialog(null, "Chceš začať novú hru?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, this.moznostiNovejHry, this.moznostiNovejHry[0]);
            if (novaHra == ZacatHru.ANO.getHodnota()) {
                this.novaHra();
            } else if (novaHra == ZacatHru.NIE.getHodnota()) {
                System.exit(0);
            }
        }
    }
    
    /**
     * Metóda posúva aktuálne padajúci tvar dole
     * na základe vstupu pomocou klávesnice.
     */
    public boolean posunTvarDole() {
        if (this.spadnuteStvorce.mozemPosunutDole(this.aktualnyTvar)) {
            this.aktualnyTvar.posunDole();
            return true;
        }
        return false;
    }
    
    /**
     * Metóda posúva aktuálne padajúci tvar vpravo
     * na základe vstupu pomocou klávesnice.
     */
    public void posunTvarVpravo() {
        if (this.spadnuteStvorce.mozemPosunutVpravo(this.aktualnyTvar)) {
            this.aktualnyTvar.posunVpravo();
        }
    }
    
    /**
     * Metóda posúva aktuálne padajúci tvar vľavo
     * na základe vstupu pomocou klávesnice.
     */
    public void posunTvarVlavo() {
        if (this.spadnuteStvorce.mozemPosunutVlavo(this.aktualnyTvar)) {
            this.aktualnyTvar.posunVlavo();
        }
    }
    
    
    
    /**
     * Metóda zistí či možno tvar otočiť
     * a na základe toho buď otočí alebo nie.
     */
    public void otocTvar() throws IOException {
        if (this.spadnuteStvorce.mozemOtocit(this.aktualnyTvar)) {
            this.aktualnyTvar.otoc();
        }
    }
    
    /**
     * Nastaví vlastnosť zobrazovaného textu.
     * 
     * @param text na ktorý majú byť vlastnosti aplikované
     * @param veľkosť daného textu
     */
    public void nastavTexty(BlokTextu text, int velkost) {
        text.zmenFarbu("#e02504");
        text.zmenFont("Arial", StylFontu.BOLD, velkost);
        text.zobraz();
    }
    
    /**
     * Trieda zmení pre zadanú inštanciu triedy Body počet bodov
     * zobrazovaných na segmentových displejoch.
     * 
     * @param inštancia triedy Body
     */
    public void pripocitajBody(Body body) {
        body.zmenCislo(this.pocetBodov);
    }
}
