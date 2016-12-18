create table usertable(
userid varchar2(50),
userpw varchar2(50),
usermail varchar2(50),
userpnum varchar2(50),
USERPICS VARCHAR2(50),
constraint usertb_pk primary key (userid));


create table userlike(
USERID VARCHAR2(50),
 MOVIECD  VARCHAR2(50),
 TITLE            VARCHAR2(1000),
 GENRE           VARCHAR2(50),
 DIRECTOR       VARCHAR2(1000),
 ACTOR              VARCHAR2(1000),
 THUMB              VARCHAR2(4000),
 STORY            VARCHAR2(4000),
 MYTM           VARCHAR2(50));

 create table usercomment( 
 USERID      VARCHAR2(50),
 MOVIECD      VARCHAR2(50),
 USERTEXT       VARCHAR2(4000),
 GRADE          NUMBER(5,2),
 USERPIC       VARCHAR2(200));

