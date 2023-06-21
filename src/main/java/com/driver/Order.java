package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        ///// The deliveryTime has to converted from string to int and then stored in the attribute
       // deliveryTime  = HH*60 + MM
//        int hours = 0;
//        hours = ((deliveryTime.charAt(0) - '0') * 10) + ((deliveryTime.charAt(1) - '0'));
//        int min = 0;
//        min = ((deliveryTime.charAt(3) - '0') * 10) + ((deliveryTime.charAt(4) - '0'));

        this.id=id;
       // this.deliveryTime = (60 * hours) + min;
       this.deliveryTime= Integer.parseInt(deliveryTime);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }
}
