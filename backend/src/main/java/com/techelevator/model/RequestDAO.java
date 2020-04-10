package com.techelevator.model;

import java.util.List;

public interface RequestDAO {


    public Request createTeamRequest(Request request);

    public List<Request> getRequestsByTournamentId(Long tournamentId);

    public List<Request> getRequestsByTeamId(Long teamId);

}