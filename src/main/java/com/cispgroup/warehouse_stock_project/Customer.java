package com.cispgroup.warehouse_stock_project;

import java.io.Serializable;
import java.util.Stack;
import java.util.UUID;

public class Customer implements Serializable {

    private UUID uuid = UUID.randomUUID();
    private String name;
    private String address;
    private String phoneNumber;
    private Stack<Order> purchaseHistory;

    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.purchaseHistory = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Stack<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(Stack<Order> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "name = " + name + "; address = " + address + "; phone_number = " + phoneNumber + ";";
    }
}
