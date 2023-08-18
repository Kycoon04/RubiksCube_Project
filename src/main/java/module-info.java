module cr.ac.una.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cr.ac.una.proyecto to javafx.fxml;
    opens cr.ac.una.proyecto.model to javafx.base;
    opens cr.ac.una.controller;
    exports cr.ac.una.proyecto;
}
