create table tarefas (
	id int primary key auto_increment,
    title varchar(50) not null,
    description varchar(100) default null,
    expiration date not null,
    completed bit default false
);

create table users (
	id int primary key auto_increment,
    email varchar(100) not null,
    password varchar(100) not null
);

alter table tarefas add column userid int not null;
alter table tarefas add constraint fk1user foreign key (userid) references users(id);

