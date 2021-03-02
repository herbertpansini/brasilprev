CREATE TABLE users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT users_roles_uk UNIQUE (user_id, role_id)
);

ALTER TABLE users_roles ADD CONSTRAINT users_roles_users_fkey FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE users_roles ADD CONSTRAINT users_roles_roles_fkey FOREIGN KEY (role_id) REFERENCES roles(id);

INSERT INTO users_roles (user_id, role_id) VALUES (1 , 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2 , 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2 , 1);