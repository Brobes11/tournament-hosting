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
  </v-container>
</template>
 
 <script>
export default {
  data() {
    return {
      tournament: null
    };
  },
  methods: {
    getTournament() {
      fetch();
    }
  },
  created() {
    this.getTournament();
  }
};
</script>
 
 <style>
</style>