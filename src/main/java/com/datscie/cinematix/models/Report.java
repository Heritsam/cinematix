package com.datscie.cinematix.models;

public class Report {
    private int ticketsSold;
    private int totalIncome;

    public Report() {}

    public Report(int ticketsSold, int totalIncome, int totalWatchHours) {
        this.ticketsSold = ticketsSold;
        this.totalIncome = totalIncome;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }
}
