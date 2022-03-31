package vidmot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import vinnsla.Gildi;
import vinnsla.Leikmadur;
import vinnsla.SpilV;
import vinnsla.Stokkur;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller fyrir aðalviðmót.
 */

public class SpilController implements Initializable {


    @FXML
    public Label fxNafnDealer;
    @FXML
    public Label fxNafnLeikmadur;
    @FXML
    public Label fxHvorVinnur;
    @FXML
    public Button fxNyttSpilButton;
    @FXML
    public Button fxKomidNogButton;
    @FXML
    public Button fxNyrLeikur;
    @FXML
    public Button fxDoubleDown;
    @FXML
    public Button fxAs1;
    @FXML
    public Button fxAs11;
    @FXML
    public Button fx10;
    @FXML
    public Button fx25;
    @FXML
    public Button fx50;

    private int peningur = 500;
    @FXML
    public Label fxInnistaeda;
    @FXML
    public Label fxPeningurUndir;
    private Leikmadur leikmadur;
    private Leikmadur dealer;
    private Stokkur stokkur;
    private String leikmadurNafn;
    @FXML
    private HBox fxHboxDealer;
    private int nuvernadiVedmal;
    @FXML
    private HBox fxHboxLeikmadur;
    private Spil spil = new Spil();

    /**
     * Sækir nafn sem notandi slær inn og stillir breytuna leikmadurNafn = nafn.
     *
     * @param nafn nafn sem notandi slær inn
     */

    public void saekjaNafn (String nafn){
        fxNafnLeikmadur.setText(nafn + " " + "Samtals: " + leikmadur.getSamtala());
        leikmadurNafn = nafn;
    }

    /*
    Ef notandi tapar öllum peningum sínum á köllum við á fallið peningurBuinn() sem gerir alla takka
    óvirka svo notandi getur bara ýtt á takkann "hætta".
     */

    public void peningurBuinn(){
        fxHvorVinnur.setText("Allur peningur búinn");
        fxKomidNogButton.setDisable(true);
        fxNyrLeikur.setDisable(true);
        fxNyttSpilButton.setDisable(true);
        fxDoubleDown.setDisable(true);
        fxAs1.setDisable(true);
        fxAs11.setDisable(true);
        fx10.setDisable(true);
        fx25.setDisable(true);
        fx50.setDisable(true);
    }

    /*
    Notandi vill veðja 10kr á þennan leik. Upphæð veðmáls er birt á viðmóti.
     */
    public void vedmal10(){

        nuvernadiVedmal = 10;
        fxPeningurUndir.setText("Peningur undir: " + nuvernadiVedmal + "kr");
        fx10.setDisable(true);
        fx25.setDisable(true);
        fx50.setDisable(true);
    }

    /*
    Notandi vill veðja 25kr á þennan leik. Upphæð veðmáls er birt á viðmóti.
     */
    public void vedmal25(){

        nuvernadiVedmal = 25;
        fxPeningurUndir.setText("Peningur undir: " + nuvernadiVedmal + "kr");
        fx10.setDisable(true);
        fx25.setDisable(true);
        fx50.setDisable(true);
    }

    /*
    Notandi vill veðja 50kr á þennan leik. Upphæð veðmáls er birt á viðmóti.
     */
    public void vedmal50(){

        nuvernadiVedmal = 50;
        fxPeningurUndir.setText("Peningur undir: " + nuvernadiVedmal + "kr");
        fx10.setDisable(true);
        fx25.setDisable(true);
        fx50.setDisable(true);
    }

    /*
    Þegar notandi fær ás þá keyrum við as1eda11() sem gerir takkana til að stjórna hvaða gildi
    notandi vill að ás gildi virka.
     */
    public void as1eda11(){
        fxAs1.setDisable(false);
        fxAs11.setDisable(false);
    }

    /*
    Notandi vill að ás gildi 1.
     */
    public void as1(){
        fxNafnLeikmadur.setText(leikmadurNafn + " " + "Samtals: " + (leikmadur.getSamtala() - 10));
        leikmadur.setSamtala(leikmadur.getSamtala() - 10);
        fxAs1.setDisable(true);
        fxAs11.setDisable(true);

    }

    /*
    Keyrir fallið vannLeikamdur() í leikmadur klasanum.
     */
    public boolean vannLeikmadur(){
        return leikmadur.vannLeikmadur;
    }

    /*
    Notandi vill tvöfalda veðmálið sitt. Upphæð veðmáls er tvöfölduð og fjöldi spila notandi sett í 4
    því notandi getur bara fengið 1 spil í viðbót.
     */
    public void doubleDown(){

        nuvernadiVedmal = nuvernadiVedmal * 2;
        fxPeningurUndir.setText("Peningur undir: " + nuvernadiVedmal + "kr");
        fxDoubleDown.setDisable(true);
        leikmadur.setFjoldispila(4);

        if (peningur <= 0) {
            peningurBuinn();
        }
    }


    /**
     * Lætur notandi fá nýtt spil ef spilafjöldi er minni en 5 þegar hann ýtir á Nýtt spil takkann.
     */
    public void nyttSpilHandler() {


        fxAs1.setDisable(true);
        fxAs11.setDisable(true);

        if (leikmadur.getFjoldispila() > 4) {
            fxNafnLeikmadur.setText(leikmadurNafn + " " + "Samtals: " + leikmadur.getSamtala());
            fxHvorVinnur.setText("Leikur í gangi...(hámarksfjölda spila náð)");
            fxNyttSpilButton.setDisable(true);
        }
        else {

            SpilV s = stokkur.dragaSpil();
            leikmadur.gefaSpil(s);
            spil = new Spil();
            spil.setSpil(s);
            if (s.getGildi() == Gildi.AS && leikmadur.getSamtala() < 21) {
                as1eda11();
            }
            fxHboxLeikmadur.getChildren().add(spil);
            fxNyrLeikur.setDisable(true);
            fxNafnLeikmadur.setText(leikmadurNafn + " " + "Samtals: " + leikmadur.getSamtala());

            if (leikmadur.getSamtala() > 21) {


                leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
                if (vannLeikmadur()) {
                    peningur = peningur + nuvernadiVedmal;
                }
                else if(!vannLeikmadur()){
                    peningur = peningur - nuvernadiVedmal;
                }
                fxKomidNogButton.setDisable(true);
                fxNyttSpilButton.setDisable(true);
                fxNyrLeikur.setDisable(false);
            }
        }

        if (leikmadur.getFjoldispila() > 2) {
            fx10.setDisable(true);
            fx25.setDisable(true);
            fx50.setDisable(true);
        }

        if (peningur <= 0) {
            peningurBuinn();
        }
    }

    /**
     * Þegar notandi smellir á Komið nóg þá fær dealer nýtt spil meðan samtala hans er minni en 17, birtir síðan
     * hvort dealer eða leikmaður vann.
     */

    public void komidNogHandler() {

        fxAs1.setDisable(true);
        fxAs11.setDisable(true);

        fxNyrLeikur.setDisable(true);
        fxNyttSpilButton.setDisable(true);
        fxKomidNogButton.setDisable(true);

        while (dealer.getSamtala() < 17 && dealer.getFjoldispila() < 5) {

            SpilV s = stokkur.dragaSpil();
            dealer.gefaSpil(s);
            fxNafnDealer.setText("Dealer " + "Samtals: " + dealer.getSamtala());
            spil = new Spil();
            spil.setSpil(s);
            fxHboxDealer.getChildren().add(spil);
        }
        leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
        if (vannLeikmadur()) {
            peningur = peningur + nuvernadiVedmal;
        }
        else if(!vannLeikmadur()){
            peningur = peningur - nuvernadiVedmal;
        }
        fxNyrLeikur.setDisable(false);
        nuvernadiVedmal = 0;

        if (peningur <= 0) {
            peningurBuinn();
        }
    }

    /**
     * Frumstillir viðmót þannig nýr leikur geti hafist.
     */

    public void nyrLeikurHandler() {

        fxDoubleDown.setDisable(false);
        fxHboxDealer.getChildren().removeAll(fxHboxDealer.getChildren());
        fxHboxLeikmadur.getChildren().removeAll(fxHboxLeikmadur.getChildren());
        fxNyrLeikur.setDisable(true);
        fxPeningurUndir.setText("Peningur undir: 0kr");
        nuvernadiVedmal = 0;

        stokkur = new Stokkur();
        leikmadur.nyrLeikur();
        dealer.nyrLeikur();

        fxAs1.setDisable(true);
        fxAs11.setDisable(true);
        fx10.setDisable(false);
        fx25.setDisable(false);
        fx50.setDisable(false);
        fxKomidNogButton.setDisable(false);
        fxNyttSpilButton.setDisable(false);
        fxHvorVinnur.setText("Leikur í gangi...");

        for (int i = 0;i < 2 ;i++ ) {

            SpilV s = stokkur.dragaSpil();
            leikmadur.gefaSpil(s);
            spil = new Spil();
            spil.setSpil(s);
            if (s.getGildi() == Gildi.AS && leikmadur.getSamtala() < 21) {
                as1eda11();
            }
            fxHboxLeikmadur.getChildren().add(spil);

            SpilV t = stokkur.dragaSpil();
            dealer.gefaSpil(t);
            spil = new Spil();
            spil.setSpil(t);
            fxHboxDealer.getChildren().add(spil);

        }

        if (leikmadur.getSamtala() == 21) {

            leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
            if (vannLeikmadur()) {
                peningur = peningur + nuvernadiVedmal;
            }
            else if(!vannLeikmadur()){
                peningur = peningur - nuvernadiVedmal;
            }
            fxKomidNogButton.setDisable(true);
            fxNyttSpilButton.setDisable(true);
            fxNyrLeikur.setDisable(false);
        }
        else if (leikmadur.getSamtala() == 22) {

            leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
            if (vannLeikmadur()) {
                peningur = peningur + nuvernadiVedmal;
            }
            else if(!vannLeikmadur()){
                peningur = peningur - nuvernadiVedmal;
            }
            fxKomidNogButton.setDisable(true);
            fxNyttSpilButton.setDisable(true);
            fxNyrLeikur.setDisable(false);
        }

        fxNafnDealer.setText("Dealer " + "Samtals: " + dealer.getSamtala());
        fxNafnLeikmadur.setText(leikmadurNafn + " " + "Samtals: " + leikmadur.getSamtala());

        if (peningur <= 0) {
            peningurBuinn();
        }

    }

    /**
     * Forrit hættir að keyra þegar notandi smellir á hætta takkann.
     */

    public void stop(){
        Platform.exit();
    }

    /**
     * Frumstillir viðmót og gefur dealer og leikmanni tvö spil.
     * @param url frumstillir viðmót
     * @param resourceBundle frumstillir viðmót
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        fxInnistaeda.setText("Innistæða: " + peningur + "kr");
        stokkur = new Stokkur();
        leikmadur = new Leikmadur();
        dealer = new Leikmadur();
        fxNyrLeikur.setDisable(true);
        fxAs1.setDisable(true);
        fxAs11.setDisable(true);

        for (int i = 0;i < 2 ;i++ ) {
            SpilV s = stokkur.dragaSpil();
            leikmadur.gefaSpil(s);
            spil = new Spil();
            spil.setSpil(s);
            if (s.getGildi() == Gildi.AS && leikmadur.getSamtala() < 21) {
                as1eda11();
            }
            fxHboxLeikmadur.getChildren().add(spil);
            SpilV t = stokkur.dragaSpil();
            dealer.gefaSpil(t);
            spil = new Spil();
            spil.setSpil(t);
            fxHboxDealer.getChildren().add(spil);
        }

        if (leikmadur.getSamtala() == 21) {

            leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
            fxKomidNogButton.setDisable(true);
            fxNyttSpilButton.setDisable(true);
            fxNyrLeikur.setDisable(false);
        }
        else if (leikmadur.getSamtala() == 22) {

            leikmadur.hvorVann(dealer,fxHvorVinnur,fxInnistaeda, peningur,nuvernadiVedmal);
            fxKomidNogButton.setDisable(true);
            fxNyttSpilButton.setDisable(true);
            fxNyrLeikur.setDisable(false);
        }

        fxNafnDealer.setText("Dealer " + "Samtals: " + dealer.getSamtala());
        fxNafnLeikmadur.setText(leikmadurNafn + " " + "Samtals: " + leikmadur.getSamtala());
    }

}
