package com.techelevator.controller;

import java.util.List;
import com.techelevator.model.JdbcTeamDao;
import com.techelevator.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private JdbcTeamDao teamDao;

    @Autowired
    public TeamController(JdbcTeamDao teamDao){
        this.teamDao = teamDao;
    }

    @GetMapping("/browse-teams")
    public List<Team> getAllTeams(){
        return teamDao.getAllTeams();
    }

}