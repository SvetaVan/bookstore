create table authors(
id int not null auto_increment,
author_name varchar2(40 char) not null unique,
primary key (id),
);

create table genres(
id int not null auto_increment,
genre_name varchar2(40 char) not null unique,
primary key (id),
);

create table books(
id int auto_increment primary key,
author_id int not null,
genre_id int not null,
book_name varchar(60 char) not null unique,
foreign key (author_id) references authors(id),
foreign key (genre_id) references genres(id)
);



