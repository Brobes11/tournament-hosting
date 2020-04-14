<template>
<v-container>
<v-card-title dark large > Browse Teams</v-card-title >

<!-- Sort teams by type of game or sport  -->
<v-container>
  <v-row>
    <v-col cols="1" class="d-flex justify-start align-center">
      <p>Filter by:</p>
    </v-col>
    <v-col xs="4" md="3" >
      <v-overflow-btn
        :items="dropdown_games"
        label="Choose Sport"
        v-model="gamefilter"
        target="#dropdown-example"
      ></v-overflow-btn>
    </v-col>
  </v-row>
</v-container>

<!-- available teams -->
<v-data-table dark :headers="headers" :items="sortGames" >
      <template v-slot:item="row">
          <tr>
            <td class="clickable" @click="$router.push('/team-page/' + row.item.teamId)">{{row.item.teamName}}</td>
            <td>{{row.item.game}}</td>
            <td>{{row.item.teamBio}}</td>
             <td>        <!--(handle snackbar notification) -->
              <join-team v-if="row.item.acceptingNewMembers===true" :teamId="row.item.teamId" @join-success="handleSnack()"></join-team>
              <v-row justify="center">
                 <v-btn class="mxauto" v-if="row.item.acceptingNewMembers===false" disabled color="primary" ><v-icon dark>mdi-send</v-icon> Join  </v-btn>
              </v-row>
            </td>
          </tr>
      </template>
</v-data-table>

<!-- notification pops only when the user's request is succesfully sent  -->
    <v-snackbar v-model="snackbar">
      {{ snackText }}
      <v-btn color="#03DAC5" text @click="snackbar = false"> Close </v-btn>
    </v-snackbar>

</v-container>
</template>
  


<script>
  import JoinTeam from '@/components/JoinTeam.vue';
  import auth from '@/auth';

  export default {
    components:{
      JoinTeam
    },
    
    
    data () {
      return {
        gamefilter:'All',
        dropdown_games: ["Volleyball","Basketball","Soccer","Hockey","Bike Polo","Euchre","Poker","DnD","Magic The Gathering","Super Smash Brothers","Other"] ,
        snackbar: false,
        snackText:'Your request has been submitted!',

        headers: [
          {
            text: 'Team Name',
            align: 'start',
            sortable: true,
            value: 'name',
          },
          { text: 'Game', value: 'game' },
          { text: 'Team Bio', value: 'bio' },
          { text: '', value: 'join' }
        ],
        teams:[],
        
      }
    },

    computed:{
        sortGames(){
            if(this.gamefilter === "All"){
                return this.teams;
            }
            return this.teams.filter((team)=> team.game == this.gamefilter);
        }
    },

    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/team`, {
        method: 'GET',
        headers: new Headers({
        Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
        })
        .then((response) => {
        if (response.ok) {
          return response.json();
        } 
        })
        .then((data) => {
          this.teams = data;
        })
        .catch((err) => console.error(err));
    },

    methods:{
      handleSnack(){
        this.snackbar=true;
      }
    }

  }
  
</script>




<style>


</style>