CREATE TABLE users (
	id bigserial NOT NULL,
	user_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT users_user_name_uk UNIQUE (user_name),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO users (user_name, "password") VALUES ('herbert', '$2a$10$IH1fZupXgOhHjr6WqcUlu.h43o8ANu9novU3Kjx7Uuxc3MrPpHQhy');
INSERT INTO users (user_name, "password") VALUES ('admin', '$2a$10$KlgCMwAgpKH4NGPw2b1DauoBAIK0F2mlcr6sws.T3t/5.OY4jUFYS');
