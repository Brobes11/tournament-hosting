<template>
  <div>
      <v-card
    class="mx-auto"
    max-width="600"
  >
    <v-list-item>
        <v-list-item-avatar
        tile
        size="150"
        color="grey"
      ></v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title class="headline mb-1">{{user.name}}</v-list-item-title>
        <div class="overline">Username: {{user.username}}</div>
        <div class="overline">Email: {{user.email}}</div>
      </v-list-item-content>
        
      
    </v-list-item>
  </v-card>
  </div>
</template>

<script>
export default {
    data(){
        return{
            user: {
                name: 'John Doe',
                email: 'john@test.com',
                username: 'johndoe2020'
            }
        }
    }
}
</script>

<style>

</style>