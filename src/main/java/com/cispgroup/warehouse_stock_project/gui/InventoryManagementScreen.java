package com.cispgroup.warehouse_stock_project.gui;

import com.cispgroup.warehouse_stock_project.GUIWindow;
import com.cispgroup.warehouse_stock_project.Location;
import com.cispgroup.warehouse_stock_project.StockItem;
import com.cispgroup.warehouse_stock_project.WarehouseDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class InventoryManagementScreen {

    @FXML public Text outputText;
    @FXML public TextFlow flow;
    @FXML public TextField searchTextField;
    @FXML public TextField itemSelectionTextField;
    @FXML public TextField zField;
    @FXML public TextField xField;
    @FXML public TextField yField;
    @FXML public TextField newPriceTextField;
    @FXML public TextField newNameTextField;
    @FXML public Label warningLabel;

    private StockItem selectedItem;
    private ArrayList<StockItem> itemHits = new ArrayList<>();

    @FXML public void navigateUp() {
        GUIWindow.navigateUp();
    }

    @FXML public void searchInventory(ActionEvent event) {
        itemHits = new ArrayList<>();
        outputText.setText("");
        HashMap<UUID, StockItem> items =  GUIWindow.getDatabase().getItemsMap();
        for (StockItem item : items.values()) {
            if (item.getName().contains(searchTextField.getText())) itemHits.add(item);
        }
        for (int i = 0; i < itemHits.size(); i++) {
            outputText.setText(outputText.getText() + "\n(" + i + ") " + itemHits.get(i).toString());
        }
        if (itemHits.isEmpty()) outputText.setText("Found nothing!");
    }

    @FXML public void selectItem(ActionEvent event) {
        if (itemHits.isEmpty()) return;
        int i = 0;
        try {
            i = Integer.parseInt(itemSelectionTextField.getText());
        } catch (NumberFormatException nfe) {
            // chomp
        }
        if (i >= itemHits.size()) selectedItem = itemHits.get(itemHits.size() - 1);
        else selectedItem = itemHits.get(i);
    }

    @FXML public void createItem(ActionEvent event) {
        String name = newNameTextField.getText();
        double price = 0.00;
        int x = 0;
        int y = 0;
        int z = 0;
        try {
            x = Integer.parseInt(xField.getText());
            y = Integer.parseInt(yField.getText());
            z = Integer.parseInt(zField.getText());
            price = Double.parseDouble(newPriceTextField.getText());
        } catch (NumberFormatException nfe) {
            // chomp
        }
        Location location = new Location(x,y,z);
        StockItem item = new StockItem(name, price, location);
        GUIWindow.getDatabase().getItemsMap().put(item.getUuid(), item);
    }

    @FXML public void deleteItem(ActionEvent event) {
        if (selectedItem != null) {
            WarehouseDatabase database = GUIWindow.getDatabase();
            if (!database.isContainedInOrder(selectedItem.getUuid())) {
                database.getItemsMap().remove(selectedItem.getUuid());
                itemHits.remove(selectedItem);
                searchInventory(new ActionEvent());
            }
        }
    }

    @FXML public void changeLocation(ActionEvent event) {
        if (selectedItem != null) {
            int x = selectedItem.getLocation().getX();
            int y = selectedItem.getLocation().getY();
            int z = selectedItem.getLocation().getZ();
            try {
                x = Integer.parseInt(xField.getText());
                y = Integer.parseInt(yField.getText());
                z = Integer.parseInt(zField.getText());
            } catch (NumberFormatException nfe) {
                // gulp
            }
            Location newLocation = new Location(x,y,z);
            GUIWindow.getDatabase().getItemsMap().get(selectedItem.getUuid()).setLocation(newLocation);
            selectedItem.setLocation(newLocation);
        }
    }

    @FXML public void changeName(ActionEvent event) {
        if (selectedItem != null) {
            selectedItem.setName(newNameTextField.getText());
        }
    }

    @FXML public void changePrice(ActionEvent event) {
        try {
            if (selectedItem != null) {
                selectedItem.setPrice(Double.parseDouble(newPriceTextField.getText()));
            }
        } catch (NumberFormatException e) {
            // gulp
        }
    }

}

