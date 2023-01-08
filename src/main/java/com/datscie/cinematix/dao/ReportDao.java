package com.datscie.cinematix.dao;

import com.datscie.cinematix.models.Report;
import com.datscie.cinematix.utils.ApplicationException;

public interface ReportDao {
    public Report getTotalTicketsByDate(String date) throws ApplicationException;
    public Report getTotalTicketsByMovieAndDate(String date, String movie) throws ApplicationException;
}
