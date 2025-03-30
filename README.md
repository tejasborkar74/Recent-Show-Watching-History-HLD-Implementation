Recently Watching Show History

Tables: 

1. shows: Stores shows information (show_id, show_name, episode_no, total_running_minutes, total_episodes). There can be 2 types of shows:
   1. Movies: total_episodes and episode_no is 1
   2. Series: there can be n total_episodes, each episode has a different row with same show_name

2. users: Stores users information (user_id, username, email)
3. recently_watching_shows: Stores watch history of user of shows which is not complete. (id, show_id, user_id, last_registered_time_in_minutes, total_running_minutes, created_time, updated_time)


APIs:

For users Table:

1. GET: /api/users/all_users
2. POST: /api/users/create_user (create a user)
    ``` 
   Request Body
   {
       "username": <name>,
       "email": <email>
   } 
   ``` 
   
For shows Table:

1. GET: /api/shows/all_shows
2. POST: /api/shows/create_movie
```
    Request Body
    {
        "showName": <movie-name>,
        "totalRunningMinutes": 50
    }
```
3. POST: /api/shows/create_series
```
      Request Body
      {
          "showName": <name>,
          "totalEpisodes": 5, 
          "episodeRunningTimeInMin": [45, 50, 43, 46, 51]
      }
```

For recently_watching_shows table:

1. GET: /api/recently_watching/getAllRecentShowsForUser/{uerId}
   
    This will give all the shows that user was watching in last watched order
2. POST: /api/recently_watching/updateUserShowProgress
```
   Request Body
   {
       "userId": 1,
       "showId": 5,
       "currentTimeInMinutes": 34
   }
   
   Case 1: If a given UserId and ShowId not present => add new entry with latest time
   Case 2: If a given UserId and ShowId present     => update the entry with the currentTimeInMinutes
   Case 3: If for a given UserId and ShowId, the currentTimeInMinute >= totalRunningMinutes:
            1. if for the given show next episode not present => delete the entry
            2. if for the given show next episode presrent    => delete the entry and insert entry for the new episode (new showId) with last_registered_time_in_minutes = 0
   Case 4: If for a given showId and UserId entry not found but for the userId and showId.showName entry found
           (This means user was previously watching same show but different episode)
           So we will update the same entry with this showId (new episode)
           Reason: User can not have 2 episodes of same series in recent watch
```


How to Run the project:
1. Replace a working postgres server connection credentials in src > main > resources > application.properties
 