create table authors(
author_id int not null auto_increment,
author_name varchar2(40 char) not null unique,
primary key (author_id),
);

create table genres(
genre_id int not null auto_increment,
genre_name varchar2(40 char) not null unique,
primary key (genre_id),
);

create table books(
book_id int auto_increment primary key,
author_id int not null,
genre_id int not null,
book_name varchar(60 char) not null unique,
foreign key (author_id) references authors(author_id),
foreign key (genre_id) references genres(genre_id)
);

create table comment(
id int auto_increment primary key,
book_id int not null,
comments varchar(200 char),
foreign key (book_id) references books(book_id)
);