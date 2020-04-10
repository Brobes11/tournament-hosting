<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Team Requests</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Sign Up Requests</span>
          <v-spacer></v-spacer>
          <v-icon class="clickable" @click="dialog = false">mdi-close-box-outline</v-icon>
        </v-card-title>
        <v-data-table :headers="headers" :items="requests" class="elevation-10">
          <template v-slot:item="row">
            <tr>
              <td>{{row.item.teamName}}</td>
              <td>{{row.item.teamBio}}</td>
              <td>
                <v-spacer></v-spacer>
                <v-btn class="mx-2" fab dark small color="green">
                  <v-icon dark>mdi-check-circle-outline</v-icon>
                </v-btn>
                <v-btn class="mx-2" fab dark small color="red">
                  <v-icon dark>mdi-minus-circle-outline</v-icon>
                </v-btn>
              </td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import auth from '@/auth.js';
export default {
  props:{
        currentTourney: Object
    },
  data() {
    return {
      tourney: null,
      dialog: false,
      requests: [
        {
          teamName: "team team",
          message: "Please let me play"
        }
      ]
    };
  },
  methods:{
    setTournament(){
      this.tourney = Object.assign({}, this.currentTourney);
    },
    getTournamentRequests(){
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament/request`, {
        method: 'GET',
        headers:{
        Authorization: 'Bearer ' + auth.getToken(),
        Accept: "application/json",
           'content-type': 'application/json'
        },
        body: JSON.stringify(this.currentTourney)
        })
        .then((response) => {
        if (response.ok) {
          return response.json();
        } 
        })
        .then((data) => {
          this.requests = data;
        })
        .catch((err) => console.error(err));
    }
  },
  created(){
    this.setTournament();
    this.getTournamentRequests();
    
  }
};
</script>

<style>

</style>