CREATE TABLE customers (
	id bigserial NOT NULL,
	name varchar(255) NOT NULL,
	cpf varchar(11) NOT NULL,
	street varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	region varchar(255) NOT NULL,
	postal_code varchar(10) NOT NULL,
	country varchar(255) NOT NULL,
	CONSTRAINT customers_cpf_uk UNIQUE (cpf),
	CONSTRAINT customers_pkey PRIMARY KEY (id)
);