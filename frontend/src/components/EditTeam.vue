<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat text v-on="on">
          <v-icon small>mdi-cog</v-icon>Edit Team
        </v-btn>
      </template>
      <v-card  ref="card" v-model="valid" lazy-validation>
        <v-card-title>
          <span class="headline">Edit Team Info</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-form v-model="isValid">
                <v-text-field v-model="team.teamName" label="Team Name" :rules="teamNameRules" error-count="2" required/>
                <v-text-field v-model="team.game" label="sport/game" :rules="teamGameRules" required/>
                <v-text-field v-model="team.teamBio" label="Team Bio" :rules="teamBioRules" required/>
                <v-checkbox v-model="team.acceptingNewMembers" label="Accepting Members" required/>
                </v-form>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false; resetTeam()">Cancel</v-btn>
          <v-btn color="blue darken-1" :disabled="!isValid" text @click="dialog = false; updateTeam()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import auth from '@/auth';
export default {
    props:{
        currentTeam: Object
    },
  data() {
    return {
      team: {
        teamName:'',
        teamBio:'',
        game:'',
         acceptingNewMembers:'',
      },
      teamNameRules: [
        v => !!v || "Team name is required.",
        v => v.length >= 4 || "Team name must be at least 4 characters.",
      ],
      teamGameRules: [
        v => !!v || "Team Game/Sport is required.",
      ],
      teamBioRules: [
        v=> !!v || "Team Bio is required",
      ],
      dialog: false,
      isValid: true
    };
  },
  methods:{
     resetTeam(){
         this.team = Object.assign({},this.currentTeam);
     },
     updateTeam(){
       fetch(`${process.env.VUE_APP_REMOTE_API}/api/team`,{
         method:'PUT',
         headers:{
            Authorization: 'Bearer ' + auth.getToken(),
            Accept: "application/json",
           'content-type': 'application/json'
         },
         body: JSON.stringify(this.team)
       })
       .then(response =>{
         if(response.ok){
           this.$emit('update-team');
         }
       })
     } 
  },
  created(){
      this.resetTeam();
  }
  
};
</script>