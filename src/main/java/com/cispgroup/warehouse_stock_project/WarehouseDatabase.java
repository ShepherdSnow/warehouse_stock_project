package com.cispgroup.warehouse_stock_project;

import java.util.HashMap;

/**
 class WarehouseDatabase -- This is the class that holds the actual data for our warehouse. This class contains a number
 of superpowered lists that contain that data.
 */
public class WarehouseDatabase {

    private HashMap<String, StockItem> items = new HashMap<String, StockItem>();
    private HashMap<String, Customer> names = new HashMap<String, Customer>();
    private HashMap<String, Order> orders = new HashMap<String, Order>();

    protected WarehouseDatabase() {
        //does nothing;
    }
    /**
     * @return items
     */
    public HashMap<String, StockItem> getItemsMap() {
        return items;
    }
    /**
     * @return names
     */
    public HashMap<String, Customer> getNamesMap() {
        return names;
    }
    /**
     * @return orders
     */
    public HashMap<String, Order> getOrdersMap() {
        return orders;
    }
}
