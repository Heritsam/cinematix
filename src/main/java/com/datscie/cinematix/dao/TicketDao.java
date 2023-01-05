package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Ticket;
import com.datscie.cinematix.models.User;

public interface TicketDao {
    public ArrayList<Ticket> getAllTicket();
    public ArrayList<Ticket> getAllTicketByUser(User user);
}
