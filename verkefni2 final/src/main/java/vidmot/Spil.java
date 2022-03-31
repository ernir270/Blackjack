package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import vinnsla.SpilV;
import vinnsla.Tegund;

import java.io.IOException;
import java.util.Objects;

public class Spil extends AnchorPane {

    @FXML
    private ImageView fxMyndNidri;
    @FXML
    private ImageView fxMyndUppi;
    @FXML
    private Label fxTextiUppi;
    @FXML
    private Label fxTextiNidri;

    Image hjarta = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/hjarta.png")));
    Image spadi = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/spadi.png")));
    Image tigull = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/tigull.png")));
    Image lauf = new Image(Objects.requireNonNull(getClass().getResourceAsStream("myndir/lauf.png")));

    /**
     * Les inn spil viðmót.
     */
    public Spil(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("spil-view.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni


        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Býr til viðmót af spili, nær í tegund og gildi og smíðar mynd.
     *
     * @param s spil sem er dregið úr stokk
     */
    public void setSpil(SpilV s){
        if (s.getTegund() == Tegund.HJARTA) {
            fxMyndNidri.setImage(hjarta);
            fxMyndUppi.setImage(hjarta);
        }
        else if (s.getTegund() == Tegund.SPADI) {
            fxMyndUppi.setImage(spadi);
            fxMyndNidri.setImage(spadi);
        }
        else if (s.getTegund() == Tegund.TIGULL) {
            fxMyndNidri.setImage(tigull);
            fxMyndUppi.setImage(tigull);
        }
        else if (s.getTegund() == Tegund.LAUF) {
            fxMyndNidri.setImage(lauf);
            fxMyndUppi.setImage(lauf);
        }
        fxTextiNidri.setText(String.valueOf(s.getGildi()));
        fxTextiUppi.setText(String.valueOf(s.getGildi()));
    }

}
