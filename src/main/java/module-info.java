module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;
    exports com.example.lab1.controller;
    opens com.example.lab1.controller to javafx.fxml;
}