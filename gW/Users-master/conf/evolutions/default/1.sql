# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_game primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence game_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

drop sequence if exists user_seq;

