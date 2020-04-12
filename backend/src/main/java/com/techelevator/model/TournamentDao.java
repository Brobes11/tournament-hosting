package com.techelevator.model;

import java.util.List;

public interface TournamentDao {

    public List<Tournament> getAllTournaments();

    public Tournament getTournamentById(long id);

    public List<Tournament> getTournamentsByUser(long id);

    public List<Tournament> getTournamentsByTeam(long id);

    public Tournament createTournament(Tournament newTournament, Long userId);

    public boolean updateTournament(Tournament tournament);

}