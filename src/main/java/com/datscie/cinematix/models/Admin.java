package com.datscie.cinematix.models;

import com.datscie.cinematix.models.Auth;

public class Admin extends Auth {
    public Admin() {
    }

    public Admin(int id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub

    }

    @Override
    public void register() {
        // TODO Auto-generated method stub

    }
}
