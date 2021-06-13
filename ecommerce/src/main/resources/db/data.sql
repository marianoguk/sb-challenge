DROP TABLE IF EXISTS back_scratcher_size;
CREATE TABLE back_scratcher_size(
	id serial PRIMARY KEY,
	back_scratcher_id serial NOT NULL,
	size VARCHAR ( 5 ) NOT NULL
);

DROP TABLE IF EXISTS back_scratcher;
CREATE TABLE back_scratcher (
	id serial PRIMARY KEY,
	name VARCHAR ( 200) NOT NULL,
	description VARCHAR ( 300) NOT NULL,
	price NUMERIC(5,2) NOT NULL
);

ALTER TABLE back_scratcher ADD UNIQUE (name);

ALTER TABLE back_scratcher_size ADD CONSTRAINT bs_size_bs_fk
    FOREIGN KEY (back_scratcher_id) REFERENCES back_scratcher (id);