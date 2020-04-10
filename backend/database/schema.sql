BEGIN TRANSACTION;

DROP TABLE IF EXISTS tournamentRequest;
DROP TABLE IF EXISTS tournamentRoster;
DROP TABLE IF EXISTS teamRoster;
DROP TABLE IF EXISTS teamRequest;
DROP TABLE IF EXISTS tournaments;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  role varchar(255) NOT NULL default('user')
);

CREATE TABLE teams (
  id serial PRIMARY KEY,
  team_name varchar(255) NOT NULL,
  game varchar(255) NOT NULL,
  accepting_members boolean NOT NULL,
  team_bio varchar(300)
);

CREATE TABLE teamRoster (
  user_id integer REFERENCES users (id),
  team_id integer REFERENCES teams (id),
  captain boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (user_id, team_id)
);

CREATE TABLE teamRequest (
  user_id integer REFERENCES users (id),
  team_id integer REFERENCES teams (id),
  message varchar(300),
  constraint pk_teamRequest primary key (user_id, team_id)
);

CREATE TABLE tournaments (
  id serial PRIMARY KEY,
  tourney_name varchar(255) NOT NULL,
  game varchar(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  location varchar(255) NOT NULL,
  entry_fee integer NOT NULL, 
  prize_desc varchar(300) NOT NULL,
  tournament_owner integer REFERENCES users(id),
  accepting_entries boolean NOT NULL
);

CREATE TABLE tournamentRequest (
  tourney_id integer REFERENCES tournaments (id), 
  team_id integer REFERENCES teams (id),
  message varchar(300),
  constraint pk_tournamentRequest primary key (tourney_id, team_id)
);

CREATE TABLE tournamentRoster (
  tourney_id integer REFERENCES tournaments (id), 
  team_id integer REFERENCES teams (id),
  constraint pk_tournamentRoster primary key (tourney_id, team_id)
);

INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('DirtyDan', '8FEzT3Yop2aA7MJNWw7dyg==', 'zFiGLfGKoTZPIVaF6bhqfRfFeSr3EsmgXfAlhZEw28rwi42Hou5NIjNVjJgIrtd6n50VseitoiS1hCyKqMJBIK0K+Tua5es8uh2pMerQoceujeMewXkGyAZbTdYSR172h2BhwiclB9UbhonIHsGlbW1kwGqsTddxA3jiy4vaCfI=', 'junk@junkmail.com', 'Ben', 'Peters');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('Brobes11', 'pjL/Ikmd4opHKbbrA70o8g==	', '1I0cJKQaQ+8oUbGd4gQK4zdTehUedjJqQ6o+6SGu4+qhZIMWF7QYpo1Dz+F6z7CjrftlwsRMWUG3nl6Z9jNlDYX60wYtI7CXMITMNgai2kYeHvXmq5+bOpMl81BvjMRi8GgL7R+8G1OWSK2X4rVn43xFY1NBVfW0ots+8TEe+hs=', 'brobes11@gmail.com', 'Taylor', 'Broberg');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('WaterBoy', 'OHlSjWgXpn9+F9tNQxQJQw==', 'b4iuB9/S1D1DQPDk5S5l1iZdbVhnPA1DgbZwBGur+I2tacuj6J1d/Mga3hrEcHRdJ+t0xpqYaIVWzCIAk9acBPK36+UhtEWCCxHuXGxW9BcGO13DfCGUcjEPGE3TS4VjIJgAM3u+eqU1hceFn/L69fLLUdf7Rg55O7An/uMaKBk=', 'thewaterboy@waterboy.org', 'Bobby', 'Bouche');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('BIG', '3DR4R18IGzjnA5iVh5m+RA==', 'wGnAK/Zhicve54OmiPoH1RlkyGrJC09YUC9i5NRSZ97cujoFb3FOzB6fQNMh0tuZ2dKP2XkOdVQyHzzfXqzDH9kZuWmiFSKdyb7QjZtsKWrwacCCwlmVbDss9nJOI5U6mwZWEWMWCGE3jfjSKVLBuMaatP0h0CxPH0Ugu2O9FJE=', 'thenotorius@gmail.com', 'Biggie', 'Smalls');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('Still_Alive_Bitches', '2ANUQvJmp1YsBlfOKbffRg==', 'BC1v/iFhPAETeVP6e4w0CYp+xHpmQygwJkiDHcYhgoJGWcHl9+QblW+d9sqMFplzB4WyJtFMiH59s7boFyJc++Qt83xT5eeJQl8MSCoRHQVXR0SHcoayecoIzDAKhIKE88bpGTrxbTax5W/z+X+3BpAHYp2sv6EKiHLp34Y005c=', 'WithMyBoyElvis@secretIsland.com', 'Tupac', 'Shakur');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('TheRealSlimShady', 'AsBdYTF15lNapuqpTxj9LA==', 'UzfWrA9Wk1eHeH4wvrl0GHsegL/UCncWVuIr5OQoDKbFPs98eLdGpCf6pKxo0TR6XhcmzoXLAHgtyDToARBOudj9yGK060XU77slcrQvTumghkkpgtPMJnfbdxlKg3Q/1M+At2i6gSttQFu0UYOy4GIJcjMiI2jFiM1Dij9TNxY=', 'shady@dreRecords.com', 'Marshall', 'Mathers');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('BringMeACarrot', 'CpS4JcKslj15L5cbMuiJDA==', 'J+jzNPL1ALV6gCU0sIdix8XRedSeQqcEtNmGSBQyl8+3JVSxkmdbba38MtgcxZ04zDvubzdnON8nu1YPw/GUA9/lfMjzrr7FvTJnHIXLnW8nItWZdoxb9HuUveXLeK2VxW+QSvdefngNjRWsiJyBzg/WbDXu7GWmvXwlRRqAk1c=', 'IuseAustinsEmail@gmail.com', 'Austins', 'Rabbit');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('IputTheSecureInSecurity', 'T/LZ8EtJAuzEMSxeujimSQ==', 'ccNvZXRL4Wx7t0KRX2boH/DJofnvGbbBSV8wE8EVwzNZOJHLt243WlppOh2fjBRjd0wxrt3C45KZLSgzbx+k9qxPxOkTA8iME9fW1vKXqbDEAxxoj+03SlGj2zHZZi00s4/QDnS2DUH5Pp/o2d5dIxbKh2X2q3V/uz2dSQR4COY=', 'joe.security@darkweb.com', 'Joe', 'Security');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('MissileMan69', 'aJpq5YPlwUpOosvalA0Oxg==', 'yZp4LiKMqJ5QZO0/nq1OL+wDkPEFVBcEYV5tMmuRr77u2QinkRTvl25MLt6IA7rIiDv3fcw1PzhpQm8uc1b1lbZjg1P8rWV2l8gJ+FPNFH5xKS8adSfcegsew61pz6kFcs9VrM0Kf+PmJwfd9JeAN4J+x+tDp4d110Yg8IxJeQs=', 'TheOneTrueLeader@northkorea.com', 'Kim', 'Jong-un');
INSERT INTO users(username, password, salt, email, first_name, last_name) VALUES ('I_Pitty_The_Fool', '5ACU3Q3rHHSTdcgMquig0Q==', 'jdTc9I7aK9rois2BJBuZmAUr/QWGntB2cb6q0k2DpK7YT5T5JBM3aax+L67oScgqpFy41hGAlo99Jtfh/xAMALtE2Uog577aCcOpJjiSyvnYcpT4TbZ6NlA4nKLfEdNlfQ2+rpRT22oACQ+c2mVzLlbqRXkh2UYgMboTE3YwvL0=', 'theOneAndOnly@yoursTruly.com', 'Mr.', 'T');





INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('The Sharks', 'Super Smash Bros', true, 'Come have fun and get your smash on!');
INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('The Exotics', 'Basketball', true, 'Just Shoot it');
INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('Magic', 'Magic the Gathering', false, 'magic chickens');
INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('Pen15', 'Bike Polo', false, 'beep boop beep');
INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('Cyclone jr.', 'Hockey', true, 'Grab your sticks and get on the ice');




INSERT INTO teamRoster(team_id, user_id, captain) VALUES (1,1,false);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (1,2,true);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (2,3,false);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (2,4,true);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (3,5,true);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (3,6,true);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (4,7,false);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (4,8,true);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (5,9,false);
INSERT INTO teamRoster(team_id, user_id, captain) VALUES (5,10,true);




INSERT INTO teamRequest(team_id, user_id, message) VALUES (10,1, 'please sir give me a home');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (9,1, 'you guys want some cookieeesss!');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (8,1, 'me big and strong. me play ball');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (7,1, 'death by snu snu ');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (6,1, 'Mr. Gorbachev, tear down this wall!');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (5,1, 'In the town where I was born Lived a man who sailed to sea And he told us of his life In the land of submarines');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (4,1, 'yall motha f*&c!S need jesus');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (3,1, 'Dont you lose him, Samwise Gamgee');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (2,1, 'In a galaxy far far away');
INSERT INTO teamRequest(team_id, user_id, message) VALUES (1,1, 'you dont even know me');



 
INSERT INTO tournaments(id,tourney_name,game,start_date,end_date,location,entry_fee,prize_desc,tournament_owner,accepting_entries)(
VALUES(1,'Smash to the death','Super Smash Brothers', 2020-10-06,2020-10-08,'Cincinnati',5,'winner gets gold joycons',5,true);

INSERT INTO tournaments(id,tourney_name,game,start_date,end_date,location,entry_fee,prize_desc,tournament_owner,accepting_entries)(
VALUES(2,'Sun in the Sand','Volleyball', 2020-08-06,2020-08-08,'Cincinnati',25,'winner gets a new ball and sunglasses',2,true);

INSERT INTO tournaments(id,tourney_name,game,start_date,end_date,location,entry_fee,prize_desc,tournament_owner,accepting_entries)(
VALUES(3,'White men cant jump','Basketball', 2020-01-06,2020-01-08,'Brooklyn',30,'winner gets new jock strap',5,false);

INSERT INTO tournaments(id,tourney_name,game,start_date,end_date,location,entry_fee,prize_desc,tournament_owner,accepting_entries)(
VALUES(4,'Your soul will be mine','DnD', 2020-11-06,2020-11-08,'Tampa',10,'almighty glory',8,false);

INSERT INTO tournaments(id,tourney_name,game,start_date,end_date,location,entry_fee,prize_desc,tournament_owner,accepting_entries)(
VALUES(5,'White men cant jump','Bike Polo', 2020-01-06,2020-01-08,'Brooklyn',30,'winner gets new jock strap',5,true);





INSERT INTO tournamentRequest(tourney_id, team_id,message)VALUES(1,1,'let us host a tournament please');
INSERT INTO tournamentRequest(tourney_id, team_id,message)VALUES(2,2,'you let us in!');
INSERT INTO tournamentRequest(tourney_id, team_id,message)VALUES(3,3,'we host tourney. have mad fun.');
INSERT INTO tournamentRequest(tourney_id, team_id,message)VALUES(4,4,'we play ball');
INSERT INTO tournamentRequest(tourney_id, team_id,message)VALUES(5,5,'champions r us ');




INSERT INTO tournamentRoster(tourney_id, team_id) VALUES (1,1);
INSERT INTO tournamentRoster(tourney_id, team_id) VALUES (2,2);
INSERT INTO tournamentRoster(tourney_id, team_id) VALUES (3,3);
INSERT INTO tournamentRoster(tourney_id, team_id) VALUES (4,4);
INSERT INTO tournamentRoster(tourney_id, team_id) VALUES (5,5);









COMMIT TRANSACTION;

SELECT * FROM users
SELECT * FROM tournamentRequest
SELECT * FROM tournamentRoster
SELECT * FROM teamRoster
SELECT * FROM teamRequest
SELECT * FROM tournaments
SELECT * FROM teams
