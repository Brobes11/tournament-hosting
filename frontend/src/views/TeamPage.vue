<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="150" />
      </v-col>
      <v-col cols="3">
        <h1>{{team.teamName}}</h1>
        <h2>{{team.game}}</h2>
      </v-col>
      <v-col cols="6">
        <v-row>
          <v-col>
            <h3>team bio:</h3>
          </v-col>
          <v-col>
            <v-card-actions>
              <edit-team :current-team="team"/>
          </v-card-actions>
          </v-col>
        </v-row>
        <p>{{team.teamBio}}</p>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card>
          <v-card-title>
            Pending Applications
            <v-spacer></v-spacer>
            <v-text-field
              v-model="searchApplicant"
              append-icon="mdi-magnify"
              label="Search Applicants"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table :headers="pendingHeaders" :items="applicants" :search="searchApplicant"></v-data-table>
        </v-card>
      </v-col>

      <v-col>
        <v-card>
          <v-card-title>
            Team Roster
            <v-spacer></v-spacer>
            <v-text-field
              v-model="searchRoster"
              append-icon="mdi-magnify"
              label="Search Team Roster"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table :headers="rosterHeaders" :items="roster" :search="searchRoster">
            <template v-slot:item.captainStatus="{ item }">
              <v-simple-checkbox v-model="item.captainStatus" disabled></v-simple-checkbox>
            </template>
            <template v-slot:item.delete="{ item }">
              <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import EditTeam from "@/components/EditTeam.vue"
import auth from '@/auth';

export default {
  components : {
    EditTeam
  },
  data() {
    return {
      searchApplicant: "",
      searchRoster: "",
      pendingHeaders: [
        { text: "Username", value: "userName" },
        { text: "First Name", value: "firstName" },
        { text: "Last Name", value: "lastName" },
        { text: "", value: "accept" }
      ],

      rosterHeaders: [
        { text: "Username", value: "userName" },
        { text: "First Name", value: "firstName" },
        { text: "Last Name", value: "lastName" },
        { text: "Email Address", value: "email" },
        { text: "Captain", value: "captainStatus" },
        { text: "", value: "delete" }
      ],

      applicants: [],

      roster: [],

      team:null
    };
  },

  created() {

    fetch(`${process.env.VUE_APP_REMOTE_API}/api/team/team-page`,  {
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + auth.getToken(),
      },
      credentials: 'same-origin',
    })
    .then((response) => {
        return response.json();
      })
    .then(teamFromApi => this.team = teamFromApi)

    this.initialize();
  },

  methods: {
    initialize() {
      this.applicants = [
        {
          userName: "test",
          firstName: "Ben",
          lastName: "Peters",
          accept: "accept"
        }
      ];

      this.roster = [
        {
           userName: "test",
          firstName: "Ben",
          lastName: "Peters",
          email: "ben@hotmail.com",
          captainStatus: true
        }
      ];
    },

    deleteItem(item) {
      const index = this.roster.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.roster.splice(index, 1);
    }
  }
};
</script>