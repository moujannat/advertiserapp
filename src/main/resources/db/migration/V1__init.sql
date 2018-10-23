create table advertiser

(
	name varchar(255) not null,
	contact_name varchar(255) not null,
	credit_limit double,
	primary key(name)
);

insert into advertiser(name,contact_name,credit_limit) values('Mou','Jannatul Mou',3500.00);