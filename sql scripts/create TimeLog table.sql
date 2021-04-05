create table tblTimeLog(
logId int auto_increment primary key,
clockNumber integer not null,
datefield date not null,
dailyIn time,
timeOut time,
timeIn time,
dailyOut time,
notes varchar (50),
approved boolean,
fullcard boolean default false);
