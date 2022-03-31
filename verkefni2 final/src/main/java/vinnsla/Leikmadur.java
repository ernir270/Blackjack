package vinnsla;

import javafx.scene.control.Label;

public class Leikmadur implements LeikmadurInterface{

    private int samtals;
    private int fjoldispila;
    private int samtalsPeningur;
    public boolean vannLeikmadur = false;

    /**
     * Gefur dealer eða leikmann spil.
     * @param s spil sem er dregið úr stokk
     */

    public void gefaSpil(SpilV s) {
        samtals += s.getVirdi();
        fjoldispila++;
    }

    /**
     * Frumstillir allar breytur þannig nýr leikur geti hafist
     */
    public void nyrLeikur(){
        samtals = 0;
        fjoldispila = 0;
    }

    /**
     * Nær í samtölu dealers eða leikmanns.
     * @return samtölu
     */
    public int getSamtala(){
        return this.samtals;
    }

    /**
     * setur samtölu notanda sem samtaala
     * @param samtaala samtala
     */
    public void setSamtala(int samtaala){
        samtals = samtaala;
    }


    /**
     * Nær í fjölda spila á hendi dealer eða leikmanni
     * @return skilar fjölda spila
     */
    public int getFjoldispila(){
        return this.fjoldispila;
    }

    /**
     * setur fjöldi spila sem s
     * @param s fjöldi spila
     */
    public void setFjoldispila(int s){
        fjoldispila = s;
    }

    /**
     *
     * @param d dealer eða leikmaður
     * @param label texti sem birtir hvort dealer eða leikmaður vann
     * @param innist Label fyrir innistæðu notanda
     * @param peningur innistæða notanda
     * @param nuvernadiVedmal veðmál notanda
     * @return skilar engu þar sem við þurfum ekki neitt feedback frá fallinu
     */
    public Leikmadur hvorVann(Leikmadur d, Label label, Label innist, int peningur, int nuvernadiVedmal) {

        if (this.getSamtala() == 21) {
            label.setText("Leikmaður vinnur!");
            samtalsPeningur = peningur + nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = true;
        }
        else if (this.getSamtala() == 22) {
            label.setText("Dealer vinnur!");
            samtalsPeningur = peningur - nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = false;
        }
        else if (d.getSamtala() > 21) {
            label.setText("Leikmaður vinnur!");
            samtalsPeningur = peningur + nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = true;
        }
        else if (this.getSamtala() > 21) {
            label.setText("Dealer vinnur!");
            samtalsPeningur = peningur - nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = false;
        }
        else if (this.getSamtala() > d.getSamtala()) {
            label.setText("Leikmaður vinnur!");
            samtalsPeningur = peningur + nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = true;
        }
        else if (this.getSamtala() < d.getSamtala()) {
            label.setText("Dealer vinnur!");
            samtalsPeningur = peningur - nuvernadiVedmal;
            innist.setText("Innistæða: " + (samtalsPeningur) + "kr");
            vannLeikmadur = false;
        }
        else if (this.getSamtala() == d.getSamtala()) {
            label.setText("Jafntefli!");
            innist.setText("Innistæða: " + peningur + "kr");
            vannLeikmadur = false;
        }
        return null;
    }

}
