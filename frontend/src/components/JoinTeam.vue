<template>
  <v-row justify="center">

    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on"><v-icon dark>mdi-send</v-icon> Join  </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Join Team</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-textarea outlined label="Message Team Captian" v-model="request.message" required></v-textarea>
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
      name:'JoinTeam',
    data: () => ({
      dialog: false,
      
      
      request:{
        userId: '',
        teamId: 7,
        message: ''
      },

    }),

      methods:{
      sendJoinRequest(){
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/team/join-request`,{
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

      setUser(){
        this.request.userId = auth.getUser().id;
      }

    },
    created(){
      this.setUser();
    },




  };

</script>



<style>

</style>