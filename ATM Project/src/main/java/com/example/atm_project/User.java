package com.example.atm_project;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 3789909326487155148L;
    private int id;
    private String username;
    private String lastName;
    private String firstName;
    private int pin;
    private double checkingBalance;
    private double savingsBalance;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int password) {
        this.pin = password;
    }

    public double getChecking() {return this.checkingBalance; }
    public double getSavings() {return this.savingsBalance; }
}
