module com.example.sep1_main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.sep1_main to javafx.fxml;
    exports com.example.sep1_main;
}