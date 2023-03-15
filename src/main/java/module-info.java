module com.example.crms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;

    opens com.example.crms to javafx.fxml;
    exports com.example.crms;
    exports com.example.crms.reg;
    opens com.example.crms.reg to javafx.fxml;
}