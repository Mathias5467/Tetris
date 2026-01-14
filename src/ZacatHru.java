
/**
 * Trieda na určenie začiatku hry.
 * 
 * @author Matúš Pytel
 * @version 2.12.2024
 */
public enum ZacatHru {
    ANO(0),
    NIE(1);
    
    private int zaciatokHry;
    /**
     * Iniciuje hodnotu začiatku hry.
     * 
     * @param hodnota získaná potvrdením JOptionPane okna
     */
    ZacatHru(int zaciatokHry) {
        this.zaciatokHry = zaciatokHry;
    }
    
    /**
     * Vráti hodnotu získanú potvrdením JOptionPane okna.
     */
    public int getHodnota() {
        return this.zaciatokHry;
    }
}
