package com.cispgroup.warehouse_stock_project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 class WarehouseDatabase -- This is the class that holds the actual data for our warehouse. This class contains a number
 of superpowered lists that contain that data.
 */
public class WarehouseDatabase implements Serializable {

    private HashMap<UUID, StockItem> items = new HashMap<UUID, StockItem>();
    private HashMap<UUID, Customer> names = new HashMap<UUID, Customer>();
    private HashMap<UUID, Order> orders = new HashMap<UUID, Order>();

    protected WarehouseDatabase() {
        //does nothing;
    }
    /**
     * @return items
     */
    public HashMap<UUID, StockItem> getItemsMap() {
        return items;
    }
    /**
     * @return names
     */
    public HashMap<UUID, Customer> getNamesMap() {
        return names;
    }
    /**
     * @return orders
     */
    public HashMap<UUID, Order> getOrdersMap() {
        return orders;
    }

    public boolean isContainedInOrder(UUID testedUUID) {
        for (Order order : orders.values()) {
            for (UUID uuid : order.getItems().keySet()) {
                if (uuid.equals(testedUUID)) {
                    return true;
                }
            }
        }
        return false;
    }

}
