 <template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{tournament.tournamentName}}</h1>
        <span>Organizer: {{tournamentOwner.username}}</span>
        <p>Location: {{tournament.location}}</p>
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
        <v-row>
          <v-col>
            <h3>Tournament Entry Fee:</h3>
            <p>${{tournament.entryFee}}</p>
          </v-col>
          <v-col>
            <h3>Tournament Timeline:</h3>
            <span>From: {{tournament.startDate}}</span>
            <p>To: {{tournament.endDate}}</p>
          </v-col>
        </v-row>
      </v-col>
      <v-col v-if="tournament.tournamentOwner === currentUser">
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="#03DAC5"
            v-if="tournament.tournamentOwner === currentUser  && !tournament.completed"
            :to="{ name: 'tournament-pairings', params:{id: tournament.tournamentId, round: rounds.length + 1}}"
          >Make New Round</v-btn>
          <v-spacer></v-spacer>
          <v-btn
            color="#03DAC5"
            v-if="tournament.tournamentOwner === currentUser  && !tournament.completed"
            @click="endTournament()"
          >End Tournament</v-btn>
          <v-spacer></v-spacer>
          <edit-tournament
            :current-tournament="tournament"
            @update-tournament="getTournament()"
            v-if="tournament.tournamentOwner === currentUser  && !tournament.completed"
          />
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-col>
    </v-row>

    <v-row v-if="!tournament.completed">
      <v-col>
        <v-card>
          <v-card-title>
            Tournament Teams
            <v-spacer></v-spacer>
            <accept-entries
              v-if="tournament.tournamentOwner === currentUser"
              :currentTourney="tournament"
            />
            <v-text-field
              v-model="searchTeams"
              append-icon="mdi-magnify"
              label="Search Tournament Teams"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table :headers="tourneyTeamHeaders" :items="tourneyTeams" :search="searchTeams">
            <template v-slot:item="row">
              <tr>
                <td
                  class="clickable"
                  @click="$router.push('/team-page/' + row.item.teamId)"
                >{{row.item.teamName}}</td>
                <td>{{row.item.captainUsername}}</td>
                <td>{{row.item.captainEmail}}</td>
                <td align="right" width="10">
                  <v-btn
                    v-if="tournament.tournamentOwner === currentUser && tournamentStarted === false"
                    class="error"
                    small
                    @click="deleteTeamFromTournament(row.item.teamId)"
                  >Remove Team</v-btn>
                </td>
              </tr>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
    <v-row v-if="tournament.completed">
      <v-col class="text-center">
        <h1>WINNER: {{top3[1]}}</h1>
        <h2>Second: {{top3[2]}}</h2>
        <h3>Third: {{top3[3]}}</h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="d-flex" cols="12">
        <v-spacer></v-spacer>
        <v-card class="round" v-for="currentRound in rounds" :key="currentRound">
          <v-card-actions>
            <v-btn
              :to="{name: 'round', params: {id : tournament.tournamentId, roundNumber : currentRound}}"
              color="#03DAC5"
            >Round {{currentRound}} Details</v-btn>
          </v-card-actions>
        </v-card>
        <v-spacer></v-spacer>
      </v-col>
    </v-row>
  </v-container>
</template>
 
 <script>
import auth from "@/auth";
import EditTournament from "@/components/EditTournament.vue";
import AcceptEntries from "@/components/AcceptEntries.vue";

export default {
  components: {
    EditTournament,
    AcceptEntries
  },
  data() {
    return {
      tournament: null,
      currentUser: null,
      searchTeams: "",
      tourneyTeamHeaders: [
        { text: "Team Name", value: "teamName" },
        { text: "Captain", value: "username" },
        { text: "Captain Email", value: "email" },
        { text: "", value: "button" }
      ],
      tourneyTeams: {
        teamId: "",
        teamName: "",
        captainUsername: "",
        captainEmail: ""
      },
      tournamentOwner: {
        username: ""
      },
      rounds: [],
      top3: []
    };
  },
  methods: {
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
    },
    getTournamentOwnerUsername() {
      const tourneyId = this.$route.params.id;
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/user/tournamentOwner/${tourneyId}`,
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
        .then(data => (this.tournamentOwner = data));
    },
    getTourneyTeams() {
      const tourneyId = this.$route.params.id;
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/team/tournament/${tourneyId}`,
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
        .then(teamsInfo => (this.tourneyTeams = teamsInfo));
    },
    deleteTeamFromTournament(teamId) {
      const tournamentId = this.$route.params.id;
      confirm(
        "Are you sure you want to remove this team from your tournament?"
      ) &&
        fetch(
          `${process.env.VUE_APP_REMOTE_API}/api/team/tournament/${tournamentId}?teamId=${teamId}`,
          {
            method: "DELETE",
            headers: {
              Authorization: "Bearer " + auth.getToken()
            }
          }
        ).then(response => {
          if (response.ok) {
            return response.json();
          }
        });
      this.getTourneyTeams();
    },
    getTourneyRounds() {
      const tournamentId = this.$route.params.id;
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/tournament/tournamentRounds/${tournamentId}`,
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
        .then(data => (this.rounds = data));
    },
    endTournament() {
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/tournament/end?tourneyId=${this.tournament.tournamentId}`,
        {
          method: "PUT",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            Accept: "application/json",
            "content-type": "application/json"
          }
        }
      ).then(response => {
        if (response.ok) {
          this.getTourneyRounds();
          this.currentUser = auth.getUser().id;
          this.getTournamentOwnerUsername();
          this.getTourneyTeams();
          this.getTournament();
        }
      });
    },
    getTop3() {
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/tournament/wins?tourneyId=${this.$route.params.id}`,
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
        .then(data => (this.top3 = data));
    }
  },
  computed: {
    tournamentStarted() {
      var date1 = this.tournament.startDate;
      date1 = new Date(date1);
      var date2 = new Date();
      return date1 <= date2;
    }
  },
  created() {
    this.getTourneyRounds();
    this.getTourneyTeams();
    this.currentUser = auth.getUser().id;
    this.getTournamentOwnerUsername();
    this.getTournament();
    this.getTop3();
  }
};
</script>
 
 <style scoped>
.round {
  margin-right: 10px;
  margin-left: 10px;
}
</style>