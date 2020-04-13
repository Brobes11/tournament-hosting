select * from users where UPPER(username) = UPPER('aus7in');

INSERT INTO teams (team_name, game, team_bio, accepting_members) VALUES ('whatevers', 'basketball', 'We like to play games', true)

select * from teamroster WHERE team_id = 3

DELETE FROM teamroster WHERE captain = false

select * from users

SELECT team_name, game, team_bio FROM teams WHERE id IN (SELECT team_id FROM teamroster WHERE user_id = 1)

UPDATE users SET first_name = 'austin', last_name = 'zani', email = 'austinzani@me.com' WHERE username = 'zaniad'

SELECT * from teams

INSERT INTO teamroster (user_id,team_id,captain )VALUES (1,3,true)

SELECT * FROM tournaments

select * from tournaments

INSERT INTO tournaments (tourney_name, game, start_date, end_date, location, prize_desc, tournament_owner, entry_fee, accepting_entries) VALUES ('World Series', 'Baseball', '10/05/2020', '10/12/2020' , 'Cinncinnati, OH', 'World Series Trohpy and title of World Champions', 2, 200, false)


INSERT INTO tournamentroster (tourney_id, team_id) VALUES (2,2)

SELECT * from tournamentrequest

SELECT users.username, message
FROM teamrequest
JOIN users ON teamrequest.user_id = users.id
WHERE team_id = 2

SELECT * FROM tournaments 
WHERE id IN (SELECT tourney_id FROM tournamentroster WHERE team_id IN (SELECT id FROM teamroster WHERE user_id = 1))

SELECT id, username, role, email, first_name, last_name, teamroster.captain FROM users 
JOIN teamroster ON teamroster.user_id = users.id
WHERE id IN (SELECT user_id FROM teamRoster WHERE team_id = 3) AND teamroster.team_id = 3;

INSERT INTO tournamentrequest (team_id, tourney_id, message) VALUES (2,1,'test')

SELECT * FROM tournamentrequest

DELETE FROM tournamentrequest WHERE team_id = 1 AND tourney_id = 1

SELECT * FROM tournamentrequest

SELECT * FROM tournamentroster

INSERT INTO tournamentrequest (team_id, tourney_id, message) VALUES (3,1,'test')

SELECT team_id FROM teamroster WHERE user_id = 5 AND captain = true