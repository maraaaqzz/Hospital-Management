module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.management;

    exports code;
    opens code to javafx.fxml;

    exports code.gui;
    opens code.gui to javafx.fxml;

    exports code.domain;
    opens code.domain to javafx.fxml;

    exports code.exceptions;
    opens code.exceptions to javafx.fxml;

    exports code.repository;
    opens code.repository to javafx.fxml;

    exports code.service;
    opens code.service to javafx.fxml;

    exports code.settings;
    opens code.settings to javafx.fxml;

    exports code.UI;
    opens code.UI to javafx.fxml;
}