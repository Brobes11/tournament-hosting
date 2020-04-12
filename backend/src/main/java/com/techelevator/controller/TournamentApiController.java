package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcRequestDAO;
import com.techelevator.model.JdbcTournamentDao;
import com.techelevator.model.Request;
import com.techelevator.model.Tournament;

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
@RequestMapping("/api/tournament")
public class TournamentApiController {

    @Autowired
    private JdbcTournamentDao tournamentDao;

    @Autowired
    private JdbcRequestDAO requestDao;

    @Autowired
    public TournamentApiController(JdbcTournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    @GetMapping
    public List<Tournament> getAllTournaments(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long teamId) {
        if (userId != null) {
            return tournamentDao.getTournamentsByUser(userId);
        } else if (teamId != null) {
            return tournamentDao.getTournamentsByTeam(teamId);
        }
        return tournamentDao.getAllTournaments();
    }

    @GetMapping("/{tournamentId}")
    public Tournament getTournamentById(@PathVariable long tournamentId) {
        Tournament tournament = tournamentDao.getTournamentById(tournamentId);
        return tournament;
    }

    @PostMapping
    public Tournament createTournament(@Valid @RequestBody Tournament tournament, BindingResult result,
            @RequestParam Long userId) {
        if (result.hasErrors() == false) {
            return tournamentDao.createTournament(tournament, userId);
        }
        return null;
    }

    @PutMapping
    public boolean updateTournament(@Valid @RequestBody Tournament tournament) {
        return tournamentDao.updateTournament(tournament);

    }

    @GetMapping("/request")
    public List<Request> getTournamentRequests(@RequestParam long tournamentId) {
        return requestDao.getRequestsByTournamentId(tournamentId);
    }

    @DeleteMapping("/request")
    public void deleteTournamentRequest(@Valid @RequestBody Request tourneyRequest, BindingResult result) {
        if (result.hasErrors()) {

        }
        requestDao.deleteTourneyRequest(tourneyRequest);
    }

    @PostMapping("/request")
    public void acceptTournamentRequest(@Valid @RequestBody Request tourneyRequest, BindingResult result) {
        if (result.hasErrors()) {

        }
        requestDao.acceptTourneyRequest(tourneyRequest);
    }

    @PostMapping("/join-request")
    public void joinTournamentRequest(@Valid @RequestBody Request request, BindingResult result) {
        if (result.hasErrors()) {

        }
        requestDao.createTournamentRequest(request);
    }

}