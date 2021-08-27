package com.example.leaderboard.models;

import java.util.List;

public class ApiResponse {
    private String message;
    private List<User> displayedUsers;

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getDisplayedUsers() {
        return displayedUsers;
    }

    public void setDisplayedUsers(List<User> displayedUsers) {
        this.displayedUsers = displayedUsers;
    }
}
