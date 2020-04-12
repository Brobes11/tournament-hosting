package com.techelevator.model;

import java.util.List;

public interface TournamentTeamDao {

    /**
     * get single team profile based on the team id.
     * 
     * @param tournamentId
     * @return all teams and captains for a single tournament.
     */
    public List<TournamentTeam> getTournamentRosterById(long tournamentId);
}