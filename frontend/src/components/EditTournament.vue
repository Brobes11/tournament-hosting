<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat text v-on="on">
          <v-icon small>mdi-cog</v-icon>Edit Tournament
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Edit Tournament Info</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="tournament.tournamentName" label="Tournament Name" required />
                <v-text-field v-model="tournament.prizeDescription" label="Prize Description" />
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false; resetTeam()">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false; updateTeam()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import auth from "@/auth";
export default {
  props: {
    currentTournament: Object
  },
  data() {
    return {
      tournament: {
        tournamentName: "",
        prizeDescription: ""
      }
    };
  },
  methods: {
    resetTeam() {
      this.tournament = Object.assign({}, this.currentTournament);
    },
    updateTeam() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament`, {
        method: "PUT",
        headers: {
          Authorization: "Bearer " + auth.getToken(),
          Accept: "application/json",
          "content-type": "application/json"
        },
        body: JSON.stringify(this.tournament)
      }).then(response => {
        if (response.ok) {
          this.$emit("update-tournament");
        }
      });
    }
  },
  created() {
    this.resetTeam();
  }
};
</script>

<style>
</style>