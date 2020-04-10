package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcRequestDAO;
import com.techelevator.model.JdbcTeamDao;
import com.techelevator.model.Request;
import com.techelevator.model.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    private JdbcRequestDAO requestDAO;

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
    public boolean updateTeam(@Valid @RequestBody Team team, BindingResult result) {
        return teamDao.updateTeam(team);
    }

    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable long teamId) {
        Team team = teamDao.getTeamById(teamId);

        return team;
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<Team> getTeamsByTournamentId(@PathVariable long tournamentId, @RequestParam boolean isRequest) {
        List<Team> tourneyTeams = teamDao.getTeamsByTournamentId(tournamentId, isRequest);

        return tourneyTeams;
    }

    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team, BindingResult result, @RequestParam Long userId) {

        if (result.hasErrors() == false) {
            return teamDao.createTeam(team, userId);

        }
        return null;
    }

    @PostMapping("/join-request")
    public void joinTeamRequest(@RequestBody Request request) {
        requestDAO.createTeamRequest(request);
    }

    @GetMapping("/request")
    public List<Request> getAllRequestsByTeam(@Valid @RequestBody Team team, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        return requestDAO.getRequestsByTeamId(team.getTeamId());
    }

    @GetMapping("/captain-teams")
    public List<Team> getTeamsByGameandCaptain(@RequestBody String game, Long userId){
        return teamDao.getTeamsForCaptain(game, userId);
    }

}