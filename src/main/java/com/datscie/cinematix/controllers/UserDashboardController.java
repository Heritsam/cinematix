/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datscie.cinematix.controllers;

import com.datscie.cinematix.dao.UserDao;
import com.datscie.cinematix.dao.UserDaoImpl;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.views.user_setting_view.UserChangeEmailView;
import com.datscie.cinematix.views.user_setting_view.UserChangeNameView;
import com.datscie.cinematix.views.user_setting_view.UserChangePhoneView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quin Derbi
 */
public class UserDashboardController {
    UserDaoImpl userDao;
    User user;

    public UserDashboardController(User user) {
        this.userDao = new UserDaoImpl();
        this.user = user;
    }

    public void buttonChangeName() {
        try {
            UserChangeNameView userChangeNameView = new UserChangeNameView(userDao.getUserById(user.getId()));
            userChangeNameView.setLocationRelativeTo(null);
            userChangeNameView.setVisible(true);
        } catch (ApplicationException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buttonChangeEmail() {
        try {
            UserChangeEmailView userChangeEmailView = new UserChangeEmailView(userDao.getUserById(user.getId()));
            userChangeEmailView.setLocationRelativeTo(null);
            userChangeEmailView.setVisible(true);
        } catch (ApplicationException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buttonChangePhone() {
        try {
            UserChangePhoneView userChangePhoneView = new UserChangePhoneView(userDao.getUserById(user.getId()));
            userChangePhoneView.setLocationRelativeTo(null);
            userChangePhoneView.setVisible(true);
        } catch (ApplicationException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
