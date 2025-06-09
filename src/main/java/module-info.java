module bd.edu.seu.seustreamz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens bd.edu.seu.seustreamz to javafx.fxml;
    exports bd.edu.seu.seustreamz;
    exports bd.edu.seu.seustreamz.controllers;
    opens bd.edu.seu.seustreamz.controllers to javafx.fxml;
    exports bd.edu.seu.seustreamz.models;
    opens bd.edu.seu.seustreamz.models to javafx.fxml;
}