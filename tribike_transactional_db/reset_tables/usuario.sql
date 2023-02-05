--\c tribike;

--DROP TABLE usuario; 

CREATE TABLE IF NOT EXISTS usuario (
	id serial,
	papel text,
	email text,
    username text,
	password_hash text,
	PRIMARY KEY( id )
);