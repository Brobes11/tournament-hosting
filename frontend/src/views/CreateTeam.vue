<template>
  <v-app>
<v-card width="400" class="mx-auto mt-5">

    <v-card-title>
      <h1 class="display-1">Create A Team</h1>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field label="Team Name"/>
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
      <v-list-item @click="method">
        <v-list-item-title>Volleyball</v-list-item-title>
      </v-list-item>
      <v-list-item @click="method">
        <v-list-item-title>Basketball</v-list-item-title>
      </v-list-item>
      <v-list-item @click="method">
        <v-list-item-title>Soccer</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>Hockey</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>Bike Polo</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>Euchre</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>Poker</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>DnD</v-list-item-title>
      </v-list-item>  <v-list-item @click="method">
        <v-list-item-title>Magic The Gathering</v-list-item-title>
      </v-list-item>
        <v-list-item @click="method">
        <v-list-item-title>Super Smash Brothers</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
</template>
   <v-text-field label="Other"/>
          <h4>Accepting New Members?</h4>
          
 <v-checkbox v-model="checkbox1" :label="`Accepting New Members? ${checkbox1.toString()}`"></v-checkbox>
            
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
      checkbox1: true
    }
  },
}
</script>