module org.example.autoscuola {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.autoscuola to javafx.fxml;
    exports com.autoscuola;
}