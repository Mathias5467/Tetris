import java.io.IOException;

/**
 * Trieda zabezpečuje vytvorenie inštancie triedy hra
 * a spustenie aplikácie mimo vývojového prostredia.
 */
public class Main {
    private static Hra hra;
    
    private Main() {
    
    }
    
    /**
     * Vytvorenie inštancie hry.
     */
    public static void main(String[] args) throws IOException {
        Main.hra = new Hra();
    }
}
