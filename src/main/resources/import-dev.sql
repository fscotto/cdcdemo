-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into users (id, name, passwd) values(1, 'utente1', 'password');
insert into users (id, name, passwd) values(2, 'utente2', 'password');
insert into users (id, name, passwd) values(3, 'utente3', 'password');
alter sequence users_seq restart with 4;
