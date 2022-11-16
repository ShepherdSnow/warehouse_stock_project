package com.cispgroup.warehouse_stock_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUIWindow extends Application {

    private static Stage stage;
    protected static final WarehouseDatabase database = new WarehouseDatabase();

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUIWindow.class.getResource("login_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        GUIWindow.stage = stage;

        GUIWindow.stage.setTitle("Warehouse Stock Management Database");
        GUIWindow.stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("box_icon.jpg"))));
        GUIWindow.stage.setScene(scene);
        GUIWindow.stage.show();

    }
    public static void main(String[] args) {
        launch();
    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

}