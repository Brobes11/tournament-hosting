<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{tournament.tournamentName}}</h1>
      </v-col>
      <v-col cols="6">
        <v-row>
          <v-col>
            <h3>Tournament Game/Sport:</h3>
            <p>{{tournament.game}}</p>
          </v-col>
          <v-col>
            <h3>Tournament Prize Description:</h3>
            <p>{{tournament.prizeDescription}}</p>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col class="d-flex" cols="16" sm="3">
        <v-select :items="matchups" label="Matchup" outlined item-text></v-select>
      </v-col>
      <v-col class="d-flex" cols="16" sm="3">
        <v-select
          :items="teams"
          label="Home Team"
          item-text="teamName"
          return-object="true"
          v-model="homeTeam"
          outlined
        ></v-select>
      </v-col>
      <v-col class="d-flex" cols="16" sm="3">
        <v-select
          :items="teams"
          label="Away Team"
          item-text="teamName"
          return-object="true"
          v-model="awayTeam"
          outlined
        ></v-select>
      </v-col>
      <v-col class="d-flex" cols="16" sm="3">
        <v-btn>Add Matchup</v-btn>
      </v-col>
    </v-row>
    <v-row justify="center">
      <p v-if="homeTeam === awayTeam && homeTeam !== null">Can't choose same team for Home and Away</p>
    </v-row>
  </v-container>
</template>

<script>
import api from "@/api.js";

export default {
  data() {
    return {
      tournament: null,
      teams: null,
      matchups: null,
      homeTeam: null,
      awayTeam: null,
      selectedMatchup: null,
      round: 1
    };
  },
  methods: {
    getTournament() {
      api
        .getTournament(this.$route.params.id)
        .then(results => (this.tournament = results));
    },
    getTournamentTeams() {
      api.getTourneyTeams(this.$route.params.id).then(results => {
        this.teams = results;
        this.numOfMatchups(results);
      });
    },
    numOfMatchups(teams) {
      let numOfMatchups = Math.ceil(teams.length / 2);
      let emptyMatchups = [];

      if (this.teams.length % 2 === 0) {
        for (let index = 0; index < numOfMatchups; index++) {
          emptyMatchups.push("Matchup " + (index + 1));
        }
      } else {
        for (let index = 1; index < numOfMatchups; index++) {
          emptyMatchups.push("Matchup " + index);
        }
        emptyMatchups.push("BYE");
      }

      this.matchups = emptyMatchups;
    }
  },
  created() {
    this.getTournament();
    this.getTournamentTeams();
  }
};
</script>

<style>
</style>