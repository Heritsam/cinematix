package com.datscie.cinematix.utils;

import com.datscie.cinematix.models.User;

public class LocalStorage {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LocalStorage.loggedInUser = loggedInUser;
    }
}
