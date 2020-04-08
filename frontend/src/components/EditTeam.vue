<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat text v-on="on">
          <v-icon small>mdi-cog</v-icon>Edit Team
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Edit Team Info</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="team.teamName" label="Team Name" required/>
                <v-text-field v-model="team.game" label="sport/game" required/>
                <v-text-field v-model="team.teamBio" label="Team Bio" required/>
                <v-checkbox v-model="team.acceptingMembers" label="Accepting Members" required/>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false; resetTeam()">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
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
            acceptingMembers:'',
        },
        dialog: false
    };
  },
  methods:{
     resetTeam(){
         this.team = Object.assign({},this.currentTeam);
     } 
  },
  created(){
      this.resetTeam();
  }
  
};
</script>