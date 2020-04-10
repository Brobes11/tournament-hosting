package com.techelevator.model;

public interface RosterDao {

    /**
     * add member to a team roster in the database
     * 
     * @param userId ID of member to be added 
     * @param teamId ID of the team taking in the new member
     * @return true boolean if successful
     */
    public boolean addMember(long userId, long teamId);

    /**
     * add member as captain to a team roster
     * 
     * @param userId ID of member to be added as captain
     * @param teamId ID of the team taking in new captain
     * @return true boolean if successful
     */
    public boolean addCaptain(long userId, long teamId);

    /**
     * deletes member from team roster in the database
     * 
     * @param userId ID of member to be deleted
     * @param teamId ID of the team the member is being deleted from
     * @return true boolean if successful
     */
    public boolean deleteMember(long userId, long teamId);

}