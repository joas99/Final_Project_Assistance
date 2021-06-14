package com.example.finalprojectassistance;

public class ClientData {

    String FullName,UserEmail,PhoneNumber,Address;

    //empty constructor for fstore
    public ClientData(){

    }

//generated constuctor
    public ClientData(String fullName, String userEmail, String phoneNumber, String address) {
        this.FullName = fullName;
        this.UserEmail = userEmail;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
    }//end constructor

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        this.UserEmail = userEmail;
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
}
