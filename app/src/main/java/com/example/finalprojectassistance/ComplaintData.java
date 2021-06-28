package com.example.finalprojectassistance;

public class ComplaintData {
    String Title;
    String ReportContent;
    String SubjectName;
    String Time;
    String UserID;
    String FullName,PhoneNumber,Address;



    public ComplaintData(){}

    public ComplaintData(String title, String UserID,  String reportContent, String subjectName,String time,String fullName, String phoneNumber, String address) {


        this.Title = title;
        this.ReportContent = reportContent;
        this.SubjectName = subjectName;
        this.FullName = fullName;
        this.UserID = UserID;
        this.PhoneNumber = phoneNumber;
        this.Time = time;
        this.Address = address;

    }

    public String getFullName() { return FullName; }

    public void setFullName(String fullName) { this.FullName = fullName; }

    public String getUserID() { return UserID; }

    public void setUserID(String UserID) { this.UserID = UserID; }

    public String getPhoneNumber() { return PhoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.PhoneNumber = phoneNumber; }

    public String getAddress() { return Address; }

    public void setAddress(String address) { this.Address = address; }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getReportContent() {
        return ReportContent;
    }

    public void setReportContent(String reportContent) {
        this.ReportContent = reportContent;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        this.SubjectName = subjectName;
    }

    public String getTime() { return Time; }

    public void setTime(String time) { this.Time = time; }


}
