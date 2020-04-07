package com.techelevator.model;

public class Team {

    private long teamId;
    private String teamName;
    private String game;
    private String teamBio;
    private boolean acceptingNewMembers = true;

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isAcceptingNewMembers() {
        return acceptingNewMembers;
    }

    public void setAcceptingNewMembers(boolean acceptingNewMembers) {
        this.acceptingNewMembers = acceptingNewMembers;
    }

    public String getTeamBio() {
        return teamBio;
    }

    public void setTeamBio(String teamBio) {
        this.teamBio = teamBio;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}