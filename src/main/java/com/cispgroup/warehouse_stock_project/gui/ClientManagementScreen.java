package com.cispgroup.warehouse_stock_project.gui;

import com.cispgroup.warehouse_stock_project.Customer;
import com.cispgroup.warehouse_stock_project.GUIWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ClientManagementScreen {


    @FXML public Text outputText;
    @FXML public TextFlow flow;
    @FXML public TextField searchTextField;
    @FXML public TextField customerSelectionTextField;
    @FXML public TextField newAddressTextField;
    @FXML public TextField newNameTextField;
    @FXML public TextField newPhoneNumberTextField;

    private Customer selectedCustomer;
    private ArrayList<Customer> nameHits = new ArrayList<>();

    @FXML public void navigateUp() {
        GUIWindow.navigateUp();
    }

    @FXML public void searchClients(ActionEvent event) {
        nameHits = new ArrayList<>();
        outputText.setText("");
        HashMap<UUID, Customer> names =  GUIWindow.getDatabase().getNamesMap();
        for (Customer customer : names.values()) {
            if (customer.getName().contains(searchTextField.getText())) { nameHits.add(customer); continue; }
            if (customer.getAddress().contains(searchTextField.getText())) { nameHits.add(customer); }
        }
        for (int i = 0; i < nameHits.size(); i++) {
            outputText.setText(outputText.getText() + "\n(" + i + ") " + nameHits.get(i).toString());
        }
        if (nameHits.isEmpty()) outputText.setText("Found nothing!");
    }

    @FXML public void selectName(ActionEvent event) {
        if (nameHits.isEmpty()) return;
        int i = 0;
        try {
            i = Integer.parseInt(customerSelectionTextField.getText());
        } catch (NumberFormatException nfe) {
            // chomp
        }
        if (i >= nameHits.size()) selectedCustomer = nameHits.get(nameHits.size() - 1);
        else selectedCustomer = nameHits.get(i);
    }

    @FXML public void createItem(ActionEvent event) {
        Customer person = new Customer(newNameTextField.getText(), newAddressTextField.getText(), newPhoneNumberTextField.getText());
        GUIWindow.getDatabase().getNamesMap().put(person.getUuid(), person);
    }

    @FXML public void deleteItem(ActionEvent event) {
        if (selectedCustomer != null) {
            /* Look I know how to remove the item from the database.
            The problem is that the Order objects look towards these StockItem objects.
            You can't just delete inventory if orders are attached.
            So I commented out the actual deletion code, which works. */
            // GUIWindow.getDatabase().getItemsMap().remove(selectedItem.getUuid());
            // itemHits.remove(selectedItem);
            searchClients(new ActionEvent());
        }
    }

    @FXML public void changeName(ActionEvent event) {
        if (selectedCustomer != null) {
            selectedCustomer.setName(newNameTextField.getText());
        }
        searchClients(new ActionEvent());
    }
    @FXML public void changeAddress(ActionEvent event) {
        if (selectedCustomer != null) {
            selectedCustomer.setAddress(newAddressTextField.getText());
        }
        searchClients(new ActionEvent());
    }
    @FXML public void changePhoneNumber(ActionEvent event) {
        if (selectedCustomer != null) {
            selectedCustomer.setPhoneNumber(newPhoneNumberTextField.getText());
        }
        searchClients(new ActionEvent());
    }

}
