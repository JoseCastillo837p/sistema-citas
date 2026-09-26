module mx.uacam.fi.sistemacitas {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.uacam.fi.sistemacitas to javafx.fxml;
    exports mx.uacam.fi.sistemacitas;
}