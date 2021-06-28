package com.example.finalprojectassistance;

public class WorkerData {

    String FullName,UserEmail,Phone,Address,Speciality;


    public WorkerData(){

    }

    public WorkerData(String fullName, String userEmail, String Phone, String address, String speciality) {
        this.FullName = fullName;
        this.UserEmail = userEmail;
        this.Phone = Phone;
        this.Address = address;
        this.Speciality = speciality;
    }

    //getter and setter Methods
    public String getFullName() { return FullName; }

    public void setFullName(String fullName) { this.FullName = fullName;}

    public String getUserEmail() { return UserEmail; }

    public void setUserEmail(String userEmail) { this.UserEmail = userEmail; }

    public String getPhone() { return Phone; }

    public void setPhone(String Phone) {  this.Phone = Phone; }

    public String getAddress() { return Address; }

    public void setAddress(String address) { this.Address = address; }

    public String getSpeciality() { return Speciality; }

    public void setSpeciality(String speciality) { this.Speciality = speciality; }
    //empty constructor for fstore



}
