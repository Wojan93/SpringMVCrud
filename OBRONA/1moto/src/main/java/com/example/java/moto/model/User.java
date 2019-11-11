package com.example.java.moto.model;


import javax.persistence.Entity;



@Entity
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    private String email;

    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return lastName;
    }

    public void setSurName(String surName) {
        this.lastName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
