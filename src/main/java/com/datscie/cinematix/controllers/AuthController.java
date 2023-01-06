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

    public AuthController(AuthView view) {
        this.view = view;
        this.dao = new UserDaoImpl();
    }

    public void login() {
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
        }//check if phone number contains only numbers
        if(!phone.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(view, "Phone number must contain only numbers");
            return;
        }//check if phone number contains 10 digits
        if(phone.length() != 10) {
            JOptionPane.showMessageDialog(view, "Phone number must contain 10 digits");
            return;
        }//check if phone number starts with 0
        if(!phone.startsWith("0")) {
            JOptionPane.showMessageDialog(view, "Phone number must start with 0");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password is required");
            return;
        }
        if(password.length() < 8) {
            JOptionPane.showMessageDialog(view, "Password must be at least 8 characters");
            return;
        }//check if password contains at least one number
        if(!password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(view, "Password must contain at least one number");
            return;
        }//check if password contains at least one uppercase letter
        if(!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(view, "Password must contain at least one uppercase letter");
            return;
        }//check if password contains at least one lowercase letter
        if(!password.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(view, "Password must contain at least one lowercase letter");
            return;
        }//check if password contains at least one special character

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
