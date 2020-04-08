package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentDao implements TournamentDao {

    private JdbcTemplate JdbcTemplate;

    @Autowired
    public JdbcTournamentDao(DataSource dataSource) {
        this.JdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> allTournaments = new ArrayList<>();
        String sql = "SELECT id, tourney_name, game, start_date, end_date, location, entry_fee, prize_desc FROM "
                + "tournaments;";
        SqlRowSet results = JdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Tournament tournament = mapRowSetTournament(results);
            allTournaments.add(tournament);
        }
        return allTournaments;
    }

    private Tournament mapRowSetTournament(SqlRowSet results) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(results.getLong("id"));
        tournament.setTournamentName(results.getString("tourney_name"));
        tournament.setGame(results.getString("game"));
        tournament.setStartDate(results.getDate("start_date").toLocalDate());
        tournament.setEndDate(results.getDate("end_date").toLocalDate());
        tournament.setLocation(results.getString("location"));
        tournament.setEntryFee(results.getInt("entry_fee"));
        tournament.setPrizeDescription(results.getString("prize_desc"));
        return tournament;
    }

}