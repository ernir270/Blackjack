package vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LeikmadurDialogPane extends DialogPane {

    @FXML
    public TextField fxNafn;
    public TextField fxAldur;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Sækir nafn sem notandi slær inn og ræsir aðalviðmót.
     * @param event viðmót
     * @throws IOException villa
     */
    public void afram(ActionEvent event) throws IOException {

        String nafn = fxNafn.getText();
        int aldur = Integer.parseInt(fxAldur.getText());

        if (aldur >= 18) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tuttuguOgEinn.fxml"));
            root = loader.load();
            SpilController spilController = loader.getController();
            spilController.saekjaNafn(nafn);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,600,480);
            stage.setScene(scene);
            stage.show();
        }
        else {
            stop();
        }

    }

    /**
     * Forrit hættir að keyra þegar notandi smellir á hætta við takkann.
     */
    public void stop(){
        Platform.exit();
    }
}
