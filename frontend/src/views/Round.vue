<template>
  <v-container>
    <v-row>
      <v-col class="d-flex" cols="12">
        <v-card width="400" class="matchup mt-5 at-5" v-for="matchup in matchups" :key="matchup">
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
          <v-card-actions v-if="tournament.tournamentOwner === currentUser">
            <v-spacer></v-spacer>
            <update-score :current-matchup="matchup" @update-scores="getMatchUps()" />
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import auth from "@/auth";
import UpdateScore from "@/components/UpdateScore.vue";
export default {
  components: {
    UpdateScore
  },
  data() {
    return {
      tournament: null,
      currentUser: null,
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
    },
    getTournament() {
      const tourneyId = this.$route.params.id;
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament/${tourneyId}`, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          }
        })
        .then(data => {
          this.tournament = data;
        });
    }
  },
  created() {
    this.getMatchUps();
    this.getTournament();
    this.currentUser = auth.getUser().id;
  }
};
</script>

<style scoped>
.matchup {
  margin-right: 10px;
  margin-left: 10px;
}
</style>