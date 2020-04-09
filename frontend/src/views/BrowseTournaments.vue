<template>
  <v-container>

    <v-card-title large class="elevation-5">Browse Tournaments</v-card-title >

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

<v-data-table :headers="headers" :items="sortTournaments" class="elevation-10">
      <template v-slot:item="row">
          <tr>
            <td>{{row.item.tournamentName}}</td>
            <td>{{row.item.game}}</td>
            <td>{{row.item.prizeDescription}}</td>
            <td>
                <join-team></join-team>
            </td>
          </tr>
      </template>
</v-data-table>


</v-container>
</template>

<script>
import auth from '@/auth.js';
  export default {
    components:{
    },
    data () {
      return {
        gamefilter:'All',
        dropdown_games: ['All','Football', 'Baseball', 'Basketball', 'Volleyball', 'Super Smash']  ,

        headers: [
          {
            text: 'Tournament',
            align: 'start',
            sortable: true,
            value: 'name',
          },
          { text: 'Game', value: 'game' },
          { text: 'Tournament Prize', value: 'prize' },
          { text: '', value: 'join' }
        ],
        tournaments:[{
            tournamentName:'',
            game:'',
            tournamentBio:''
        }],
      }
    },

    computed:{
        sortTournaments(){
            if(this.gamefilter === "All"){
                return this.tournaments;
            }
            return this.tournaments.filter((team)=> team.game == this.gamefilter);
        }
    },
     created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament`, {
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
          this.tournaments = data;
        })
        .catch((err) => console.error(err));
    }
  }
</script>

<style>

</style>