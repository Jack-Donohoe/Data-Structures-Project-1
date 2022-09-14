module com.example.ca_assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires xstream;

    exports com.example.ca_assignment1.models;
    opens com.example.ca_assignment1.models to javafx.fxml, xstream;
    exports com.example.ca_assignment1.utils;
    opens com.example.ca_assignment1.utils to javafx.fxml, xstream;
    exports com.example.ca_assignment1.main;
    opens com.example.ca_assignment1.main to javafx.fxml,xstream;

}