package com.datscie.cinematix.controllers.user_setting_controller;

import com.datscie.cinematix.dao.UserDaoImpl;
import com.datscie.cinematix.dao.UserDao;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;

public class UserChangeFieldController {
    private UserDao userDao;
    private User user;

    public UserChangeFieldController(User user) {
        this.userDao = new UserDaoImpl();
        this.user = user;
    }

    public void changeName(String name, String password) throws ApplicationException {
        int id = user.getId();
        if (!userDao.passwordConfirmation(id, password)) {
            throw new ApplicationException("Password is incorrect");
        }
        userDao.updateName(id, name);
    }

    public void changeEmail(String email, String password) throws ApplicationException {
        int id = user.getId();
        if (!userDao.passwordConfirmation(id, password)) {
            throw new ApplicationException("Password is incorrect");
        }
        userDao.updateEmail(id, email);
    }

    public void changePhone(String phone, String password) throws ApplicationException {
        int id = user.getId();
        if (!userDao.passwordConfirmation(id, password)) {
            throw new ApplicationException("Password is incorrect");
        }
        userDao.updatePhone(id, phone);
    }
}
