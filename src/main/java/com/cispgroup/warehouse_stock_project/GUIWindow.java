package com.cispgroup.warehouse_stock_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;
import java.util.UUID;

public class GUIWindow extends Application {

    private static Stage stage;
    private static final WarehouseDatabase database;
    private static final Stack<Scene> backStack = new Stack<>();

    // deserialize database from local file system
    static {

        WarehouseDatabase db = null;
        ObjectInputStream ois = null;
        try {
            InputStream inputStream = GUIWindow.class.getResourceAsStream("data.data");
            ois = new ObjectInputStream(inputStream);
            db = (WarehouseDatabase) ois.readObject();
            if (inputStream != null) inputStream.close();
            ois.close();
        } catch (IOException e) {
            //gulp
        } catch (ClassNotFoundException e) {
            //thwop
        } catch (NullPointerException e) {
            //chomp
        }
        finally {
            /* THE IMPORTANT PART! --> */ if (db != null) database = db; else database = new WarehouseDatabase();
        }

        // hardcoded example objects

        StockItem exampleToilet = new StockItem(
                "Toilet",
                40.00,
                new Location(0,0,0)
        );
        StockItem exampleHighShelvedPalette = new StockItem(
                "Palette",
                107.32,
                new Location(2,0,4)
        );
        StockItem valve = new StockItem(
                "Bigsby's Toilet Control Valve",
                17.89,
                new Location(3,2,0)
        );

        database.getItemsMap().put(exampleToilet.getUuid(), exampleToilet);
        database.getItemsMap().put(exampleHighShelvedPalette.getUuid(), exampleHighShelvedPalette);
        database.getItemsMap().put(valve.getUuid(), valve);

        Customer exampleSamuel = new Customer("Samuel Lethargy", "9234 Bumpkin Avenue", "423-324-4444" );
        Customer exampleSally = new Customer("Loaded Sally", "9999 Dollars Street", "999-999-9999");
        Customer exampleDave = new Customer("Discontented Dave", "I ain't giving you my address", "423-YOU-WISH");

        database.getNamesMap().put(exampleSamuel.getUuid(), exampleSamuel);
        database.getNamesMap().put(exampleSally.getUuid(), exampleSally);
        database.getNamesMap().put(exampleDave.getUuid(), exampleDave);

        OrderMap daveOrderQuantities = new OrderMap();
        daveOrderQuantities.put(valve.getUuid(), 2);
        daveOrderQuantities.put(exampleToilet.getUuid(), 1);

        OrderMap sallyOrderQuantities = new OrderMap();
        sallyOrderQuantities.put(exampleHighShelvedPalette.getUuid(), 9999);

        OrderMap samuelOrderQuantities = new OrderMap();
        samuelOrderQuantities.put(valve.getUuid(), 1);

        Order example_order_1 = new Order("Yesterday", exampleDave, daveOrderQuantities);
        Order example_order_2 = new Order("Immediately", exampleDave, daveOrderQuantities);
        Order example_order_3 = new Order("Next Week", exampleSally, sallyOrderQuantities);
        Order example_order_4 = new Order("Always Tomorrow", exampleSamuel, samuelOrderQuantities);

        HashMap<UUID,Order> orders = database.getOrdersMap();
        orders.put(example_order_1.getUuid(), example_order_1);
        orders.put(example_order_2.getUuid(), example_order_2);
        orders.put(example_order_3.getUuid(), example_order_3);
        orders.put(example_order_4.getUuid(), example_order_4);

    }

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

    @Override
    public void stop() throws Exception {
        writeDatabaseToFileSystem();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setScene(Scene scene) {
        backStack.add(stage.getScene());
        stage.setScene(scene);
        stage.show();
    }

    public static void navigateUp() {
        if (backStack.peek() != null) {
            stage.setScene(backStack.pop());
            stage.show();
        }
    }

    public static WarehouseDatabase getDatabase() { return database; }

    private static void writeDatabaseToFileSystem() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(GUIWindow.class.getResource("data.data").getPath());
            oos = new ObjectOutputStream(fos);
            oos.writeObject(database);
            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println("caught IO EXCEPTION");
            System.out.println(e.toString());
        } catch (NullPointerException e) {
            System.out.println("caught NULL POINTER EXCEPTION");
            System.out.println(e.toString());
        }
    }

}