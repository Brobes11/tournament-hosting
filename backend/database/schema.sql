BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS teamRoster;
DROP TABLE IF EXISTS teamRequest;

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
  sport/game varchar(255) NOT NULL,
  accepting_members boolean NOT NULL,
  team_bio varchar(300)
);

CREATE TABLE teamRoster (
  user_id integer REFERENCES users (id),
  team_id integer REFERENCES teams (id),
  captain boolean NOT NULL
);

CREATE TABLE teamRequest (
  user_id integer REFERENCES users (id),
  team_id integer REFERENCES teams (id),
  message varchar(300)
);

COMMIT TRANSACTION;
