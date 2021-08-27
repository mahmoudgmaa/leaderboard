package com.example.leaderboard.models;

import java.io.Serializable;

public class User implements Serializable {
    private int bananas;
    private String lastDayPlayed;
    private int longestStreak;
    private String name;
    private int stars;
    private Boolean subscribed;
    private String uid;
    private Boolean isCurrentUser;
    private int rank;

    public User() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getBananas() {
        return bananas;
    }

    public void setBananas(int bananas) {
        this.bananas = bananas;
    }

    public String getLastDayPlayed() {
        return lastDayPlayed;
    }

    public void setLastDayPlayed(String lastDayPlayed) {
        this.lastDayPlayed = lastDayPlayed;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getCurrentUser() {
        return isCurrentUser;
    }

    public void setCurrentUser(Boolean currentUser) {
        isCurrentUser = currentUser;
    }
}
