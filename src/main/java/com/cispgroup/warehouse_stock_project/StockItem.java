package com.cispgroup.warehouse_stock_project;

public class StockItem {
    // Private variables

    private String name;
    private double price;
    private int quantity;
    private Location location;
    // constructor
    /**
     * @param name
     * @param price
     * @param quantity
     */
    public StockItem(String name, double price, int quantity,Location location) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price of the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the brand name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the item price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the item quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return toString, returns items details with spaces to separate each
     *         item.
     */
    @Override
    public String toString() {

        return getName() + " " + getPrice() + " " + getQuantity();
    }
}

