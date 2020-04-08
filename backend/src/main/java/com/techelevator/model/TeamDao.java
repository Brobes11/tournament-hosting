package com.techelevator.model;

import java.util.List;

public interface TeamDao {

    /**
     * Save a new team to the database.
     *
     * @param teamName            the team name provided by the user team captain.
     * @param game                game the team is playing
     * @param acceptingNewMembers boolean: if true, accept new member requests.
     * @param teamBio             team bio provided by team captain.
     * @return the new team.
     */
    public Team createTeam(String teamName, String game, boolean acceptingNewMembers, String teamBio);

    /**
     * get a list of all active teams for any user to browse;
     * 
     * @return List of Team objects.
     */
    public List<Team> getAllTeams();

    /**
     * get single team profile based on the team id.
     * 
     * @param teamId
     * @return all team information
     */
    public Team getTeamById(long id);

    /**
     * get all teams tied to a specific user
     * 
     * @param userId
     * @return all teams that a specific user belongs to
     */
    public List<Team> getTeamsByUser(long id);

    
   
}