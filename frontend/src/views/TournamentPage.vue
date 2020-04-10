 <template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{tournament.tournamentName}}</h1>
        <h2>GAME GOES HERE</h2>
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
        <p>PRIZE DESCRIPTION GOES HERE</p>
      </v-col>
    </v-row>
  </v-container>
</template>
 
 <script>
import auth from "@/auth";
export default {
  data() {
    return {
      tournament: null
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
    }
  },
  created() {
    this.getTournament();
  }
};
</script>
 
 <style>
</style>