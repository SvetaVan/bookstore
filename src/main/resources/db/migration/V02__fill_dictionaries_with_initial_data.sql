insert into genres (genre_name) values ('feature film');--1
insert into genres (genre_name) values ('short film');--2
insert into genres (genre_name) values ('action');--3
insert into genres (genre_name) values ('adventure');--4
insert into genres (genre_name) values ('comedy');--5
insert into genres (genre_name) values ('drama');--6
insert into genres (genre_name) values ('horror');--7
insert into genres (genre_name) values ('thriller');--8
insert into genres (genre_name) values ('animation');--9

insert into authors (author_name) values ('Bram Stoker');
insert into authors (author_name) values ('Mark Twain');
insert into authors (author_name) values ('Oscar Wilde');
insert into authors (author_name) values ('Sir Arthur Conan Doyle');
insert into authors (author_name) values ('Agatha Christie');

insert into books (author_id, genre_id, book_name) values (1, 7, 'Dracula');
insert into books (author_id, genre_id, book_name) values (2, 4, 'The Million Pound Bank Note');
insert into books (author_id, genre_id, book_name) values (3, 1, 'The Picture of Dorian Gray');
insert into books (author_id, genre_id, book_name) values (4, 4, 'The Hound of the Baskervilles');
insert into books (author_id, genre_id, book_name) values (5, 4, 'Appointment With Death');