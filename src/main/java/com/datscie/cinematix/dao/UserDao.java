package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Admin;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;

public interface UserDao {
    public User login(String email, String password) throws ApplicationException;
    public User register(String name, String email, String password, String phone) throws ApplicationException;
    public Admin loginAsAdmin(String email, String password) throws ApplicationException;
    public ArrayList<User> getAllUsers() throws ApplicationException;
    public User getUserById(int id) throws ApplicationException;
    public boolean passwordConfirmation(int id, String password) throws ApplicationException;
    public User updateName(int id, String name) throws ApplicationException;
    public User updateEmail(int id, String email) throws ApplicationException;
    public User updatePhone(int id, String phone) throws ApplicationException;
}
