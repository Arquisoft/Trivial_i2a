# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

<<<<<<< HEAD
create table game (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_game primary key (id))
=======
create table board (
  number_of_players         integer)
;

create table game_model (
  id                        bigint not null,
  game_name                 varchar(255),
  password                  varchar(255),
  constraint pk_game_model primary key (id))
>>>>>>> origin/CrisIntegration
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

<<<<<<< HEAD
create sequence game_seq;
=======
create sequence game_model_seq;
>>>>>>> origin/CrisIntegration

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

<<<<<<< HEAD
drop table if exists game;
=======
drop table if exists board;

drop table if exists game_model;
>>>>>>> origin/CrisIntegration

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

<<<<<<< HEAD
drop sequence if exists game_seq;
=======
drop sequence if exists game_model_seq;
>>>>>>> origin/CrisIntegration

drop sequence if exists user_seq;

