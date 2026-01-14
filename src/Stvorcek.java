import fri.shapesge.Stvorec;
/**
 * Trieda obsahujúca niektoré metódy triedy Stvorec
 * avšak s pridanými metódami: getX() a getY().
 * 
 * @author Matúš Pytel 
 * @version 5.12.
 */
public class Stvorcek {
    private int strana;
    private String farba;
    private Stvorec stvorec;
    private int x;
    private int y;
    /**
     * Vytvorí sa štvorec a inicializuje sa jeho pozícia
     * podľa zadaných parametrov.
     * 
     * @param x-ová súradnica štvorčeka
     * @param y-ová súradnica štvorčeka
     */
    public Stvorcek(int x, int y, int strana, String farba) {
        this.stvorec = new Stvorec(x , y);
        this.x = x;
        this.y = y;
        this.strana = strana;
        this.stvorec.zmenStranu(this.strana);
        this.farba = farba;
        this.stvorec.zmenFarbu(this.farba);
        this.zobraz();
    }
    
    /**
     * Spraví štvorček viditeľným.
     */
    public void zobraz() {
        this.stvorec.zobraz();
    }
    
    /**
     * Skryje štvorček.
     */
    public void skry() {
        this.stvorec.skry();
    }
    
    /**
     * Vráti dĺžku strany štvorčeka.
     */
    public int getStrana() {
        return this.strana;
    }
    
    /**
     * Vráti x-ovú súradnicu štvorčeka (ľavý horný roh).
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Vráti y-ovú súradnicu štvorčeka (ľavý horný roh).
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Posunie štvorček zvisle o zadanú dĺžku.
     * 
     * @param dĺžka posunu štvorčeka
     */
    public void posunZvisle(int posun) {
        this.stvorec.posunZvisle(posun);
        this.y += posun;
    }
    
    /**
     * Posunie štvorček vodorovne o zadanú dĺžku.
     * 
     * @param dĺžka posunu štvorčeka
     */
    public void posunVodorovne(int posun) {
        this.stvorec.posunVodorovne(posun);
        this.x += posun;
    }
}
