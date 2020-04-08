select * from users where UPPER(username) = UPPER('aus7in');

INSERT INTO teams (team_name, game, team_bio, accepting_members) VALUES ('whatevers', 'basketball', 'We like to play games', true)

select * from teams

DELETE FROM teamroster WHERE captain = false

select * from teamroster

SELECT team_name, game, team_bio FROM teams WHERE id IN (SELECT team_id FROM teamroster WHERE user_id = 1)

UPDATE users SET first_name = 'austin', last_name = 'zani', email = 'austinzani@me.com' WHERE username = 'zaniad'

SELECT * from users