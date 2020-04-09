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
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private JdbcTeamRequestDAO teamRequestDAO;

    @Autowired
    public TeamApiController(JdbcTeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @GetMapping
    public List<Team> getAllTeams(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return teamDao.getAllTeams();
        }
        return teamDao.getTeamsByUser(userId);
    }

    @PutMapping
    public boolean updateUser(@Valid @RequestBody Team team, BindingResult result) {
        return teamDao.updateTeam(team);
    }

    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable long teamId) {
        Team team = teamDao.getTeamById(teamId);

        return team;
    }

    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team, BindingResult result) {

        if (result.hasErrors() == false) {
            return teamDao.createTeam(team);

        }
        return null;
    }

    @PostMapping("/join-request")
    public void joinTeamRequest(@RequestBody TeamRequest request) {
        teamRequestDAO.createTeamRequest(request);
    }

}