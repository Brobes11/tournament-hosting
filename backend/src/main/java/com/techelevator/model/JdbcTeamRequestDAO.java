package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcTeamRequestDAO implements TeamRequestDAO {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTeamRequestDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public TeamRequest createTeamRequest(TeamRequest request) {
        String sql = "INSERT into teamrequest(user_id, team_id, message) VALUES(?,?,?);" ;
        jdbcTemplate.update(sql, request.getUserId(), request.getTeamId(), request.getMessage() );
        return null;
    }

}