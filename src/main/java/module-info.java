module com.example.ticketsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires spire.barcode.free;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.ticketsapp to javafx.fxml;
    exports com.example.ticketsapp;
}