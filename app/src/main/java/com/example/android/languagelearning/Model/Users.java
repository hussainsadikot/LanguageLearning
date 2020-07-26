package com.example.android.languagelearning.Model;

public class Users {


    private String name,password,phone,email;
    private  Integer id;

    public Users(){
        //Empty Constructor is Require
        //Do not Delete
    }


    public Users(Integer id, String name, String password, String phone, String email) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public Integer getId() {
        return id ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
