<template>
  <v-card width="350" id="login" class="mx-auto mt-5 at-5">
    <v-card-title>
      <h3 class="h1 mb-3 font-weight-normal">Match Up #{{}}</h3>
    </v-card-title>
    <v-card-text>
      <p>Team 1: {{}}</p>
      <p>Team 2: {{}}</p>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      tournamentMatch: {}
    };
  }
};
</script>

<style scoped>
</style>