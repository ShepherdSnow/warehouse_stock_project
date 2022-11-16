package com.cispgroup.warehouse_stock_project;

public class StockItem {
    // Private variables

    private String name;
    private double price;
    private Location location;

    /**
     * @param name
     * @param price
     * @param location
     */
    public StockItem(String name, double price, Location location) {
        this.name = name;
        this.price = price;
        this.setLocation(location);
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
     * @return toString, returns items details with spaces to separate each
     *         item.
     */
    @Override
    public String toString() {

        return getName() + " " + getPrice();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

