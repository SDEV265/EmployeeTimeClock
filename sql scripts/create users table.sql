create table tblusers(
userid integer not null AUTO_INCREMENT,
lastname varchar (15),
firstname varchar (15),
username varchar (15),
password varchar (15),
role varchar (15),
inactive varchar(5) Default 'False',
primary key (userid));