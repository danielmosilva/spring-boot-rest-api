alter table medicos add ativo tinyint not null;
update medicos set ativo = 1;
alter table medicos modify ativo tinyint not null;
