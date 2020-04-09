package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcRequestDAO implements RequestDAO {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRequestDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Request createTeamRequest(Request request) {
        String sql = "INSERT into teamrequest(user_id, team_id, message) VALUES(?,?,?);" ;
        jdbcTemplate.update(sql, request.getSenderId(), request.getRecipientId(), request.getMessage() );
        return null;
    }

}