drop user 'user'@'localhost';
drop schema cetriolo;

create schema harley;
 
use harley;

create user 'user'@'localhost' identified by 'fatec';

grant select, insert, delete, update on harley.* to user@'localhost';

create table mto_moto (
  mto_id bigint unsigned not null auto_increment,
  mto_placa varchar(20) not null,
  mto_modelo varchar(50) not null,
  primary key (mto_id),
  unique key uni_moto_placa (mto_placa)
);

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table loj_loja (
  loj_id bigint unsigned not null auto_increment,
  loj_nome varchar(20) not null,
  primary key (loj_id),
  unique key uni_loj_nome (loj_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table moj_moto_loja (
  mto_id bigint unsigned not null,
  loj_id bigint unsigned not null,
  primary key (mto_id, loj_id),
  foreign key loj_moto_fk (mto_id) references mto_moto (mto_id) on delete restrict on update cascade,
  foreign key loj_loja_fk (loj_id) references loj_loja (loj_id) on delete restrict on update cascade
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

insert into mto_moto (mto_placa, mto_modelo) values ('CTF-6176','RD 125');

insert into usr_usuario (usr_nome, usr_senha) values ('admin','$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');

insert into loj_loja (loj_nome) values ('Yamaha');

insert into aut_autorizacao (aut_nome) values ('ROLE_ADMIN');

insert into moj_moto_loja (mto_id,loj_id) values (1,1);

insert into uau_usuario_autorizacao (usr_id,aut_id) values (1,1);

