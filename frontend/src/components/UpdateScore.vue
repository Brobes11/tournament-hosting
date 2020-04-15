<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat text v-on="on">
          <v-icon small>mdi-cog</v-icon>Update Score
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Update Match Up Scores</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="matchup.homeScore" label="Home Team Score" required />
                <v-text-field v-model="matchup.awayScore" label="Away Team Score" required />
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false; resetTournament()">Close</v-btn>
          <v-btn color="blue darken-1" text @click="updateTournament()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import auth from "@/auth";
export default {
  props: {
    currentMatchup: Object
  },
  data() {
    return {
      matchup: null
    };
  },
  methods: {
    updateMatchupScores() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament/scores`, {
        method: "POST",
        headers: {
          Authorization: "Bearer " + auth.getToken(),
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.matchup)
      }).then(response => {
        if (response.ok) {
          this.$emit("update-scores");
          this.dialog = false;
        }
      });
    }
  }
};
</script>

<style>
</style>