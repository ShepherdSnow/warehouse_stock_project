package com.cispgroup.warehouse_stock_project;
import java.util.ArrayList;
public class Order {
    private String date;
    private Customer customer;
    private ArrayList<StockItem> items = new ArrayList<>();




    public Order(String date, Customer customer){
        this.date = date;
        this.Customer = customer;


    }

    public void setDate(String date){
        this.date = date;
    }

    public void setCustomer(Customer customer){
        this.Customer = customer;
    }

    public void setItems(StockItem items){
        this.items.add(items);
    }

    public String getDate(){
        return date;
    }

    public Customer getCustomer(){
        return customer;
    }

    public ArrayList<StockItem> getItems(){
        return items;
    }

    @Override
    public String toString(){
        return date + " \n" + customer + " \n" + items;
    }
}
