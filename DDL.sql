create schema cetriolo;
 
use cetriolo;

create user 'user'@'localhost' identified by 'fatec';

grant select, insert, delete, update on cetriolo.* to user@'localhost';

create table mto_moto (
  mto_id bigint unsigned not null auto_increment,
  mto_placa varchar(20) not null,
  mto_modelo varchar(50) not null,
  primary key (mto_id),
  unique key uni_moto_placa (mto_placa)
);

create table loj_loja (
  loj_id bigint unsigned not null auto_increment,
  loj_nome varchar(20) not null,
  primary key (loj_id),
  unique key uni_loj_nome (loj_nome)
);

create table moj_moto_loja (
  mto_id bigint unsigned not null,
  loj_id bigint unsigned not null,
  primary key (mto_id, loj_id),
  foreign key loj_moto_fk (mto_id) references mto_moto (mto_id) on delete restrict on update cascade,
  foreign key loj_loja_fk (loj_id) references loj_loja (loj_id) on delete restrict on update cascade
);

insert into mto_moto (mto_placa, mto_modelo) values ('CTF-6176','RD 125');

insert into loj_loja (loj_nome) values ('Yamaha');

insert into moj_moto_loja (mto_id,loj_id) values (1,1);