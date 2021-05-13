create table tbltimelog(
logId int auto_increment primary key,
clockNumber integer not null,
datefield date not null,
dailyin time,
timeout time,
timein time,
dailyout time,
notes varchar(50),
approved tinyint(1) default 0,
fullcard tinyint(1) default 0,
totalhours Decimal(5,2)
);
