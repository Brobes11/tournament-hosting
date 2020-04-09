<template>
  <v-app>
    <nav-bar :user="currentUser" :teams="currentTeams" @update-user="currentUser = getUser()"/>
    <v-content>
      <router-view @update-user="currentUser = getUser(); getUserTeams()"/>
    </v-content>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import auth from "@/auth";

export default {
  name: "App",
  components: {
    NavBar
  },
  data(){
    return{
      currentUser: this.getUser(),
      currentTeams: this.getUserTeams()
    }
  },
  methods: {
    getUser() {
      return auth.getUser();
    },
    getUserTeams(){
       fetch(`${process.env.VUE_APP_REMOTE_API}/api/team?userId=${auth.getUser().id}`,  {
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + auth.getToken(),
      },
      credentials: 'same-origin',
    })
    .then((response) => {
        return response.json();
      })
    .then(teamsFromApi => this.currentTeams = teamsFromApi)
    }
  }
};
</script>
