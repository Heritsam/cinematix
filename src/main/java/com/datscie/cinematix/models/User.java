package com.datscie.cinematix.models;

import java.util.ArrayList;
import java.util.List;

public class User extends Auth {
    private List<Ticket> userTickets;

    public User() {
        userTickets = new ArrayList<Ticket>();
    }

    public User(int id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
        userTickets = new ArrayList<Ticket>();
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
