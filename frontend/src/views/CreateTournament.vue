<template>
  <v-card width="500" id="register" class="mx-auto at-5">
    <v-card-title dark large class="elevation-2">Create a New Tournament</v-card-title>
    <v-divider></v-divider>
    <v-card-text id="instructions" class="text-center">
      Please fill out the information below and click the 'Create Your Tournament' button
      below to create your tournament.
    </v-card-text>
    <v-card-text>
      <v-form>
        <v-text-field label="Tournament Name"></v-text-field>
        <v-text-field label="Game/Sport/Activity"></v-text-field>
        <v-text-field type="date" label="Start Date"></v-text-field>
        <v-text-field type="date" label="End Date"></v-text-field>
        <v-text-field label="Entry Fee"></v-text-field>
        <v-textarea label="Prize Description"></v-textarea>
        <v-card-actions>
          <v-btn color="success" type="submit">Create Your Tournament</v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      tournament: {
        tournamentName: "",
        game: "",
        startDate: "",
        endDate: "",
        location: "",
        entryFee: "",
        prizeDescription: ""
      },
      user: {
        userId: "",
        userName: ""
      },
      sports: [
        { id: 1, name: "Volleyball" },
        { id: 2, name: "Basketball" },
        { id: 3, name: "Soccer" },
        { id: 4, name: "Hockey" },
        { id: 5, name: "Bike Polo" },
        { id: 6, name: "Euchre" },
        { id: 7, name: "Poker" },
        { id: 8, name: "DnD" },
        { id: 9, name: "Magic The Gathering" },
        { id: 10, name: "Super Smash Brothers" },
        { id: 11, name: "Other" }
      ]
    };
  }
};
</script>

<style>
</style>