<template>
  <v-app>
<v-card width="500" class="mx-auto mt-5">

    <v-card-title>
      <h1 class="display-1">Create A Team</h1>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field label="Team Name" v-model="team.name"/>
          <v-spacer></v-spacer>
       <h4>Please select your sport/game: </h4>
      
       <h5>If not on the list please enter it below. </h5>
       <br>
       <spacer></spacer>
          <template>
  <v-menu>
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        v-bind="attrs"
        v-on="on"
      >
      Sport/game
      </v-btn>
    </template>

    <v-list>
   <v-list-item v-model="game" @click="method" v-for="sport in sports" :key="sport.id">
     <v-list-item-title>{{sport.name}}</v-list-item-title>
       <v-list-item-action>
          <v-btn icon>
            <v-icon color="grey lighten-1">mdi-information</v-icon>
          </v-btn>
        </v-list-item-action>
   </v-list-item>
    </v-list>
  </v-menu>
 </template>
   <v-text-field v-if="team.game==='other'" label="Other"/>
          <h4>Accepting New Members?</h4>
          
 <v-checkbox v-model="checkbox1" :label="`Accepting New Members? ${checkbox1.toString()}`"></v-checkbox>
            <h4>Level of Play:</h4>
             <v-radio-group v-model="team.levelOfPlay" row>
             <v-radio  label="Professional" value="Professional"></v-radio>
            <v-radio  label="Competitive" value="Competitive"></v-radio>
      <v-radio  label="Beer League" value="Beer League"></v-radio>
      <v-text-field label="About The Team:"/>
       
    </v-radio-group>
        </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn color="success">Register</v-btn>
       </v-card-actions>

  </v-card>
    
  </v-app>
</template>

<script>


export default {
  name: 'App',

  components: {
    
  },

  data: ()=> {
    return{
      checkbox1: true,
      sports:[
    { id: 1, name: 'Volleyball' },
    { id: 2, name: 'Basketball'},
    { id: 3, name: 'Soccer' },
    { id: 4, name: 'Hockey' },
    { id: 5, name: 'Bike Polo'},
    { id: 6, name: 'Euchre' },
    { id: 7, name: 'Poker'},
    { id: 8, name: 'DnD'},
    { id: 9, name: 'Magic The Gathering'},
    { id: 10, name: 'Super Smash Brothers'},
    { id: 11, name: 'Other'}
],
      team:{
        name:'',
        game:'',
        acceptingMembers: true,
        teamBio:'',
        levelOfPlay:''
      }
    }
  },
}
</script>