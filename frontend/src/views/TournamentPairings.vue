<template>
  <v-container>
      <h1>Hello</h1>

  </v-container>
</template>

<script>
import api from "@/api.js";


export default {
    data(){
        return{
            tournament: null,
            teams: null
        }
    },
    methods:{
        getTournament(){
            api.getTournament(this.$route.params.id)
            .then(results => this.tournament = results);
        },
        getTournamentTeams(){
            api.getTourneyTeams(this.$route.params.id)
            .then(results => this.teams = results);
        }
    },
    created(){
        this.getTournament();
        this.getTournamentTeams();
    }

}
</script>

<style>

</style>