package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
@Repository
public class OrderRepository {

    //database//Hashmap
    HashMap<String,Order> orderDB;
    HashMap<String,DeliveryPartner> partnerDB;
    HashMap<String, List<String>> pairDB;
    HashSet<String> isOrderAssigned;

    public OrderRepository() {
        orderDB=new HashMap<>();
        partnerDB=new HashMap<>();
        pairDB=new HashMap<>();
        isOrderAssigned=new HashSet<>();

    }

    public void addOrder(Order order) {
        orderDB.put(order.getId(),order);
        isOrderAssigned.add(order.getId());
    }

    public void addPartner(String partnerId) {
        DeliveryPartner d1=new DeliveryPartner(partnerId);
        partnerDB.put(partnerId,d1);
    }


    public void addOrderPartnerPair(String orderId, String partnerId) {
        List<String> list=pairDB.getOrDefault(partnerId,new ArrayList<>());
        list.add(orderId);
        pairDB.put(partnerId,list);
        partnerDB.get(partnerId).setNumberOfOrders(partnerDB.get(partnerId).getNumberOfOrders()+1);

        isOrderAssigned.remove(orderId);
    }

    public Order getOrderById(String orderId) {
        return orderDB.get(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId) {
        return partnerDB.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
//        return partnerDB.get(partnerId).getNumberOfOrders();
        return pairDB.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> list=pairDB.getOrDefault(partnerId,new ArrayList<>());
        return list;
    }

    public List<String> getAllOrders() {
        List<String> list=new ArrayList<>();
        for(String s: orderDB.keySet()){
            list.add(s);
        }
        return list;
    }

    public Integer getCountOfUnassignedOrders() {
        return isOrderAssigned.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        Integer count=0;
        //converting given string time to integer
        String arr[]=time.split(":"); //12:45
        int hr=Integer.parseInt(arr[0]);
        int min=Integer.parseInt(arr[1]);

        int total=(hr*60+min);

        List<String> list=pairDB.getOrDefault(partnerId,new ArrayList<>());
        if(list.size()==0)return 0; //no order assigned to partnerId

        for(String s: list){
            Order currentOrder=orderDB.get(s);
            if(currentOrder.getDeliveryTime()>total){
                count++;
            }
        }

        return count;

    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        //return in HH:MM format
        String str="00:00";
        int max=0;

        List<String>list=pairDB.getOrDefault(partnerId,new ArrayList<>());
        if(list.size()==0)return str;
        for(String s: list){
            Order currentOrder=orderDB.get(s);
            max=Math.max(max,currentOrder.getDeliveryTime());
        }
        //convert int to string (140-> 02:20)
        int hr=max/60;
        int min=max%60;

        if(hr<10){
            str="0"+hr+":";
        }else{
            str=hr+":";
        }

        if(min<10){
            str+="0"+min;
        }
        else{
            str+=min;
        }
        return str;


    }

    public void deletePartnerById(String partnerId) {
        if(!pairDB.isEmpty()){
            isOrderAssigned.addAll(pairDB.get(partnerId));
        }
//        if(!partnerDB.containsKey(partnerId)){
//            return;
//        }
        //removing form partnerDB
        partnerDB.remove(partnerId);

//        List<String>list=pairDB.getOrDefault(partnerId,new ArrayList<>());
//        if(list.size()==0)return;
//        isOrderAssigned.addAll(pairDB.get(partnerId));

        //remove form the pairDB
        pairDB.remove(partnerId);

    }

    public void deleteOrderById(String orderId) {
        //Delete an order and the corresponding partner should be unassigned
        if(orderDB.containsKey(orderId)){
            if(isOrderAssigned.contains(orderId)){
                isOrderAssigned.remove(orderId);
            }
            else{

                for(String str : pairDB.keySet()){
                    List<String> list=pairDB.get(str);
                    if(list.contains(orderId)){
                        list.remove(orderId);
                    }
                }
            }
        }


    }
}

//package com.driver;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Repository
//public class OrderRepository {
//
//    private HashMap<String, Order> orderMap;
//    private HashMap<String, DeliveryPartner> deliveryPartnerMap;
//    private HashMap<String, List<String>> orderToDeliveryPartner;
//    private HashMap<String, List<String>> partnerIdTimeMap;
//
//    public OrderRepository() {
//    }
//
//    public OrderRepository(HashMap<String, Order> orderMap, HashMap<String, DeliveryPartner> deliveryPartnerMap, HashMap<String, List<String>> orderToDeliveryPartner, HashMap<String, List<String>> partnerIdTimeMap) {
//        this.orderMap = orderMap;
//        this.deliveryPartnerMap = deliveryPartnerMap;
//        this.orderToDeliveryPartner = orderToDeliveryPartner;
//        this.partnerIdTimeMap = partnerIdTimeMap;
//    }
//
//    public void addOrder(Order order){
//        orderMap.put(order.getId(),order);
//    }
//
//    public void addPartner(String partnerId){
//        DeliveryPartner deliveryPartner=new DeliveryPartner();
//        deliveryPartner.setId(partnerId);
//        deliveryPartner.setNumberOfOrders(0);
//        deliveryPartnerMap.put(partnerId,deliveryPartner);
//    }
//
//    public void addOrderToPartnerId(String orderId, String partnerId){
//
//        List<String> orderIdList=new ArrayList<>();
//        List<String> timeList=new ArrayList<>();
//
//        if(orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId)) {
//            if (orderToDeliveryPartner.containsKey(partnerId)) {
//                orderIdList = orderToDeliveryPartner.get(partnerId);
//            }
//
//            orderIdList.add(orderId);
//
//            orderToDeliveryPartner.put(partnerId, orderIdList);
//
//            Order order= orderMap.get(orderId);
//            String time= String.valueOf(order.getDeliveryTime());
//
//            if(partnerIdTimeMap.containsKey(partnerId)){
//                timeList=partnerIdTimeMap.get(partnerId);
//            }
//
//            timeList.add(time);
//
//            partnerIdTimeMap.put(time, timeList);
//        }
//    }
//
//    public Order getOrderById(String orderId){
//        return orderMap.get(orderId);
//    }
//
//    public DeliveryPartner getPartnerById(String partnerId){
//        return deliveryPartnerMap.get(partnerId);
//    }
//
//    public int getOrderCountByPartnerId(String partnerId){
//
//        List<String> orderList=new ArrayList<>();
//        int count=0;
//        if(orderToDeliveryPartner.containsKey(partnerId)){
//            orderList=orderToDeliveryPartner.get(partnerId);
//            count= orderList.size();
//        }
//        return count;
//    }
//
//    public List<String> getOrdersByPartnerId(String partnerId){
//
//        List<String> orderList=new ArrayList<>();
//
//        if(orderToDeliveryPartner.containsKey(partnerId)){
//            orderList=orderToDeliveryPartner.get(partnerId);
//        }
//        return orderList;
//    }
//
//    public List<String> getAllOrders(){
//
//        // List<String> orderList=new ArrayList<>();
//
//        return new ArrayList<>(orderMap.keySet());
//    }
//
//    public int getCountOfUnassignedOrders(){
//        int totalOrders=orderMap.size();
//
//        for(String orders: orderMap.keySet()){
//            for(String partner: orderToDeliveryPartner.keySet()){
//                List<String> orderList=new ArrayList<>();
//                orderList=orderToDeliveryPartner.get(partner);
//                for(String order: orderList){
//                    if(orders.equals(order)){
//                        totalOrders--;
//                        break;
//                    }
//                }
//            }
//        }
//        return totalOrders;
//    }
//
//    public int getCountOfOrdersLeftAfterTime(String time, String partnerId){
//        Order order=new Order();
//
//        int convertedTime= order.convertTimeToInt(time);
//        int count=0;
//        if(partnerIdTimeMap.containsKey(partnerId)){
//            List<String> timeList=new ArrayList<>();
//            timeList= partnerIdTimeMap.get(partnerId);
//            for(String times: timeList){
//                int newTime= order.convertTimeToInt(times);
//                if(newTime>convertedTime){
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//
//    public String getLastDeliveryTimeByPartnerId(String partnerId){
//        int maxTime=0;
//        String time="";
//        Order order=new Order();
//        List<String> list=new ArrayList<>();
//        if(partnerIdTimeMap.containsKey(partnerId)){
//            list= partnerIdTimeMap.get(partnerId);
//            for(String times: list){
//                int newTime=order.convertTimeToInt(times);
//                if(newTime>maxTime){
//                    maxTime=newTime;
//                }
//            }
//        }
//        time=Integer.toString(maxTime);
//        return time;
//    }
//
//    public void deletePartnerId(String partnerId){
//        if(deliveryPartnerMap.containsKey(partnerId)){
//            deliveryPartnerMap.remove(partnerId);
//        }
//        if(orderToDeliveryPartner.containsKey(partnerId)){
//            orderToDeliveryPartner.remove(partnerId);
//        }
//        if(partnerIdTimeMap.containsKey(partnerId)){
//            partnerIdTimeMap.remove(partnerId);
//        }
//    }
//
//    public void deleteOrderById(String orderId){
//        if(orderMap.containsKey(orderId)){
//            orderMap.remove(orderId);
//        }
//        List<String> orderList=new ArrayList<>();
//        for(String partnerId: orderToDeliveryPartner.keySet()){
//            orderList=orderToDeliveryPartner.get(partnerId);
//            for(String orders: orderList){
//                if(orders.equals(orderId)){
//                    orderList.remove(orders);
//                }
//            }
//            orderToDeliveryPartner.put(partnerId,orderList);
//        }
//        Order order=new Order();
//        String time= String.valueOf(orderMap.get(orderId));
//
//        List<String> timeList=new ArrayList<>();
//        for(String partner: partnerIdTimeMap.keySet()){
//            timeList=partnerIdTimeMap.get(partner);
//            for(String times: timeList){
//                if(times.equals(time)){
//                    timeList.remove(times);
//                }
//            }
//            partnerIdTimeMap.put(partner,timeList);
//        }
//    }
//
//}