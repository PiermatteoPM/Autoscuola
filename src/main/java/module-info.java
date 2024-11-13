module org.example.autoscuola {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.autoscuola to javafx.fxml;
    exports com.autoscuola;
}