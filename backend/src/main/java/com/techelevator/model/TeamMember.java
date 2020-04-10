package com.techelevator.model;

public class TeamMember {

    private long memberId;
    private long teamId;
    private boolean captainStatus;

    public long getMemberId() {
        return memberId;
    }

    public boolean isCaptainStatus() {
        return captainStatus;
    }

    public void setCaptainStatus(boolean captainStatus) {
        this.captainStatus = captainStatus;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void SetMemberId(long senderId) {
        this.memberId = senderId;
    }

}