package com.datscie.cinematix.dao;

import com.datscie.cinematix.models.Admin;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;

public interface UserDao {
    public User login(String email, String password) throws ApplicationException;
    public User register(String name, String email, String password, String phone) throws ApplicationException;
    public Admin loginAsAdmin(String email, String password) throws ApplicationException;
}
