<template>
  <v-container>
    <v-row>
      <v-col class="d-flex" cols="12">
        <v-card
          width="400"
          id="login"
          class="mx-auto mt-5 at-5"
          v-for="matchup in matchups"
          :key="matchup"
        >
          <v-card-title>
            <h3 class="h1 mb-3 font-weight-normal">Match Up #{{matchup.id}}</h3>
          </v-card-title>
          <v-card-text>
            <v-card>
              <p>
                Home Team:
                <span>{{matchup.homeTeam.teamName}}</span>
              </p>
              <p>
                Home Score:
                <span>{{matchup.homeScore}}</span>
              </p>
            </v-card>
            <v-card>
              <p>
                Away Team:
                <span>{{matchup.awayTeam.teamName}}</span>
              </p>
              <p>
                Away Score:
                <span>{{matchup.awayScore}}</span>
              </p>
            </v-card>
            <v-divider></v-divider>
            <p>Winner: {{matchup.Winner}}</p>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn>Update Score</v-btn>
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import auth from "@/auth";
export default {
  data() {
    return {
      tournament: null,
      tournamentMatch: {},
      matchups: []
    };
  },
  methods: {
    getMatchUps() {
      let tourneyId = this.$route.params.id;
      let roundNumber = this.$route.params.roundNumber;
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/tournament/matchups?tournamentId=${tourneyId}&round=${roundNumber}`,
        {
          method: "GET",
          headers: {
            Authorization: "Bearer " + auth.getToken()
          }
        }
      )
        .then(response => {
          if (response.ok) {
            return response.json();
          }
        })
        .then(data => {
          this.matchups = data;
        });
    }
  },
  created() {
    this.getMatchUps();
    this.currentUser = auth.getUser().id;
  }
};
</script>

<style scoped>
</style>