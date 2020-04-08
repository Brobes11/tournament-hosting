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
            <td>{{row.item.tournamentBio}}</td>
            <td>
                <join-team></join-team>
            </td>
          </tr>
      </template>
</v-data-table>


</v-container>
</template>

<script>

  export default {
    components:{
    },
    data () {
      return {
        gamefilter:'All',
        dropdown_games: ['Football', 'Baseball', 'Basketball', 'Volleyball', 'Super Smash', 'All']  ,

        headers: [
          {
            text: 'Tournament',
            align: 'start',
            sortable: true,
            value: 'name',
          },
          { text: 'Game', value: 'game' },
          { text: 'Tournament Bio', value: 'bio' },
          { text: '', value: 'join' }
        ],
        tournaments:[{
            tournamentName:'World Series',
            game:'Baseball',
            tournamentBio:'Winner takes home title of World Champ'
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
    }
  }
</script>

<style>

</style>