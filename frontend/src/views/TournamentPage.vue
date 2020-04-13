 <template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{tournament.tournamentName}}</h1>
        <h2>{{tournament.game}}</h2>
        <p>Tourney Organizer: {{tournamentOwner.username}}</p>
      </v-col>
      <v-col cols="6">
        <v-row>
          <v-col>
            <h3>Tournament Prize Description:</h3>
            <p>{{tournament.prizeDescription}}</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <h3>Tournament Entry Fee:</h3>
            <p>$$$ {{tournament.entryFee}}</p>
          </v-col>
        </v-row>
      </v-col>
      <v-col>
        <v-card-actions>
          <edit-tournament :current-tournament="tournament" @update-tournament="getTournament()" />
        </v-card-actions>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-card dark>
          <v-card-title>
            Tournament Teams
            <v-spacer></v-spacer>
            <accept-entries :currentTourney="tournament" />
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
      }
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
    }
  },
  created() {
    this.getTournamentOwnerUsername();
    this.getTourneyTeams();
    this.getTournament();
  }
};
</script>
 
 <style>
</style>