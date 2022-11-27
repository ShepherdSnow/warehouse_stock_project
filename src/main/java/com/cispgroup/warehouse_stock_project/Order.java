package com.cispgroup.warehouse_stock_project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Order implements Serializable {

    private final UUID uuid = UUID.randomUUID();
    private String date;
    private Customer customer;
    private OrderMap items = new OrderMap();

    public Order(String date, Customer customer, OrderMap items){
        this.date = date;
        this.customer = customer;
        this.items = items;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setItems(OrderMap items) { this.items = items; }

    public String getDate(){
        return date;
    }

    public Customer getCustomer(){
        return customer;
    }

    public HashMap<UUID, Integer> getItems(){
        return items;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getTotalPrice() {
        int quantity = 0;
        double totalPrice = 0.0;
        for (UUID uuid : items.keySet()) {
            quantity = items.get(uuid);
            totalPrice += GUIWindow.getDatabase().getItemsMap().get(uuid).getPrice() * quantity;
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order: client = " + customer.getName() + "; price = $" + getTotalPrice() + "; date = " + date + "; " + items.toString();
    }

}
