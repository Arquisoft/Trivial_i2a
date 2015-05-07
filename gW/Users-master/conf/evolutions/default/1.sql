# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table board (
  number_of_players         integer)
;

create table game_model (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_game_model primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence game_model_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists board;

drop table if exists game_model;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_model_seq;

drop sequence if exists user_seq;

