package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentTeamDao implements TournamentTeamDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTournamentTeamDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TournamentTeam> getTournamentRosterById(long tournamentId) {
        List<TournamentTeam> tourneyTeams = new ArrayList<>();
        String sql = "SELECT a.team_name, c.username, c.email FROM teams a JOIN teamroster b ON a.id = b.team_id "
                + "JOIN users c ON b.user_id = c.id WHERE b.captain = true AND a.game in "
                + "(SELECT a.game FROM teams a JOIN tournamentroster b ON a.id = b.team_id JOIN tournaments c ON b.tourney_id = c.id "
                + "WHERE c.id = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
        while (results.next()) {
            TournamentTeam tourneyTeam = mapResultToTournamentTeam(results);
            tourneyTeams.add(tourneyTeam);
        }
        return tourneyTeams;
    }

    private TournamentTeam mapResultToTournamentTeam(SqlRowSet results) {
        TournamentTeam tourneyTeam = new TournamentTeam();
        tourneyTeam.setTeamName(results.getString("team_name"));
        tourneyTeam.setCaptainUsername(results.getString("username"));
        tourneyTeam.setCaptainEmail(results.getString("email"));
        return tourneyTeam;
    }

}