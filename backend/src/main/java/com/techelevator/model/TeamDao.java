package com.techelevator.model;

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
    public Team saveTeam(String teamName, String game, boolean acceptingNewMembers, String teamBio);
}