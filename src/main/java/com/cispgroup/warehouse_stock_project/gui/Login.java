package com.cispgroup.warehouse_stock_project.gui;

import com.cispgroup.warehouse_stock_project.GUIWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Login {

    private static final String CORRECT_PASSWORD = "warehouseAdmin&&";
    private static final String[] CORRECT_USERNAMES = {"gsnow", "zluna", "hking", "emcfann"};

    @FXML
    Label failureLabel;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    Button btn1;

    @FXML
    public void login(ActionEvent event) {

        boolean usernameCheck = isUsernameValid(usernameField.getText());
        boolean passwordCheck = isPasswordValid(passwordField.getText());

        if (usernameCheck && passwordCheck) {
            Scene homeScreenScene = new Scene(new Parent() {} ); // might cause problems
            try { homeScreenScene = new Scene(new FXMLLoader(GUIWindow.class.getResource("home_screen.fxml")).load()); }
            catch (IOException ioe) {}
            GUIWindow.setScene(homeScreenScene);
        }
        else {
            failureLabel.setText("*Failure! Check username or password.");
            failureLabel.setTextFill(Color.color(1,0,0));
        }

    }

    private boolean isPasswordValid(String enteredPassword) {
        if (enteredPassword == null) return false;
        if (enteredPassword.equals(CORRECT_PASSWORD)) return true;
        else return false;
    }

    //checks to see if enteredUsername is in the list of valid usernames
    private boolean isUsernameValid(String enteredUsername) {
        if (enteredUsername == null) return false;
        for (String userName : CORRECT_USERNAMES) {
            if (userName.equals(enteredUsername)) return true;
        }
        return false;
    }



}
