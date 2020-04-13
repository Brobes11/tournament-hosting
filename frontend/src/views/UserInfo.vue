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
            <edit-user :current-user="user" @update-user="updateUser()" />
          </v-card-actions>
        </v-list-item-content>
      </v-list-item>
    </v-card>
    <v-row class="mx-auto">
      <v-col>
        <v-data-table :headers="teamHeaders" :items="teams" :items-per-page="5" class="elevation-1">
          <template v-slot:item="row">
            <tr>
              <td
                class="clickable"
                @click="$router.push('/team-page/' + row.item.teamId)"
              >{{row.item.teamName}}</td>
              <td>{{row.item.game}}</td>
              <td>{{row.item.teamBio}}</td>
              <td>
                <v-icon v-if="captainedTeams.includes(row.item.teamId)">mdi-check-bold</v-icon>
              </td>
            </tr>
          </template>
        </v-data-table>
      </v-col>
      <v-col>
        <v-data-table
          :headers="tourneyHeaders"
          :items="tourneys"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item="row">
            <tr>
              <td
                class="clickable"
                @click="$router.push('/tournament-page/' + row.item.tournamentId)"
              >{{row.item.tournamentName}}</td>
              <td>{{row.item.game}}</td>
              <td>{{row.item.startDate}}</td>
            </tr>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import EditUser from "@/components/EditUser.vue";
import api from "@/api.js";

export default {
  components: {
    EditUser
  },
  data() {
    return {
      teamHeaders: [
        {
          text: "Teams",
          align: "start",
          value: "teamName",
          sortable: false
        },
        { text: "Game/Sport", value: "game", sortable: false },
        { text: "Team Bio", sortable: false, value: "teamBio" },
        { text: "Captain", sortable: false, value: "captain" }
      ],
      tourneyHeaders: [
        {
          text: "Tournament",
          align: "start",
          value: "name",
          sortable: false
        },
        { text: "Game/Sport", value: "game", sortable: false },
        { text: "Start Date", value: "start-date", sortable: false }
      ],
      user: null,
      teams: [
        {
          teamName: "",
          teamBio: "",
          game: ""
        }
      ],
      tourneys: [
        {
          tournamentName: "",
          game: "",
          startDate: ""
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
      ],
      captainedTeams: []
    };
  },
  methods: {
    updateUser() {
      api.getUser().then(result => (this.user = result));
    }
  },
  created() {
    this.updateUser();

    api.getCaptainedTeams().then(result => (this.captainedTeams = result));

    api.getUserTeams().then(result => (this.teams = result));

    api.getUserTournaments().then(result => (this.tourneys = result));
  }
};
</script>

<style>
</style>