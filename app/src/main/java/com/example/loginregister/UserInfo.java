package com.example.loginregister;

public class UserInfo {
    private String user_FullName;
    private String user_Username;
    private String user_Email;
    private String user_Phone;
    private String user_Password;

    public UserInfo() {
    }

    public UserInfo(String user_FullName, String user_Username, String user_Email, String user_Phone, String user_Password) {
        this.user_FullName = user_FullName;
        this.user_Username = user_Username;
        this.user_Email = user_Email;
        this.user_Phone = user_Phone;
        this.user_Password = user_Password;
    }

    public String getUser_FullName() {
        return user_FullName;
    }

    public void setUser_FullName(String user_FullName) {
        this.user_FullName = user_FullName;
    }

    public String getUser_Username() {
        return user_Username;
    }

    public void setUser_Username(String user_Username) {
        this.user_Username = user_Username;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }
}
