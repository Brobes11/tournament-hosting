package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentMatchDao implements TournamentMatchDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTournamentMatchDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TournamentMatch> createMatches(List<TournamentMatch> matches) {
        for (TournamentMatch tournamentMatch : matches) {
            String sql = "INSERT INTO tournamentMatch (tourney_id, round_number, team_1_id, team_2_id) "
                    + "VALUES (?, ?, ?, ?) RETURNING match_id";
            Long newId = jdbcTemplate.queryForObject(sql, Long.class, tournamentMatch.getTournamentId(),
                    tournamentMatch.getRound(), tournamentMatch.getTeam1Id(), tournamentMatch.getTeam2Id());
            tournamentMatch.setId(newId);
        }
        return matches;
    }

    @Override
    public boolean finalizeMatches(List<TournamentMatch> matches) {
        for (TournamentMatch tournamentMatch : matches) {
            String sql = "UPDATE tournamentMatch SET team_1_score = ?, team_2_score = ? winner_id = ? WHERE id = ?;";
        jdbcTemplate.update(sql, tournamentMatch.getTeam1Score(), tournamentMatch.getTeam2Id(), tournamentMatch.getWinnerId(), tournamentMatch.getId());
        }
        return true;
    }

}