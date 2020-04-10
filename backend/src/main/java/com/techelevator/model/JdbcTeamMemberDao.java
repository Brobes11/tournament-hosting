package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcTeamMemberDao implements TeamMemberDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTeamMemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addMember(TeamMember teamMember) {
        boolean result = false;
        String sql = "INSERT into teamRoster(user_id, team_id, captain) VALUES(?,?,?);";
        jdbcTemplate.update(sql, teamMember.getMemberId(), teamMember.getTeamId(), teamMember.isCaptainStatus());
        return result;
    }

    @Override
    public boolean deleteMember(TeamMember teamMember) {
        boolean result = false;
        String sql = "DELETE FROM teamRoster WHERE user_id = ? AND team_id = ?;";
        jdbcTemplate.update(sql, teamMember.getMemberId(), teamMember.getTeamId());
        return result;
    }
    

}