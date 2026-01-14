
/**
 * Trieda predstavuje štyri rôzne typy otočenia tetramina.
 * 
 * @author Matúš Pytel
 * @version 5.12.2024
 */
public enum OtocenieTvaru {
    HORE(0),
    VPRAVO(1),
    DOLE(2),
    VLAVO(3);
    
    private int otocenie;
    /**
     * Nastaví hodnotu otočenia.
     * 
     * @param hodnota otočenia
     */
    OtocenieTvaru(int hodnota) {
        this.otocenie = hodnota;
    }
    
    /**
     * Vráti hodnotu otočenia.
     */
    public int getOtocenie() {
        return this.otocenie;
    }
    
}
