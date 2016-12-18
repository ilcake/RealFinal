drop table movieboxinfo;

create table movieboxinfo(
moviecd varchar2(30),
movienm varchar2(50),
directornm varchar2(50),
opendt varchar2(30),
rank number(5),
constraint movieb_pk primary key (moviecd)
);