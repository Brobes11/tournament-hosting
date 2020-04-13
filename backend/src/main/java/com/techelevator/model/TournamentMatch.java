package com.techelevator.model;

public class TournamentMatch {

    private long id;
    private long tournamentId;
    private int round;
    private long team1Id;
    private long team2Id;
    private long team1Score;
    private long team2Score;
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

    public long getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(long team2Score) {
        this.team2Score = team2Score;
    }

    public long getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(long team1Score) {
        this.team1Score = team1Score;
    }

    public long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(long team2Id) {
        this.team2Id = team2Id;
    }

    public long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(long team1Id) {
        this.team1Id = team1Id;
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