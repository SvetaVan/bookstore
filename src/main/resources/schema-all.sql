create table authors(
author_id varchar2(40 char) not null,
author_name varchar2(40 char) not null unique,
primary key (author_id),
);

create table genres(
genre_id varchar2(40 char) not null,
genre_name varchar2(40 char) not null unique,
primary key (genre_id),
);

create table books(
book_id int auto_increment primary key,
author_id varchar2(40 char) not null,
genre_id varchar2(40 char) not null,
book_name varchar(60 char) not null unique,
);
