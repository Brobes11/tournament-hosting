select * from users where UPPER(username) = UPPER('aus7in');

INSERT INTO teams (team_name, game, team_bio, accepting_members) VALUES ('whatevers', 'basketball', 'We like to play games', true)

select * from teams

DELETE FROM teamroster WHERE captain = false

select * from teamroster

SELECT team_name, game, team_bio FROM teams WHERE id IN (SELECT team_id FROM teamroster WHERE user_id = 1)

UPDATE users SET first_name = 'austin', last_name = 'zani', email = 'austinzani@me.com' WHERE username = 'zaniad'

SELECT * from users

INSERT INTO teamroster (user_id,team_id,captain )VALUES (1,1,true)

SELECT * FROM tournamentroster;

INSERT INTO tournaments (tourney_name, game, start_date, end_date, location, prize_desc, tournament_owner, entry_fee, accepting_entries) VALUES ('World Series', 'Baseball', '10/05/2020', '10/12/2020' , 'Cinncinnati, OH', 'World Series Trohpy and title of World Champions', 1, 200, true)



select * from users;

select * from tournaments;