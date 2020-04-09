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
  captain boolean NOT NULL,
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
  prize_desc varchar(300) NOT NULL,
  tournament_owner integer REFERENCES users(id)
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

INSERT INTO teams(team_name, game, accepting_members, team_bio) VALUES ('The Sharks', 'Super Smash Bros', true, 'Come have fun and get your smash on!');

COMMIT TRANSACTION;
