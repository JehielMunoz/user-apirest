-- Creación del esquema "core"
create schema if not exists users_data;
create table users_data.user_data (
	user_id bigserial not null,
	name varchar(100) not null,
	mail varchar(100) not null,
	pass varchar(100) not null,
	created_at TIMESTAMP not null,
	updated_at TIMESTAMP null,
	last_login TIMESTAMP null,
	active boolean not null,
	primary key (user_id)
);
create table users_data.phones (
phone_id bigserial not null,
number varchar(100) not null,
city_code varchar(10) not null,
country_code varchar(10) not null,
user_id bigserial,
primary key (phone_id)
);

alter table if exists users_data.phones add constraint fk_user_phone foreign key (user_id) references users_data.user_data;
