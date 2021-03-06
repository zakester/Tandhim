module com.example.tandhim {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires java.management;
    requires poi.ooxml;
    requires poi.ooxml.schemas;
    requires com.google.zxing;
    requires jfreesvg;
    requires json.simple;
    requires spring.security.core;

    opens com.example.tandhim to javafx.fxml;
    exports com.example.tandhim;
}