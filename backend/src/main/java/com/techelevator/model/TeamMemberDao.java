package com.techelevator.model;

public interface TeamMemberDao {

    /**
     * add member to a team roster in the database
     * 
     * @param teamMember object to be added to roster in database
     * @return true boolean if successful
     */
    public boolean addMember(TeamMember teamMember);


    /**
     * deletes member from team roster in the database
     * 
     * @param teamMember object to be deleted from roster in database
     * @return true boolean if successful
     */
    public boolean deleteMember(TeamMember teamMember);

}