create table advertiser

(
	name varchar(255) not null,
	contactName varchar(255) not null,
	creditLimit double,
	primary key(name)
);

insert into advertiser(name,contactName,creditLimit) values('Mou','Jannatul Mou',3500.00);