package vinnsla;

import javafx.scene.control.Label;

public interface LeikmadurInterface {

    /**
     * Gefur dealer eða leikmann spil.
     * @param s spil sem er dregið úr stokk
     */
    void gefaSpil(SpilV s);

    /**
     * Frumstillir allar breytur þannig nýr leikur geti hafist
     */
    void nyrLeikur();

    /**
     * Nær í samtölu dealers eða leikmanns.
     * @return samtölu
     */
    int getSamtala();

    /**
     * Tékkar á því hvort dealer eða leikmaður vann
     *
     * @param d dealer eða leikmaður
     * @param label texti sem birtir hvort dealer eða leikmaður vann
     * @return skilar þeim sem vann
     */
    Leikmadur hvorVann(Leikmadur d, Label label, Label innist, int peningur, int nuvernadiVedmal);
}
