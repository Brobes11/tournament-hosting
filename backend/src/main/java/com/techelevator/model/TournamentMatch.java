package com.techelevator.model;

public class TournamentMatch {

    private long id;
    private long tournamentId;
    private int round;
    private long homeTeam;
    private long awayTeam;
    private int homeScore;
    private int awayScore;
    private long winnerId;

    public long getId() {
        return id;
    }

    public long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }

    public long getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public long getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public long getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(long awayTeam) {
        this.awayTeam = awayTeam;
    }

    public long getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(long homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setId(long id) {
        this.id = id;
    }

}