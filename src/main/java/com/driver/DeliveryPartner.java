package com.driver;

public class DeliveryPartner {

    private String id;
    private int numberOfOrders;

    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfOrders = 0;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getNumberOfOrders(){
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}

//package com.driver;
//
//public class DeliveryPartner {
//
//    private String id;
//    private int numberOfOrders;
//
//    public DeliveryPartner(String id) {
//        this.id = id;
//        this.numberOfOrders = 0;
//    }
//
//    public DeliveryPartner() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Integer getNumberOfOrders(){
//        return numberOfOrders;
//    }
//
//    public void setNumberOfOrders(Integer numberOfOrders) {
//        this.numberOfOrders = numberOfOrders;
//    }
//}