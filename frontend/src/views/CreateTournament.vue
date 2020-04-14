<template>
  <v-card width="500" id="register" class="mx-auto mt-5 at-5">
    <v-card-title dark large class="elevation-2">Create a New Tournament</v-card-title>
    <v-divider></v-divider>
    <v-card-text id="instructions" class="text-center">
      Please fill out the information below and click the 'Create Your Tournament' button
      below to create your tournament.
    </v-card-text>
    <v-card-text>
      <v-form ref="createTournamentForm">
        <v-text-field label="Tournament Name" v-model="tournament.tournamentName" :rules="tNameRules" required></v-text-field>
        <template>
          <v-menu>
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-bind="attrs" v-on="on">{{tournament.game===''?'Sport/game':tournament.game}}</v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="sport in sports"
                :key="sport.id"
                @click="tournament.game=sport.name"
              >
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
        <v-text-field type="date" label="Start Date" v-model="tournament.startDate" :rules="startDateRules" required></v-text-field>
        <v-text-field type="date" label="End Date" v-model="tournament.endDate" :rules="endDateRules" required></v-text-field>
        <v-text-field label="Location" v-model="tournament.location" :rules="locationRules" required ></v-text-field>
        <v-text-field label="Entry Fee" v-model="tournament.entryFee" :rules="entryFeeRules" required></v-text-field>
        <v-textarea label="Prize Description" v-model="tournament.prizeDescription" :rules="prizeDescriptionRules" required></v-textarea>
      </v-form>
      <v-card-actions>
        <v-btn color="#03DAC5" @click="createTournament" >Create Your Tournament</v-btn>
      </v-card-actions>
    </v-card-text>
  </v-card>
</template>

<script>
import auth from "@/auth";

export default {
  data() {
    return {
      tournament: {
        tournamentName: "",
        game: "",
        startDate: "",
        endDate: "",
        location: "",
        entryFee: 0,
        prizeDescription: "",
        acceptingEntries: true
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
      ],
      tNameRules:[v => !!v || "Tournament Name is required"],
      startDateRules:[v => !!v || "required"],
      endDateRules:[v => !!v || "required"],
      locationRules:[v => !!v || "required"],
      entryFeeRules:[v => !!v || "required"],
      prizeDescriptionRules:[v => !!v || "required"],
    };
  },
  methods: {
    createTournament() {
      if(this.$refs.createTournamentForm.validate()){
      fetch(
        `${process.env.VUE_APP_REMOTE_API}/api/tournament?userId=${
          auth.getUser().id
        }`,
        {
          method: "POST",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.tournament)
        }
      ).then(response => {
        if (response.ok) {
          this.$router.push("/browse-tournaments");
        }
      });
    }
    }
  }
};
</script>

<style>
</style>