module verkefni {
    requires javafx.controls;
    requires javafx.fxml;


    opens vidmot to javafx.fxml;
    exports vidmot;
    exports vinnsla;
    opens vinnsla to javafx.fxml;
}
