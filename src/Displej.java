
/**
 * Miesto kde sa zobrazia segmenty.
 * Nápad prevzatý z cvičení doc. Ing. Ján Janech, PhD.
 * 
 * @author  Matúš Pytel
 * @version 1.0  (18 Októbra 2024)
 */
public class Displej {
    private Segment segmentA;
    private Segment segmentB;
    private Segment segmentC;
    private Segment segmentD;
    private Segment segmentE;
    private Segment segmentF;
    private Segment segmentG;
    private int xBody;
    private int yBody;
    
    /**
     * Nastaví sa kde sa má zobraziť daný displej na hracej ploche
     * a umiestni sa v rovine definovanej súradnicami x a y.
     * 
     * @param x-ová súradnica displeja
     * @param y-ová súradnica displeja
     * @param posun displeja po x-ovej osi
     */
    public Displej(int x, int y, int posunDisplejaX) {
        this.segmentA = new Segment(posunDisplejaX + x + 9, y + 6, false);
        this.segmentB = new Segment(posunDisplejaX + x + 25, y + 13, true);
        this.segmentC = new Segment(posunDisplejaX + x + 25, y + 35, true);
        this.segmentD = new Segment(posunDisplejaX + x + 9, y + 52, false);
        this.segmentE = new Segment(posunDisplejaX + x + 3, y + 35, true);
        this.segmentF = new Segment(posunDisplejaX + x + 3, y + 13, true);
        this.segmentG = new Segment(posunDisplejaX + x + 9, y + 30, false);
    }

    /**
     * Udáva, ktoré segmenty sa pri zobrazení cifry
     * zobrazia a ktoré nie.
     * 
     * @param cifra zobrazovaná na displeji
     */
    public void zmenCislicu(int cislica) {
        switch (cislica) {
            case 0:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.zapni();
                this.segmentF.zapni();
                this.segmentG.vypni();
                break;
            case 1:
                this.segmentA.vypni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.vypni();
                this.segmentE.vypni();
                this.segmentF.vypni();
                this.segmentG.vypni();
                break;
            case 2:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.vypni();
                this.segmentD.zapni();
                this.segmentE.zapni();
                this.segmentF.vypni();
                this.segmentG.zapni();
                break;
            case 3:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.vypni();
                this.segmentF.vypni();
                this.segmentG.zapni();
                break;
            case 4:
                this.segmentA.vypni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.vypni();
                this.segmentE.vypni();
                this.segmentF.zapni();
                this.segmentG.zapni();
                break;
            case 5:
                this.segmentA.zapni();
                this.segmentB.vypni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.vypni();
                this.segmentF.zapni();
                this.segmentG.zapni();
                break;
            case 6:
                this.segmentA.zapni();
                this.segmentB.vypni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.zapni();
                this.segmentF.zapni();
                this.segmentG.zapni();
                break;
            case 7:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.vypni();
                this.segmentE.vypni();
                this.segmentF.vypni();
                this.segmentG.vypni();
                break;
            case 8:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.zapni();
                this.segmentF.zapni();
                this.segmentG.zapni();
                break;
            case 9:
                this.segmentA.zapni();
                this.segmentB.zapni();
                this.segmentC.zapni();
                this.segmentD.zapni();
                this.segmentE.vypni();
                this.segmentF.zapni();
                this.segmentG.zapni();
                break;
        }
    }
}