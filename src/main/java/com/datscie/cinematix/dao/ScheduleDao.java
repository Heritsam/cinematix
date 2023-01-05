package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Schedule;
import com.datscie.cinematix.utils.ApplicationException;

public interface ScheduleDao {
    public ArrayList<Schedule> getAllSchedule() throws ApplicationException;
    public void addSchedule(Schedule schedule) throws ApplicationException;
    public void updateSchedule(Schedule schedule) throws ApplicationException;
    public void deleteSchedule(Schedule schedule) throws ApplicationException;
}
