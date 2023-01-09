package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Ticket;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;

public interface TicketDao {
    public ArrayList<Ticket> getAllTicket() throws ApplicationException;
    public ArrayList<Ticket> getAllTicketByUser(User user) throws ApplicationException;
    public ArrayList<Ticket> getTicketsByDate(String date) throws ApplicationException;
    public ArrayList<Ticket> getTicketsByMovieAndDate(String movie, String date) throws ApplicationException;
}
