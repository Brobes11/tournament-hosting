select * from users where UPPER(username) = UPPER('aus7in');

INSERT INTO teams (team_name, game, team_bio, accepting_members) VALUES ('whatevers', 'basketball', 'We like to play games', true)

select * from teams

DELETE FROM teamroster WHERE captain = false

select * from users

SELECT team_name, game, team_bio FROM teams WHERE id IN (SELECT team_id FROM teamroster WHERE user_id = 1)

UPDATE users SET first_name = 'austin', last_name = 'zani', email = 'austinzani@me.com' WHERE username = 'zaniad'

SELECT * from teams

INSERT INTO teamroster (user_id,team_id,captain )VALUES (1,1,true)

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
select tourney_name, game, start_date, end_date, location, 
entry_fee, prize_desc, tournament_owner, accepting_entries 
from tournaments 
where id = 2;

SELECT * 
FROM teams
JOIN 
