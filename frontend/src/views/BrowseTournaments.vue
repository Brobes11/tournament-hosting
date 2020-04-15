<template>
  <v-container>
    <v-card-title large flat>Browse Tournaments</v-card-title>

    <v-container>
      <v-row>
        <v-col cols="1" class="d-flex justify-start align-center">
          <p>Filter by:</p>
        </v-col>

        <v-col xs="3" md="3">
          <v-overflow-btn
            color="#03DAC5"
            :items="dropdown_games"
            label="Choose Sport"
            v-model="gamefilter"
            target="#dropdown-example"
          ></v-overflow-btn>
        </v-col>
      </v-row>
      <p>Show Closed Tournaments?</p>
      <v-switch
        color="#03DAC5"
        inset
        v-model="show_closed_tournaments"
        :label="show_closed_tournaments? 'True':'False'"
      ></v-switch>
    </v-container>

    <v-data-table :headers="headers" :items="sortTournaments" flat>
      <template v-slot:item="row">
        <tr>
          <td
            class="clickable"
            @click="$router.push('/tournament-page/' + row.item.tournamentId)"
          ><v-btn small outlined block>{{row.item.tournamentName}}</v-btn></td>
          <td>{{row.item.game}}</td>
          <td>{{row.item.startDate}}</td>
          <td>{{row.item.endDate}}</td>
          <td>{{row.item.prizeDescription}}</td>
          <td>
            <join-tournament
              v-if="row.item.acceptingEntries===true"
              :game="row.item.game"
              :tournamentId="row.item.tournamentId"
              @join-success="handleSnack()"
              @duplicate-request="duplicateRequestSnack()"
            ></join-tournament>
            <v-row justify="center">
              <v-btn
                class="mxauto"
                v-if="row.item.acceptingEntries===false"
                disabled
                color="primary"
              >
                <v-icon dark>mdi-send</v-icon>Join
              </v-btn>
            </v-row>
          </td>
        </tr>
      </template>
    </v-data-table>

    <v-snackbar v-model="snackbar">
      {{ snackText }}
      <v-btn color="#03DAC5" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import auth from "@/auth.js";
import JoinTournament from "@/components/JoinTournament.vue";

export default {
  components: {
    JoinTournament
  },
  data() {
    return {
      gamefilter: "All",
      dropdown_games: [
        "All",
        "Volleyball",
        "Basketball",
        "Soccer",
        "Hockey",
        "Bike Polo",
        "Euchre",
        "Poker",
        "DnD",
        "Magic The Gathering",
        "Super Smash Brothers",
        "Other"
      ],

      show_closed_tournaments: true,
      headers: [
        {
          text: "Tournament",
          align: "start",
          sortable: true,
          value: "name"
        },
        { text: "Game", value: "game" },
        { text: "Start Date", value: "date" },
        { text: "Tournament Prize", value: "prize" },
        { text: "", value: "join" }
      ],
      tournaments: [
        {
          tournamentName: "",
          game: "",
          tournamentBio: ""
        }
      ],
      captainsTeams: [
        {
          teamName: "",
          teamBio: "",
          game: ""
        }
      ],
      userId: "",
      user: null,
      snackbar: false,
      snackText: "Your request has been submitted!"
    };
  },

  computed: {
    sortTournaments() {
      if (this.gamefilter === "All" && this.show_closed_tournaments) {
        return this.tournaments;
      } else if (this.gamefilter !== "All" && this.show_closed_tournaments) {
        return this.tournaments.filter(team => team.game === this.gamefilter);
      } else if (this.gamefilter === "All" && !this.show_closed_tournaments) {
        return this.tournaments.filter(team => team.acceptingEntries === true);
      } else {
        return this.tournaments.filter(team => {
          return (
            team.acceptingEntries === true && team.game === this.gamefilter
          );
        });
      }
    }
  },
  created() {
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament`, {
      method: "GET",
      headers: new Headers({
        Authorization: "Bearer " + auth.getToken()
      }),
      credentials: "same-origin"
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        }
      })
      .then(data => {
        this.tournaments = data;
      })
      .catch(err => console.error(err));
  },
  methods: {
    handleSnack() {
      this.snackText = "Thank you, your request has been submitted!";
      this.snackbar = true;
    },
    duplicateRequestSnack() {
      this.snackText = "You must join with a compatible team, or you already have a pending request";
      this.snackbar = true;
    }
  }
};
</script>

<style>
.elevation-5 {
  width: 15em;
}
</style>