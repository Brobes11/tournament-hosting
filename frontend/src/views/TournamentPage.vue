 <template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{tournament.tournamentName}}</h1>
        <h2>{{tournament.game}}</h2>
      </v-col>
      <v-col cols="6">
        <v-row>
          <v-col>
            <h3>Tournament Prize Description:</h3>
          </v-col>
          <v-col>
            <v-card-actions></v-card-actions>
          </v-col>
        </v-row>
        <p>{{tournament.prizeDescription}}</p>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-card>
          <v-card-title>
            Tournament Teams
            <v-spacer></v-spacer>
            <accept-entries/>
            <v-text-field
              v-model="searchTeams"
              append-icon="mdi-magnify"
              label="Search Tournament Teams"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table :headers="tourneyTeamHeaders" :items="teams" :search="searchTeams">
            <template v-slot:item="row">
              <tr>
                <td>{{row.item.username}}</td>
                <td>{{row.item.firstName}}</td>
                <td>{{row.item.lastName}}</td>
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
import AcceptEntries from "@/components/AcceptEntries.vue"
export default {
  components:{
     AcceptEntries
    },
  data() {
    return {
      tournament: null,
      searchTeams: "",
      tourneyTeamHeaders: [
        { text: "Team Name", value: "teamName" },
        { text: "Captain", value: "username" },
        { text: "Captain Email", value: "email" }
      ]
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
    getTourneyTeams() {
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
        .then(tourneyTeams => (this.tourneyRoster = tourneyTeams));
    }
  },
  created() {
    this.getTournament();
  }
};
</script>
 
 <style>
</style>