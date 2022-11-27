package com.cispgroup.warehouse_stock_project;

import java.util.HashMap;
import java.util.UUID;

public class OrderMap extends HashMap<UUID, Integer> {
    @Override
    public String toString() {
        String returnString = "";
        for (UUID uuid : keySet()) {
            returnString += GUIWindow.getDatabase().getItemsMap().get(uuid).getName();
            returnString += "=" + get(uuid) + "   ";
        }
        return returnString;
    }
}