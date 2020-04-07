package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public Team saveTeam(String teamName, String game, boolean acceptingNewMembers, String teamBio) {

        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO teams (team_name, game, accepting_members, team_bio) VALUES (?, ?, ?, ?) RETURNING id",
                Long.class, teamName, game, acceptingNewMembers, teamBio);

        Team newTeam = new Team();
        newTeam.setTeamId(newId);
        newTeam.setTeamName(teamName);
        newTeam.setGame(game);
        newTeam.setAcceptingNewMembers(acceptingNewMembers);
        newTeam.setTeamBio(teamBio);
        return newTeam;
    }

}