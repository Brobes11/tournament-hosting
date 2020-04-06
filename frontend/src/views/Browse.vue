<template>
<v-container>
    <p>Browse Teams</p>
    <p>Sort by:</p>

    <v-overflow-btn
        class="my-2"
        :items="dropdown_games"
        label="Choose Sport"
        v-model="gamefilter"
        target="#dropdown-example"
    ></v-overflow-btn>


  <v-data-table
    :headers="headers"
    :items="sortGames"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</v-container>
</template>
  


<script>
  export default {
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