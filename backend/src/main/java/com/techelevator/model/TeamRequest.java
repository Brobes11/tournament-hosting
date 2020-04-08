package com.techelevator.model;

public class TeamRequest {

    private long userId;
    private long teamId;
    private String message;

    public long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}