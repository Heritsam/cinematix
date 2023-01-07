/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datscie.cinematix.controllers.user_setting_controller;

import com.datscie.cinematix.dao.UserDaoImpl;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quin Derbi
 */
public class UserChangeNameController {
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    User user;

    public UserChangeNameController(User user) {
        this.userDaoImpl = new UserDaoImpl();
        this.user = user;
    }

    public void changeName(String name, String password) throws ApplicationException {
        int id = user.getId();
        if (!userDaoImpl.passwordConfirmation(id, password)) {
            throw new ApplicationException("Password is incorrect");
        }
        userDaoImpl.updateName(id, name);
    }

}
