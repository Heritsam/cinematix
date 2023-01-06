package com.datscie.cinematix.controllers;

import com.datscie.cinematix.models.Admin;
import javax.swing.JOptionPane;

import com.datscie.cinematix.views.AdminDashboardView;
import com.datscie.cinematix.views.AuthView;
import com.datscie.cinematix.views.UserDashboardView;
import com.datscie.cinematix.dao.UserDao;
import com.datscie.cinematix.dao.UserDaoImpl;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;

public class AuthController {
    private AuthView view;
    private UserDao dao;

    //Constructor
    public AuthController(AuthView view) {
        this.view = view;
        this.dao = new UserDaoImpl();
    }
    //ambil email dan password dari textfield
    public void login() {
        String email = view.getInputEmail().getText();
        String password = view.getInputPassword().getText();

        //cek 
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email is required");
            return;
        }

        // check email format with regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(view, "Email format is invalid");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password is required");
            return;
        }

        try {
            User user = dao.login(email, password);
            this.reset();
            UserDashboardView userDashboardView = new UserDashboardView(user);
            userDashboardView.setLocationRelativeTo(null);
            userDashboardView.setVisible(true);
            view.dispose();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void register() {
        String name = view.getInputFullName().getText();
        String email = view.getInputEmailReg().getText();
        String password = view.getInputPasswordReg().getText();
        String passwordConfirmation = view.getInputPasswordRegConf().getText();
        String phone = view.getInputPhone().getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name is required");
            return;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email is required");
            return;
        }

        // check email format with regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(view, "Email format is invalid");
            return;
        }

        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Phone is required");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password is required");
            return;
        }

        if (!passwordConfirmation.equals(passwordConfirmation)) {
            JOptionPane.showMessageDialog(view, "Password confirmation is not match");
            return;
        }

        try {
            User user = dao.register(name, email, password, phone);
            this.reset();
            JOptionPane.showMessageDialog(view, "Registration success");
            UserDashboardView userDashboardView = new UserDashboardView(user);
            userDashboardView.setLocationRelativeTo(null);
            userDashboardView.setVisible(true);
            view.dispose();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void loginAsAdmin() {
        String email = view.getInputEmail().getText();
        String password = view.getInputPassword().getText();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email is required");
            return;
        }

        // check email format with regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(view, "Email format is invalid");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password is required");
            return;
        }

        try {
            Admin admin = dao.loginAsAdmin(email, password);
            this.reset();

            AdminDashboardView adminDashboardView = new AdminDashboardView(admin);
            adminDashboardView.setLocationRelativeTo(null);
            adminDashboardView.setVisible(true);
            view.dispose();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void reset() {
        view.getInputEmail().setText("");
        view.getInputPassword().setText("");
        view.getInputFullName().setText("");
        view.getInputEmailReg().setText("");
        view.getInputPasswordReg().setText("");
        view.getInputPasswordRegConf().setText("");
        view.getInputPhone().setText("");
    }
}
