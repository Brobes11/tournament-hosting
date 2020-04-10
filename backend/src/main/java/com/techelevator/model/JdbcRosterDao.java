package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcRosterDao implements RosterDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRosterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addMember(long userId, long teamId) {
        boolean result = false;
        String sql = "INSERT into teamRoster(user_id, team_id) VALUES(?,?);";
        jdbcTemplate.update(sql, userId, teamId);
        return result;
    }

    @Override
    public boolean addCaptain(long userId, long teamId) {
        boolean result = false;
        boolean captain = true;
        String sql = "INSERT into teamRoster(user_id, team_id, captain) VALUES(?,?,?);";
        jdbcTemplate.update(sql, userId, teamId, captain);
        return result;
    }

    @Override
    public boolean deleteMember(long userId, long teamId) {
        boolean result = false;
        String sql = "INSERT into teamRoster(user_id, team_id) VALUES(?,?);";
        jdbcTemplate.update(sql, userId, teamId);
        return result;
    }

    

}