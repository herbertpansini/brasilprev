CREATE TABLE public.roles (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

INSERT INTO roles ("name") VALUES ('ROLE_USER');
INSERT INTO roles ("name") VALUES ('ROLE_ADMIN');