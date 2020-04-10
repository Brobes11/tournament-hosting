package com.techelevator.model;

public class Roster {

    private long senderId;
    private long recipientId;
    private boolean captainStatus;

    public long getSenderId() {
        return senderId;
    }

    public boolean isCaptainStatus() {
        return captainStatus;
    }

    public void setCaptainStatus(boolean captainStatus) {
        this.captainStatus = captainStatus;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

}