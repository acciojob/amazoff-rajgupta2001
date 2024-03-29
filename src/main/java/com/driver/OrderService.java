package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class OrderService {
    OrderRepository orderRepository=new OrderRepository();


    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }


    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }


    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return orderRepository.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }


}

//package com.driver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrderService {
//
//    //@Autowired
//    OrderRepository orderRepository=new OrderRepository();
//
//    public void addOrder(Order order){
//        orderRepository.addOrder(order);
//    }
//
//    public void addPartner(String id){
//        orderRepository.addPartner(id);
//    }
//
//    public void addOrderToPartnerId(String orderId, String partnerId){
//        orderRepository.addOrderToPartnerId(orderId,partnerId);
//    }
//
//    public Order getOrderById(String orderId){
//        return orderRepository.getOrderById(orderId);
//    }
//
//    public DeliveryPartner getPartnerById(String partnerId){
//        return orderRepository.getPartnerById(partnerId);
//    }
//
//    public int getOrderCountByPartnerId(String partnerId){
//        return orderRepository.getOrderCountByPartnerId(partnerId);
//    }
//
//    public List<String> getOrdersByPartnerId(String partnerId){
//        return orderRepository.getOrdersByPartnerId(partnerId);
//    }
//
//    public List<String> getAllOrders(){
//        return orderRepository.getAllOrders();
//    }
//
//    public int getCountOfUnassignedOrders(){
//        return orderRepository.getCountOfUnassignedOrders();
//    }
//
//    public int getCountOfOrdersLeftAfterTime(String time, String partnerId){
//        return orderRepository.getCountOfOrdersLeftAfterTime(time,partnerId);
//    }
//
//    public String getLastDeliveryTimeByPartnerId(String partnerId){
//        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
//    }
//
//    public void deletePartnerId(String partnerId){
//        orderRepository.deletePartnerId(partnerId);
//    }
//
//    public void deleteOrderById(String orderId){
//        orderRepository.deleteOrderById(orderId);
//    }
//}