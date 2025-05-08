module arboles.arboles {
    requires javafx.controls;
    requires javafx.fxml;


    opens arboles.arboles to javafx.fxml;
    exports arboles.arboles;
    exports arboles.arboles.model;
    opens arboles.arboles.model to javafx.fxml;
    exports arboles.arboles.controller;
    opens arboles.arboles.controller to javafx.fxml;
}