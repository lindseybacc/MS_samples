module com.example.atm_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.atm_project to javafx.fxml;
    exports com.example.atm_project;
}