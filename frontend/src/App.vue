<template>
  <v-app>
    <nav-bar :key="loggedIn()" />
    <v-content>
      <router-view />
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
  methods: {
    loggedIn() {
      let user = auth.getUser();
      if (user === null) {
        return false;
      }
      return true;
    }
  },
  data: () => ({
    //
  })
};
</script>
