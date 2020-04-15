<template>
  <v-container>
    <v-row class = "text-center">
      <v-col>
        <h1 class="display-3 font-weight-bold mb-3">Welcome to Tournament Buddy!</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col col="6">
        <v-img
          :src="require('../assets/salad.jpg')"
          class="my-3"
          contain
        />
        <p>Come and see our many tournaments featuring all kinds of different sports and games. From magic to basketball, here at Tournament Buddy, we've got them all!</p>
        <v-btn href="/browse-tournaments">
           Click here to browse tournaments
        </v-btn>
      </v-col>
      <v-col cols="6">
        <v-img
          :src="require('../assets/loss.jpg')"
          class="my-3"
          contain
        />
        <p>We've got some fierce competitors in our selection of awesome teams! Register with us to become the best of the best!</p>
        <v-btn href="/register">
           Click here to register
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
</script>
