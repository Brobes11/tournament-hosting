package com.techelevator.model;

import java.util.List;

public interface TournamentMatchDao {

    public void createMatches(List<TournamentMatch> matches);

    public boolean finalizeMatches(List<TournamentMatch> matches);

}