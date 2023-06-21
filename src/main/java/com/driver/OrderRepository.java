package com.driver;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final Map<String,Order>orderMap;
    private final Map<String,DeliveryPartner>deliveryPartnerMap;
    private final Map<String,String>orderPartnerMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
        this.deliveryPartnerMap = new HashMap<>();
        this.orderPartnerMap = new HashMap<>();
    }

    public void addOrder(Order order){
         orderMap.put(order.getId(),order);

    }
    public void addPartner(String partnerId){
        deliveryPartnerMap.put(partnerId,new DeliveryPartner(partnerId));
    }
    public void addOrderPartnerPair(String orderId, String partnerId){
        orderPartnerMap.put(orderId,partnerId);
        int orders =deliveryPartnerMap.get(partnerId).getNumberOfOrders()+1;
        deliveryPartnerMap.get(partnerId).setNumberOfOrders(orders);
    }
    public Order getOrderById(String orderId){
        return orderMap.get(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerMap.get(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){
        int count=0;
        for (String orderId:orderPartnerMap.keySet()) {
            if(orderPartnerMap.get(orderId).equals(partnerId)){
                count++;
            }
        }
        return count;
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        List<String>order=new ArrayList<>();
        for(String orderId:orderPartnerMap.keySet()){
            if(orderPartnerMap.get(orderId).equals(partnerId)){
                order.add(orderPartnerMap.get(orderId));
            }
        }
        return order;
    }
    public List<Order> getAllOrders() {

        return new ArrayList<>(orderMap.values());
    }
    public int getCountOfUnassignedOrders(){
        int count=0;
        for (String orderId:orderMap.keySet()){
            if(!orderMap.containsKey(orderId)){
                count++;
            }
        }
        return count;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int count=0;
        for(String orderId: orderPartnerMap.keySet()){
            if(orderPartnerMap.get(orderId).equals(partnerId) && orderMap.get(orderId).getDeliveryTime()>0){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        String lastDeliveryTime=null;
        for(String orderId:orderPartnerMap.keySet()){
            if(orderPartnerMap.get(orderId).equals(partnerId)){
                Order order=orderMap.get(orderId);
                String ans=String.valueOf(order.getDeliveryTime());
                if(lastDeliveryTime==null || ans.compareTo(lastDeliveryTime) > 0){
                    lastDeliveryTime= ans;
                }
            }
        }
        return lastDeliveryTime;
    }
    public void deletePartnerById(String partnerId){
        deliveryPartnerMap.remove(partnerId);
        for(String orderId:orderPartnerMap.keySet()){
            if(orderPartnerMap.get(orderId).equals(partnerId)){
                orderPartnerMap.remove(orderId);
            }
        }
    }
    public void deleteOrderById(String partnerId){
        orderMap.remove(partnerId);
        orderPartnerMap.remove(partnerId);
    }
}
