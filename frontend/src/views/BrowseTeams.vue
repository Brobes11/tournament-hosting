<template>
<v-container>
<v-card-title dark large class="elevation-5"> Browse Teams</v-card-title >

<!-- Sort teams by type of game or sport  -->
<v-container>
  <v-row>
    <v-col sm="1">
      <p>Sort by:</p>
    </v-col>
    <v-col>
      <v-overflow-btn
        class="elevation-5"
        :items="dropdown_games"
        label="Choose Sport"
        v-model="gamefilter"
        target="#dropdown-example"
      ></v-overflow-btn>
    </v-col>
  </v-row>
</v-container>

<!-- available teams -->
<v-data-table :headers="headers" :items="sortGames" class="elevation-10">
      <template v-slot:item="row">
          <tr>
            <td>{{row.item.teamName}}</td>
            <td>{{row.item.game}}</td>
            <td>{{row.item.teamBio}}</td>
             <td>        <!--(handle snackbar notification) -->
              <join-team @join-success="handleSnack()"></join-team>
            </td>
          </tr>
      </template>
</v-data-table>

<!-- notification pops only when the user's request is succesfully sent  -->
    <v-snackbar v-model="snackbar">
      {{ snackText }}
      <v-btn color="green" text @click="snackbar = false"> Close </v-btn>
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
        dropdown_games: ['Football', 'Baseball', 'Basketball', 'Volleyball', 'Super Smash', 'All']  ,
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