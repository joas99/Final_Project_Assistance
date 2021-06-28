package com.example.finalprojectassistance;

public class AdminNotificationData {

    String FullName,PhoneNumber,Address,Time,Service;
    String UserID;
    String Order;
    String Title;
    String Date;

    //empty constructor for fstore
    public AdminNotificationData(){

    }
    public AdminNotificationData(String fullName, String phoneNumber,
                                 String address, String time,
                                 String service, String userID,
                                 String order, String title, String date, String userId) {
        this.FullName = fullName;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
        this.Time = time;
        this.Service = service;
        this.UserID = userID;
        this.Order = order;
        this.Title = title;
        this.Date = date;
        this.UserId = userId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    String UserId;

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

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        this. Service = service;
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
        this.Order = order;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
