package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcTeamDao;
import com.techelevator.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.PathVariable;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api/team")
public class TeamApiController {

    @Autowired
    private JdbcTeamDao teamDao;

    @Autowired
    public TeamApiController(JdbcTeamDao teamDao){
        this.teamDao = teamDao;
    }

    @GetMapping("/browse-teams")
    public List<Team> getAllTeams(){
        return teamDao.getAllTeams();
    }

<<<<<<< Updated upstream
    @GetMapping("/user-teams/{id}")
    public List<Team> getAllUsersTeams(@PathVariable long id){
        return teamDao.getTeamsByUser(id);
    }

=======
    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team, BindingResult result){
        
        if (result.hasErrors()==false) {
         return teamDao.createTeam(team);
               
            }
        return null;
    }
>>>>>>> Stashed changes
}