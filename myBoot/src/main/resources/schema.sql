

create table room (
	room_id int auto_increment primary key,
	room_name varchar(16) not null,
	room_number char(2) not null unique,
	bed_info char(3) not null
);