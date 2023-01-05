package com.datscie.cinematix.models;

public class Admin extends Auth {
    public Admin() {
    }

    public Admin(int id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public void login() {
        
    }

    @Override
    public void register() {
        // TODO Auto-generated method stub

    }
}
