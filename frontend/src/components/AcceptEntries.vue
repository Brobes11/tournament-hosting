<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Team Requests</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Sign Up Requests</span>
        </v-card-title>
        <v-data-table :headers="headers" :items="requests" class="elevation-10">
          <template v-slot:item="row">
            <tr>
              <td
                class="clickable"
                @click="$router.push('/team-page/' + row.item.teamId)"
              >{{row.item.teamName}}</td>
              <td>{{row.item.teamBio}}</td>
              <td>
                <v-btn class="mx-2" fab dark small color="primary">
                  <v-icon dark>mdi-check-circle-outline</v-icon>
                </v-btn>
                <v-btn class="mx-2" fab dark small color="primary">
                  <v-icon dark>mdi-minus-circle-outline</v-icon>
                </v-btn>
              </td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      requests: [
        {
          teamName: "team team",
          message: "Please let me play"
        },
        {
          teamName: "team two",
          message: "Let me play"
        },
        {
          teamName: "team win",
          message: "Whatever"
        }
      ]
    };
  }
};
</script>

<style>
</style>