module com.example.stden {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stden to javafx.fxml;
    exports com.example.stden;
}