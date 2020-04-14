<template>
  <v-container grid-list-xl>
    <v-app-bar prominent flat src="https://i.picsum.photos/id/353/6016/3376.jpg" collapse-on-scroll >
      <v-row>
        <!-- nav bar- row tag start -->

        <v-col md="6" cols="12" class="pa-1">
          <div class="d-flex align-center">
            <v-img
              alt="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
              class="shrink mr-2"
              contain
              src="@/assets/tournLogo.png"
              transition="scale-transition"
              width="50"
            />
            <h1>Tournament Buddy</h1>
          </div>
        </v-col>

        <v-row v-if="user === null" class="d-flex justify-center align-end pa-1" dense align-end>
          <router-link to="/browse" class="d-flex justify-center align-end pa-1">
            <v-col cols="4" class="d-flex justify-center">
              <v-btn target="_blank" text v-if="user === null">
                <v-icon>mdi-magnify</v-icon>Browse
              </v-btn>
            </v-col>
          </router-link>
          <router-link to="/login" class="d-flex justify-center align-end pa-1">
            <v-col cols="4" class="d-flex justify-center">
              <v-btn target="_blank" text v-if="user === null">
                <v-icon>mdi-account</v-icon>Login
              </v-btn>
            </v-col>
          </router-link>
          <router-link to="/register" class="d-flex justify-center align-end ">
            <v-col cols="4"  class="d-flex justify-center">
              <v-btn target="_blank" text v-if="user === null">
                <v-icon>mdi-new-box</v-icon>Register
              </v-btn>
            </v-col>
          </router-link>
        </v-row>

        <v-row v-if="user !== null" class="d-flex justify-center align-end pa-1" dense align-end>
          <v-menu offset-y v-if="user !== null">
            <template v-slot:activator="{ on }">
              <v-col cols="4"  class="d-flex justify-center">
                <v-btn target="_blank" text v-on="on">
                  <v-icon>mdi-account-group</v-icon>Teams
                </v-btn>
              </v-col>
            </template>
            <v-list>
              <v-list-item>
                <h4 @click="$router.push('/browse-teams')" class="clickable">Join Team</h4>
              </v-list-item>
              <v-list-item>
                <h4 @click="$router.push('/create-team')" class="clickable">Create Team</h4>
              </v-list-item>
              <v-divider></v-divider>
              <v-list-item v-for="(item, index) in teams" :key="index">
                <v-list-item-title
                  class="clickable"
                  @click="$router.push('/team-page/' + item.teamId)"
                >{{ item.teamName }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu offset-y v-if="user !== null">
            <template v-slot:activator="{ on }">
              <v-col cols="4"  class="d-flex justify-center">
                <v-btn target="_blank" text v-on="on">
                  <v-icon>mdi-trophy</v-icon>Tournaments
                </v-btn>
              </v-col>
            </template>
            <v-list>
              <v-list-item>
                <h4 class="clickable" @click="$router.push('/browse-tournaments')">Join Tournament</h4>
              </v-list-item>
              <v-list-item>
                <h4 class="clickable" @click="$router.push('/create-tournament')">Create Tournament</h4>
              </v-list-item>
              <v-divider></v-divider>
              <v-list-item v-for="(item, index) in tournaments" :key="index">
                <v-list-item-title
                  class="clickable"
                  @click="$router.push('/tournament-page/' + item.tournamentId)"
                >{{ item.tournamentName }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu offset-y v-if="user !== null">
            <template v-slot:activator="{ on }">
              <v-col cols="4"  class="d-flex justify-center">
                <v-btn target="_blank" text v-on="on">
                  <v-icon>mdi-account-circle</v-icon>
                  Hi {{user.sub}}!
                </v-btn>
              </v-col>
            </template>
            <v-list>
              <v-list-item>
                <h4 @click="$router.push('/user-info')" class="clickable">User Info</h4>
              </v-list-item>
              <v-list-item>
                <h4 @click="logout" class="clickable">Logout</h4>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-row>
      </v-row>
    </v-app-bar>
  </v-container>
</template>

<script>
import auth from "@/auth";

export default {
  props: {
    user: Object,
    teams: Array,
    tournaments: Array
  },
  data() {
    return {};
  },

  methods: {
    logout() {
      auth.logout();
      this.$router.push("/login");
      this.$emit("update-user");
    }
  }
};
</script>

<style scoped>
.clickable {
  cursor: pointer;
}
h1 {
  font-size: 2.5vw;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
}
</style>