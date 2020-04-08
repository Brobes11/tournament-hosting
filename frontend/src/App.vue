<template>
  <v-app>
    <nav-bar :user="currentUser" @update-user="currentUser = getUser()"/>
    <v-content>
      <router-view @update-user="currentUser = getUser()"/>
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
      currentUser: this.getUser()
    }
  },
  methods: {
    getUser() {
      return auth.getUser();
    }
  }
};
</script>
