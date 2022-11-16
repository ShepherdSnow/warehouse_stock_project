module com.cispgroup.warehouse_stock_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.cispgroup.warehouse_stock_project to javafx.fxml;
    exports com.cispgroup.warehouse_stock_project;
    exports com.cispgroup.warehouse_stock_project.gui;
    opens com.cispgroup.warehouse_stock_project.gui to javafx.fxml;
}