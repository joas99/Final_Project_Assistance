package com.example.finalprojectassistance;

public class AdminOrderData {


    String FullName,PhoneNumber,Address,Time,Service;
    String UserID,Order;


    //empty constructor for fstore
    public AdminOrderData(){

    }

    public AdminOrderData(String fullName, String phoneNumber, String address, String time, String userID, String order, String service) {
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Address = address;
        Time = time;
        UserID = userID;
        Order = order;
        Service = service;
    }



    public String getService() {
        return Service;
    }

    public void setService(String service) {
        this.Service = service;
    }


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }
}
