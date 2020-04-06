<template>
  <v-app-bar app color="primary" dark>
    <div class="d-flex align-center">
      <v-img
        alt="Vuetify Logo"
        class="shrink mr-2"
        contain
        src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
        transition="scale-transition"
        width="40"
      />

      <h1>Tournament Buddy</h1>
    </div>

    <v-spacer></v-spacer>

    <router-link to="/browse"><v-btn target="_blank" text v-if="!loggedIn">Browse</v-btn></router-link>
    <router-link to="/login"><v-btn target="_blank" text v-if="!loggedIn">Login</v-btn></router-link>
    <router-link to="/register"><v-btn target="_blank" text v-if="!loggedIn">Register</v-btn></router-link>

  <h3 v-if="loggedIn" @click="$router.push('/user-info')" >Hi {{userName}}!</h3>
    <v-menu offset-y v-if="loggedIn">
      <template v-slot:activator="{ on }">
        <v-btn target="_blank" text v-on="on">Teams</v-btn>
      </template>
      <v-list>
        <v-list-item>
          <h4>Join Team</h4>
        </v-list-item>
        <v-list-item>
          <h4>Create Team</h4>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item v-for="(item, index) in userTeams" :key="index">
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <v-menu offset-y v-if="loggedIn">
      <template v-slot:activator="{ on }">
        <v-btn target="_blank" text v-on="on">Tournaments</v-btn>
      </template>
      <v-list>
           <v-list-item>
          <h4>Join Tournament</h4>
        </v-list-item>
        <v-list-item>
          <h4>Create Tournament</h4>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item v-for="(item, index) in userTournaments" :key="index">
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
export default {
  computed: {
    loggedIn() {
      return true;
    },
    userName() {
      return "User";
    },
    userTeams() {
      return [
        { title: "Cincinnati Reds" },
        { title: "Cincinnati Bearcats" },
        { title: "Cincinnati Bengals" },
        { title: "Cincinnati Royals" }
      ];
    },
    userTournaments() {
      return [
        { title: "World Series" },
        { title: "March Madness" },
        { title: "NFL Playoffs" },
        { title: "NBA Playoffs" }
      ];
    }
  }
};
</script>

<style scoped>
h3 {
  cursor: pointer;
}
</style>