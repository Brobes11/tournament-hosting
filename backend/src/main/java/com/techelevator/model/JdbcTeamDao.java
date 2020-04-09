package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTeamDao implements TeamDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * Create a new user dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for users.
     *
     * @param dataSource an SQL data source
     */

    @Autowired
    public JdbcTeamDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Save a new team to the database.
     *
     * @param teamName            the team name provided by the user team captain.
     * @param game                game the team is playing
     * @param acceptingNewMembers boolean: if true, accept new member requests.
     * @param teamBio             team bio provided by team captain.
     * @return the new team.
     */
    public Team createTeam(Team newTeam, Long userId) {

        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO teams (team_name, game, accepting_members, team_bio) VALUES (?, ?, ?, ?) RETURNING id",
                Long.class, newTeam.getTeamName(), newTeam.getGame(), newTeam.isAcceptingNewMembers(),
                newTeam.getTeamBio());

        newTeam.setTeamId(newId);
        String makeCaptain = "INSERT INTO teamroster (user_id,team_id,captain )VALUES (?,?,true)";
        jdbcTemplate.update(makeCaptain, userId, newId);
        return newTeam;
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> allTeams = new ArrayList<>();
        String sql = "SELECT id, team_name, game, accepting_members, team_bio from teams;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Team team = mapResultToTeam(results);
            allTeams.add(team);
        }
        return allTeams;
    }

    @Override
    public Team getTeamById(long id) {
        String sql = "SELECT id, team_name, game, accepting_members, team_bio from teams " + " WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapResultToTeam(results);
        } else {
            return null;
        }
    }

    private Team mapResultToTeam(SqlRowSet results) {
        Team team = new Team();
        team.setTeamId(results.getLong("id"));
        team.setTeamName(results.getString("team_name"));
        team.setGame(results.getString("game"));
        team.setAcceptingNewMembers(results.getBoolean("accepting_members"));
        team.setTeamBio(results.getString("team_bio"));
        return team;
    }

    @Override
    public List<Team> getTeamsByUser(long id) {
        List<Team> userTeams = new ArrayList<>();
        String sql = "SELECT id, team_name, game, accepting_members, team_bio from teams "
                + "WHERE id IN (SELECT team_id FROM teamroster WHERE user_id = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            Team team = mapResultToTeam(results);
            userTeams.add(team);
        }
        return userTeams;
    }

}