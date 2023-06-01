create table usuario(
	idusuario		serial			primary key,
	nome			varchar(150)	not null,
	email			varchar(100)	not null unique,
	senha			varchar(40)		not null
);

create table categoria(
	idcategoria		serial			primary key,
	nome			varchar(100)	not null,
	tipo			integer	        not null,
	idusuario       integer         not null,
	foreign key(idusuario) references usuario(idusuario)
);

create table conta(
	idconta			serial          primary key,
	nome            varchar(150)    not null,
	valor			numeric(18, 2)  not null,
	data            date            not null,
	observacoes     varchar(500)    null,
	idcategoria     integer         not null,
	idusuario       integer         not null,
	foreign key(idcategoria) references categoria(idcategoria),
	foreign key(idusuario) references usuario(idusuario)
);


