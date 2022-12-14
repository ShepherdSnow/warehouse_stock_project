package com.cispgroup.warehouse_stock_project;

public class Location {

    //warehouse location ROW
    private int x;

    //warehouse location COLUMN
    private int y;

    //warehouse location SHELF_HEIGHT
    private int z;

    /**
     * @param x
     * @param y
     * @param z
     */
    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @param x sets x to integer location.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * @param y sets y to integer location.
     */
    public void setY(int y){
        this.x = y;
    }

    /**
     * @param z sets z to integer location.
     */
    public void setZ(int z){
        this.z = z;
    }

    /**
     * @return return the value of x.
     */
    public int getX(){
        return x;
    }

    /**
     * @return return the value of y.
     */
    public int getY(){
        return y;
    }

    /**
     * @return return the value of z.
     */
    public int getZ(){
        return z;
    }
    @Override
    public String toString() {

        return getX() + ", " + getY() + ", " + getZ();
    }
}
