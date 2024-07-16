create table users(
	u_id serial primary key,
	username varchar(50) unique not null,
	password varchar(2084) not null CHECK (LENGTH(password) >=8),
	role varchar(50) not null

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

create table food(
	f_id serial primary key,
	food_name varchar(50) unique not null,
	calories int not null
);

create table calorie_track(
	c_id serial primary key,
	f_id int references food(f_id) on delete set null,
	u_id int references users(u_id) on delete set null,
	serving double precision not null,
	log_date date not null DEFAULT CURRENT_DATE
);

drop table calorie_track;
drop table profiles;
drop table food;
drop table calorie_track;
drop table users;

select * from users;
select * from profiles;
select * from food;
select * from calorie_track;