package com.cispgroup.warehouse_stock_project.gui;

import com.cispgroup.warehouse_stock_project.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen {

    @FXML TextFlow flow;
    @FXML Text outputText;
    @FXML TextField searchTextField;

    @FXML public void gotoInventoryManagementScreen(ActionEvent event) {
        Scene inventoryManagementScreen = new Scene(new Parent() {} ); // might cause problems
        try { inventoryManagementScreen = new Scene(new FXMLLoader(GUIWindow.class.getResource("inventory_management.fxml")).load()); }
        catch (IOException ioe) { /* gulp */ }
        GUIWindow.setScene(inventoryManagementScreen);
    }

    @FXML public void gotoClientManagementScreen(ActionEvent event) {
        Scene clientManagementScreen = new Scene(new Parent() {} ); // might cause problems
        try { clientManagementScreen = new Scene(new FXMLLoader(GUIWindow.class.getResource("client_management.fxml")).load()); }
        catch (IOException ioe) { /* gulp */ }
        GUIWindow.setScene(clientManagementScreen);
    }

    @FXML public void gotoOrderManagementScreen(ActionEvent event) {
        Scene orderManagementScreen = new Scene(new Parent() {} ); // might cause problems
        try { orderManagementScreen = new Scene(new FXMLLoader(GUIWindow.class.getResource("order_management.fxml")).load()); }
        catch (IOException ioe) { /* gulp */ }
        GUIWindow.setScene(orderManagementScreen);
    }

    /**
     * @param event -- this is the event passed in from JavaFX. Don't worry about it.
     */
    @FXML public void search(ActionEvent event) {

        String searchResults = "";

        String query = searchTextField.getText();

        WarehouseDatabase db = GUIWindow.getDatabase();
        HashMap<UUID, StockItem> items = db.getItemsMap();
        HashMap<UUID, Customer> names = db.getNamesMap();
        HashMap<UUID, Order> orders = db.getOrdersMap();

        ArrayList<StockItem> itemHits = new ArrayList<>();
        ArrayList<Customer> nameHits = new ArrayList<>();
        ArrayList<Order> orderHits = new ArrayList<>();

        // tally number of hits
        for (StockItem item : items.values()) {
            if (item.getName().contains(query)) itemHits.add(item);
        }
        for (Customer name: names.values()) {
            if (name.getName().contains(query)) { nameHits.add(name); continue; }
            if (name.getAddress().contains(query)) { nameHits.add(name); }
        }
        for (Order order : orders.values()) {

            if (order.getCustomer().getName().contains(query)) { orderHits.add(order); continue; }
            if (order.getCustomer().getAddress().contains(query)) { orderHits.add(order); continue; }

            for (UUID uuid : order.getItems().keySet()) {
                if (items.get(uuid).getName().contains(query)) { orderHits.add(order); continue; }
            }

            if (order.getDate().contains(query)) { orderHits.add(order); continue; }

        }

        int totalHits = itemHits.size() + nameHits.size() + orderHits.size();

        searchResults = searchResults
                .concat("Found matches: " + totalHits + "\n")
                .concat(" -- items: " + itemHits.size() + "\n")
                .concat(" -- names: " + nameHits.size() + "\n")
                .concat(" -- orders: " + orderHits.size() + "\n")
                .concat("\n");

        searchResults += "\nINVENTORY: ##########\n";
        if (!itemHits.isEmpty()) for (StockItem item : itemHits) {
            searchResults += item.toString() + "\n";
        }
        searchResults += "\nCUSTOMERS: ##########\n";
        if (!nameHits.isEmpty()) for (Customer customer : nameHits) {
            searchResults += customer.toString() + "\n";
        }
        searchResults += "\nORDERS: ##########\n";
        if (!orderHits.isEmpty()) for (Order order : orderHits) {
            searchResults += order.toString() + "\n";
        }


        outputText.setText("Searched for {" + searchTextField.getText() + "}:\n" +
                "############################\n" +
                searchResults);

    }

}
