package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.model.JdbcTournamentDao;
import com.techelevator.model.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Jdbc;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/api/tournament")
public class TournamentApiController {

    @Autowired
    private JdbcTournamentDao tournamentDao;

    @Autowired
    public TournamentApiController(JdbcTournamentDao tournamentDao){
        this.tournamentDao = tournamentDao;
    }

    @GetMapping
    public List<Tournament> getAllTournaments(@RequestParam(required = false) Long userId ){
        if(userId ==null){
            return tournamentDao.getAllTournaments();
        }
        return tournamentDao.getTournamentsByUser(userId);
    }

    @GetMapping("/{tournamentId}")
    public Tournament getTournamentById(@PathVariable long tournamentId){
        Tournament tournament = tournamentDao.getTournamentById(tournamentId);
        return tournament;
    }

    @PostMapping
    public Tournament createTournament(@Valid @RequestBody Tournament tournament, BindingResult result, @RequestParam Long userId) {
        if (result.hasErrors() == false) {
            return tournamentDao.createTournament(tournament, userId);
        }
        return null;
    }



}