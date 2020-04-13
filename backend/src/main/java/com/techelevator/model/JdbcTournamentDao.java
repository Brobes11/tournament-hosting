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

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTournamentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> allTournaments = new ArrayList<>();
        String sql = "SELECT id, tourney_name, game, start_date, end_date, location, entry_fee, prize_desc, accepting_entries, tournament_owner FROM "
                + "tournaments;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
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
        tournament.setTournamentOwner(results.getInt("tournament_owner"));
        tournament.setAcceptingEntries(results.getBoolean("accepting_entries"));
        return tournament;
    }

    @Override
    public Tournament getTournamentById(long id) {
        String sql = "SELECT id, tourney_name, game, start_date, end_date, location, "
                + " entry_fee, prize_desc, tournament_owner, accepting_entries FROM tournaments WHERE id = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowSetTournament(results);
        }
        return null;
    }

    @Override
    public List<Tournament> getTournamentsByUser(long id) {
        List<Tournament> usersTournaments = new ArrayList<>();

        String sql = "SELECT id, tourney_name, game, start_date, end_date, location, "
                + " entry_fee, prize_desc, tournament_owner, accepting_entries FROM tournaments "
                + "WHERE id IN (SELECT tourney_id FROM tournamentroster WHERE team_id "
                + "IN (SELECT team_id FROM teamroster WHERE user_id = ?));";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            usersTournaments.add(mapRowSetTournament(results));
        }
        return usersTournaments;
    }

    @Override
    public Tournament createTournament(Tournament newTournament, Long userId) {
        String sql = "INSERT INTO tournaments (tourney_name, game, start_date, end_date, location, prize_desc, tournament_owner, entry_fee, accepting_entries) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, newTournament.getTournamentName(),
                newTournament.getGame(), newTournament.getStartDate(), newTournament.getEndDate(),
                newTournament.getLocation(), newTournament.getPrizeDescription(), userId, newTournament.getEntryFee(),
                newTournament.isAcceptingEntries());
        newTournament.setTournamentId(newId);

        return newTournament;
    }

    @Override
    public List<Tournament> getTournamentsByTeam(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateTournament(Tournament tournament) {
        String sql = "UPDATE tournaments SET tourney_name = ?, prize_desc = ? WHERE id = ?;";
        jdbcTemplate.update(sql, tournament.getTournamentName(), tournament.getPrizeDescription(),
                tournament.getTournamentId());
        return true;
    }

}