<template>
  <v-row justify="center">

    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on"><v-icon dark>mdi-send</v-icon> Join  </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <v-row>
            <v-col>
          <span class="headline">Join Tournament</span>
            </v-col>
          <v-col class="d-flex" cols="12" sm="6">
            <v-select 
              :items="userTeams" 
              item-text="teamName" 
              v-model="selectedTeam" 
              label="Team Joining:" 
              @change="getIdFromTeamObj();" 
              dense outlined></v-select>
          </v-col>
          </v-row>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-textarea outlined label="Message Tournament Host" v-model="request.message" required></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false;">Close</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false; sendJoinRequest()">Join</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import auth from '@/auth';

  export default {
    name:'JoinTournament',

    props:{
      tournamentId: Number,
      game: String
    },
      
    data: () => ({
      dialog: false,
      request: {
        senderId: '',
        recipientId: '',
        message: ''
      },
      userId:'',
      userTeams:[{
        teamId: '',
        teamName: '',
        game: '',
        teamBio: '',
        acceptingNewMembers: ''
      }],
      selectedTeam:'',
      selectedTeamObj:[],
    }),

      methods:{
      sendJoinRequest(){
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament/join-request`,{
         method:'POST',
         headers:{
            Authorization: 'Bearer ' + auth.getToken(),
            Accept: "application/json",
           'content-type': 'application/json'
         },
         body: JSON.stringify(this.request)
       })
       .then(response =>{
         if(response.ok){
           this.$emit('join-success');
         }
       })
      },

      getCaptainsTeamsByGame(){
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/team/captain-teams?game=${this.game}&userId=${this.userId}`, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken(),
        }
      })
        .then(response => {
          return response.json();
        })
        .then(captainsTeams => (this.userTeams = captainsTeams));
      },

      setTournament(){
        this.request.recipientId = this.tournamentId;
      },
      setTeam(){
        this.request.senderId = this.selectedTeamObj.teamId;
      },
      setUserId(){
        this.userId = auth.getUser().id;
      },
      getIdFromTeamObj(){
        this.selectedTeamObj = this.userTeams.find((team) => team.teamName === this.selectedTeam);
        this.setTeam();
      },
      

    },

    created(){
      this.setTournament();
      this.setTeam();
      this.setUserId();
      this.getCaptainsTeamsByGame();

    },

    computed:{
      getListOfUserGames(){
        return this.userTeams.map(a => a.teamName);
      },

    },




  };

</script>



<style>

</style>