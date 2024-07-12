create table users(
	u_id serial primary key,
	username varchar(50) unique not null,
	password varchar(50) not null CHECK (LENGTH(password) >=8)

);

create table profiles(
	p_id serial primary key,
	gender boolean not null,
	height double precision not null,
	weight double precision not null,
	activity int not null,
	calorie_goal int not null,
	u_id int references users(u_id) on delete set null
);

create table foods(
	f_id serial primary key,
	food_name varchar(50) unique not null,
	calorie int not null
);

create table custom_foods(
	cf_id serial primary key,
	custom_food varchar(50),
	custom_calorie int
);

create table calorie_track(
	c_id serial primary key,
	f_id int references foods(f_id) on delete set null,
	u_id int references users(u_id) on delete set null,
	serving double precision not null,
	datetime timestamp not null,
	cf_id int references custom_foods(cf_id) on delete set null
);



drop table calorie_track;
drop table profiles;
drop table foods;
drop table users;
drop table custom_foods;


