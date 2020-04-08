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
      
   <v-list-item v-model="team.game"  v-for="sport in sports" :key="sport.id" @click="v-select" >
     <v-list-item-title>{{sport.name}}</v-list-item-title>
       <v-list-item-action v-model="team.game">
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
          
 
 <v-radio-group v-model="team.acceptingMembers" row>
   <v-radio label="yes" value="yes"></v-radio>
   <v-radio label="no" value="no"></v-radio>
   </v-radio-group>
         
     <v-text-field label="About The Team:"/>
       
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
  name: 'registerT',

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
        acceptingMembers:true,
        teamBio:''
        
      },
      registrationErrors: false,
     
    }
  },

methods:{
  createTeam() {
    fetch('$process.env.VUE_APP_REMOTE_API}/createTeam',{
       method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.team)
      })
      .then((response)=>{
        if(response.ok){
          this.$router.push({path:'/create-team',query :{registration: 'success'}});

        }else{
          this.registrationErrors=true;
        }

      })
      .then((err)=> console.error(err));
    },
  },
  
};
</script>