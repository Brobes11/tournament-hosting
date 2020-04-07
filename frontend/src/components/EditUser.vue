<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn flat text v-on="on">
          <v-icon small>mdi-cog</v-icon>Edit User
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Edit User Info</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="user.firstName" label="First Name" required/>
                <v-text-field v-model="user.lastName" label="Last Name" required/>
                <v-text-field v-model="user.email" label="Email" type="email" required/>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
    props:{
        currentUser: Object
    },
  data() {
    return {
        user: this.currentUser,
        dialog: false
    };
  },
  
};
</script>

<style>
</style>