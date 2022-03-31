package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SpilApplication extends Application {
    /**
     * Keyrir dialog og biður notanda um nafn og aldur.
     *
     * @param stage dialog
     * @throws IOException kastar IO villu
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpilApplication.class.getResource("leikmadur-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 357);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
