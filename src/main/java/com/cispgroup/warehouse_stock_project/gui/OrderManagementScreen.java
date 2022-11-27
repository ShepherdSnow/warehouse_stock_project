package com.cispgroup.warehouse_stock_project.gui;

import com.cispgroup.warehouse_stock_project.GUIWindow;
import com.cispgroup.warehouse_stock_project.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class OrderManagementScreen {

    @FXML public Text outputText;
    @FXML public TextFlow flow;
    @FXML public TextField searchTextField;
    @FXML public TextField orderSelectionTextField;

    private Order selectedOrder;
    private ArrayList<Order> orderHits = new ArrayList<>();

    @FXML public void navigateUp() {
        GUIWindow.navigateUp();
    }

    @FXML public void searchOrders(ActionEvent event) {
        orderHits = new ArrayList<>();
        outputText.setText("");
        HashMap<UUID, Order> orders =  GUIWindow.getDatabase().getOrdersMap();
        for (Order order : orders.values()) {
            if (order.getCustomer().getName().contains(searchTextField.getText())) orderHits.add(order);
        }
        for (int i = 0; i < orderHits.size(); i++) {
            outputText.setText(outputText.getText() + "\n(" + i + ") " + orderHits.get(i).toString());
        }
        if (orderHits.isEmpty()) outputText.setText("Found nothing!");
    }

    @FXML public void selectOrder(ActionEvent event) {
        if (orderHits.isEmpty()) return;
        int i = 0;
        try {
            i = Integer.parseInt(orderSelectionTextField.getText());
        } catch (NumberFormatException nfe) {
            // chomp
        }
        if (i >= orderHits.size()) selectedOrder = orderHits.get(orderHits.size() - 1);
        else selectedOrder = orderHits.get(i);
    }

    @FXML public void deleteOrder(ActionEvent event) {
        if (selectedOrder != null) {
            /* Look I know how to remove the object from the database.
            You can't just delete orders if inventory are attached.
            So I commented out the actual deletion code, which works. */
            // GUIWindow.getDatabase().getItemsMap().remove(selectedItem.getUuid());
            // itemHits.remove(selectedItem);
            searchOrders(new ActionEvent());
        }
    }
}
