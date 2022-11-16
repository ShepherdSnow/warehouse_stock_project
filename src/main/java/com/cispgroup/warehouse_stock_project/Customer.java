package com.cispgroup.warehouse_stock_project;

import java.util.Stack;

public class Customer {

    private String name;
    private String address;
    private String phoneNumber;
    private Stack<Order> purchaseHistory;

    public Customer(String name, String address, String phoneNumber, Stack<Order> purchaseHistory) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.purchaseHistory = purchaseHistory;
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
}
