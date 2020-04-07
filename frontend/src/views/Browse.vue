<template>
<v-container>

    <v-card-title large class="elevation-5"> Browse Teams</v-card-title >

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




<v-data-table :headers="headers" :items="sortGames" class="elevation-10">
      <template v-slot:item="row">
          <tr>
            <td>{{row.item.name}}</td>
            <td>{{row.item.game}}</td>
            <td>{{row.item.bio}}</td>
            <td>
                <join-team></join-team>
            </td>
          </tr>
      </template>
</v-data-table>



  

</v-container>
</template>
  


<script>
  import JoinTeam from '@/components/JoinTeam.vue';

  export default {
    components:{
      JoinTeam
    },
    
    data () {
      return {
        gamefilter:'All',
        dropdown_games: ['Football', 'Baseball', 'Basketball', 'Volleyball', 'Super Smash', 'All']  ,

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

        teams: [
          {
            name: "Blue Team",
            game: 'Football',
            bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,',
            join: 'join',
          },
          {
            name: "Red Team",
            game: 'Baseball',
            bio: 'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in',
            join: 'join',
          },
          {
            name: "Green Team",
            bio: 'voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
            game: 'Volleyball',
            join: 'join',           
          },
          {
            name:"Yellow Team",
            bio: 'voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
            game: 'Super Smash',
            join: 'join',
          },
          
        ],
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
    
   
  }
  

</script>




<style>


</style>