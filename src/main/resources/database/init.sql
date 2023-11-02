create table  movies(id serial primary key, name varchar(100) not null, released_year numeric (4,0),
director_firstname varchar(50), director_lastname varchar(50), genre varchar(30));
create table  directors(id serial primary key, lastname varchar(30) ,
    firstname varchar(30) not null, date_birthday date);
create table  genres(id serial primary key, name varchar (30));
