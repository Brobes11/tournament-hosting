package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcRequestDAO;
import com.techelevator.model.JdbcTeamDao;
import com.techelevator.model.JdbcTournamentTeamDao;
import com.techelevator.model.Request;
import com.techelevator.model.Team;
import com.techelevator.model.TournamentTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private JdbcTournamentTeamDao tournamentTeamDao;

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
    public List<TournamentTeam> getTeamsByTournamentId(@PathVariable long tournamentId) {
        List<TournamentTeam> tourneyTeams = tournamentTeamDao.getTournamentRosterById(tournamentId);

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
    public List<Request> getAllRequestsByTeam(@Valid @RequestParam long teamId) {
        return requestDAO.getRequestsByTeamId(teamId);
    }

    @DeleteMapping("/request")
    public boolean deleteTeamRequest(@RequestBody Request request, BindingResult result) {
        if (result.hasErrors()) {
            return false;
        }

        requestDAO.deleteTeamRequest(request);
        return true;
    }

    @PostMapping("/roster")
    public void addTeamMember(@RequestParam long userId, @RequestParam long teamId,
            @RequestParam boolean captainStatus) {
        teamDao.addMember(userId, teamId, captainStatus);
    }

    @DeleteMapping("/roster")
    public void deleteTeamMember(@RequestParam long userId, @RequestParam long teamId) {
        teamDao.deleteMember(userId, teamId);
    }

    @GetMapping("/captain-teams")
    public List<Team> getTeamsByGameandCaptain(@RequestParam String game, @RequestParam Long userId) {
        return teamDao.getTeamsForCaptain(game, userId);
    }

}