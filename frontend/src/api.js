import auth from '@/auth.js'

export default {

    
      getUserTeams(){
         fetch(`${process.env.VUE_APP_REMOTE_API}/api/team?userId=${auth.getUser().id}`,  {
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
      })
      .then((response) => {
          return response.json();
        })
      .then(teamsFromApi => {
        return teamsFromApi;
      })
      },
       getUserTournaments(){
         fetch(`${process.env.VUE_APP_REMOTE_API}/api/tournament?userId=${auth.getUser().id}`,  {
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
      })
      .then((response) => {
          return response.json();
        })
      .then(tourneysFromApi => {
        return tourneysFromApi;
      })
      },
      getCaptainedTeams(){
         fetch(`${process.env.VUE_APP_REMOTE_API}/api/user/captain?id=${auth.getUser().id}`,  {
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
      })
      .then((response) => {
          return response.json();
        })
      .then(apiCaptainedTeams => {
        return apiCaptainedTeams;
      })
      },
      getUser(){
        let username = auth.getUser().sub;
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/user/${username}`,  {
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + auth.getToken(),
        },
      })
      .then((response) => {
          return response.json();
        })
      .then(userFromApi => {
        return userFromApi;
        })
      }
}