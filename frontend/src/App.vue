<template>
  <v-app>
    <nav-bar :user="currentUser" :teams="currentTeams" :tournaments="currentTournaments" @update-user="currentUser = getUser(); getUserTeams(); getUserTournaments()"/>
    <v-content>
      <router-view @update-user="currentUser = getUser(); getUserTeams(); getUserTournaments()"/>
    </v-content>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import auth from "@/auth";
import api from "@/api.js";

export default {
  name: "App",
  components: {
    NavBar
  },
  data(){
    return{
      currentUser: this.getUser(),
      currentTeams: this.getUserTeams(),
      currentTournaments: this.getUserTournaments()
    }
  },
  methods: {
    getUser() {
      return auth.getUser();
    },
    getUserTeams(){
      if(auth.getUser() === null || auth.getUser() === undefined){
        this.currentTeams = [];
      }else{
       api.getUserTeams()
       .then(results => this.currentTeams = results)
      }
    },
     getUserTournaments(){
       if(auth.getUser() === null || auth.getUser() === undefined){
         this.currentTournaments = [];
       } else{
      api.getUserTournaments()
    .then(tourneysFromApi => this.currentTournaments = tourneysFromApi);
       }
    }
  }
};
</script>
<style>
.clickable {
  cursor: pointer;
}
</style>
