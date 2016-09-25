# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table budzet (
  id                        bigint not null,
  opis                      varchar(255),
  cifra                     double,
  suma                      double,
  datum                     timestamp,
  constraint pk_budzet primary key (id))
;

create sequence budzet_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists budzet;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists budzet_seq;

