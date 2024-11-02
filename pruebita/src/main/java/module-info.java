module com.example.pruebita {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.pruebita to javafx.fxml;
    exports com.example.pruebita;
}