<template>
  <div>
    <v-card class="ml-3 mt-4" max-width="600">
      <v-list-item>
        <v-list-item-avatar tile size="150" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="headline mb-1">{{user.firstName}} {{user.lastName}}</v-list-item-title>
          <div class="overline">Username: {{user.username}}</div>
          <div class="overline">Email: {{user.email}}</div>
          <v-card-actions>
            <edit-user :current-user="user"/>
            <reset-password />
          </v-card-actions>
        </v-list-item-content>
      </v-list-item>
    </v-card>
    <v-row class="mx-auto">
      <v-col>
        <v-data-table :headers="teamHeaders" :items="teams" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
      <v-col>
        <v-data-table :headers="tourneyHeaders" :items="tourneys" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import ResetPassword from "@/components/ResetPassword.vue";
import EditUser from "@/components/EditUser.vue";
import auth from '@/auth.js'

export default {
  components: {
    ResetPassword,
    EditUser
  },
  data() {
    return {
      teamHeaders: [
        {
          text: "Teams",
          align: "start",
          value: "name",
          sortable: false
        },
        { text: "Game/Sport", value: "game", sortable: false },
        { text: "Team Bio", sortable: false, value: "bio" }
      ],
      tourneyHeaders: [
        {
          text: "Tournament",
          align: "start",
          value: "name",
          sortable: false
        },
        { text: "Game/Sport", value: "game", sortable: false },
        { text: "Tourney Info", value: "info", sortable: false }
      ],
      user: null,
      teams: [
        {
          name: "Blue Team",
          game: "Football",
          bio:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,"
        },
        {
          name: "Red Team",
          game: "Baseball",
          bio:
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
        },
        {
          name: "Green Team",
          bio:
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
          game: "Volleyball"
        },
        {
          name: "Yellow Team",
          bio:
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
          game: "Super Smash"
        }
      ],
      tourneys: [
        {
          name: "Blue Team",
          game: "Football",
          info:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,"
        },
        {
          name: "Red Team",
          game: "Baseball",
          info:
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
        },
        {
          name: "Green Team",
          info:
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
          game: "Volleyball"
        },
        {
          name: "Yellow Team",
          info:
            "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
          game: "Super Smash"
        }
      ]
    };
  },
  created(){
    let username = auth.getUser().sub;
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/user/${username}`,  {
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + auth.getToken(),
      },
      credentials: 'same-origin',
    })
    .then((response) => {
        return response.json();
      })
    .then(userFromApi => this.user = userFromApi)
  }
};
</script>

<style>
</style>