package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface TournamentDao {

    public List<Tournament> getAllTournaments();

    public Tournament getTournamentById(long id);

    public List<Tournament> getTournamentsByUser(long id);

    public List<Tournament> getTournamentsByTeam(long id);

    public Tournament createTournament(Tournament newTournament, Long userId);

    public boolean updateTournament(Tournament tournament);

    public Map<String,Integer> getTourneyWins(long id);

}