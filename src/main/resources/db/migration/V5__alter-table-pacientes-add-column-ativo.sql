alter table pacientes add ativo tinyint not null;
update pacientes set ativo = 1;
alter table pacientes modify ativo tinyint not null;
