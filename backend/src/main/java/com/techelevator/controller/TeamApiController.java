package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcTeamDao;
import com.techelevator.model.JdbcTeamRequestDAO;
import com.techelevator.model.Team;
import com.techelevator.model.TeamRequest;
import com.techelevator.model.TeamRequestDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api/team")
public class TeamApiController {

    @Autowired
    private JdbcTeamDao teamDao;
    private JdbcTeamRequestDAO teamRequestDAO;

    @Autowired
    public TeamApiController(JdbcTeamDao teamDao){
        this.teamDao = teamDao;
    }

    @GetMapping("/browse-teams")
    public List<Team> getAllTeams(){
        return teamDao.getAllTeams();
    }

    @GetMapping("/team-page")
    public Team getTeamById(@RequestParam long teamId){
        return teamDao.getTeamById(teamId);
    }
        

    @GetMapping("/user-teams/{id}")
    public List<Team> getAllUsersTeams(@PathVariable long id){
        return teamDao.getTeamsByUser(id);
    }


    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team, BindingResult result){
        
        if (result.hasErrors()==false) {
         return teamDao.createTeam(team);
               
            }
        return null;
    }

    @PostMapping("/join-request")
    public void joinTeamRequest(@RequestBody TeamRequest request){
        teamRequestDAO.createTeamRequest(request);
    }


}