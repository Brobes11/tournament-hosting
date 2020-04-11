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
              <edit-team :current-team="team" @update-team="getTeam()" />
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
          <v-data-table :headers="pendingHeaders" :items="applicants" :search="searchApplicant">
            <template v-slot:item="row">
              <tr>
                <td>{{row.item.username}}</td>
                <td>{{row.item.firstName}}</td>
                <td>{{row.item.lastName}}</td>
              </tr>
            </template>
          </v-data-table>
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
            <template v-slot:item="row">
              <tr>
                <td>{{row.item.username}}</td>
                <td>{{row.item.firstName}}</td>
                <td>{{row.item.lastName}}</td>
                <td>{{row.item.email}}</td>
                <td>{{row.item.role}}</td>
              </tr>
            </template>
            <template v-slot item.delete="{ item }">
                  <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import EditTeam from "@/components/EditTeam.vue";
import auth from "@/auth";

export default {
  components: {
    EditTeam
  },
  data() {
    return {
      team: null,
      searchApplicant: "",
      searchRoster: "",
      pendingHeaders: [
        { text: "Username", value: "username" },
        { text: "First Name", value: "firstName" },
        { text: "Last Name", value: "lastName" },
        { text: "", value: "accept" }
      ],

      rosterHeaders: [
        { text: "Username", value: "username" },
        { text: "First Name", value: "firstName" },
        { text: "Last Name", value: "lastName" },
        { text: "Email Address", value: "email" },
        { text: "Role", value: "role" },
        { text: "", value: "delete" }
      ],

      applicants: [
        {
          userName: "",
          firstName: "",
          lastName: ""
        }
      ],

      roster: [
        {
          userName: "",
          firstName: "",
          lastName: "",
          email: "",
          role: "",
          userId: ""
        }
      ]
    };
  },

  created() {
    this.getTeam();
  },

  methods: {
    getTeam() {
      const teamId = this.$route.params.id;
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/team/${teamId}`, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          return response.json();
        })
        .then(data => {
          this.team = data;
          this.getApplicants(data.teamId);
          this.getRoster(data.teamId);
        });
    },

    getApplicants(teamId) {
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/user?teamId=${teamId}&isRequest=true`,
        {
          method: "GET",
          headers: {
            Authorization: "Bearer " + auth.getToken()
          },
          credentials: "same-origin"
        }
      )
        .then(response => {
          return response.json();
        })
        .then(data => (this.applicants = data));
    },

    getRoster(teamId) {
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/user?teamId=${teamId}&isRequest=false`,
        {
          method: "GET",
          headers: {
            Authorization: "Bearer " + auth.getToken()
          },
          credentials: "same-origin"
        }
      )
        .then(response => {
          return response.json();
        })
        .then(members => (this.roster = members));
    },

    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/team/roster?userId=${item.userId}&teamId=${team.teamId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: "Bearer " + auth.getToken()
          },
          credentials: "same-origin"
        }
      )
        .then(response => {
          return response.json();
        });
    }
  }
};
</script>