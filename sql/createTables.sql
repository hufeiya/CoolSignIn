use coolSignIn;
create table User
(
	uid int primary key auto_increment,
    username varchar(10),
    pass varchar(10),
    userNo varchar(20),
    phone varchar(11),
    userType bool
    
);
create table StudentSheet
(
	sheetID int primary key auto_increment,
    uid int not null,
    sheetName varchar(20) not null,
    constraint User_Sheet foreign key(uid) references User(uid)
);

create table Student
(
	sid int primary key auto_increment,
    clientID varchar(32),
    sheetID int,
    studentName varchar(10),
    studentNO varchar(20),
    className varchar(20),
    constraint Sheet_Student foreign key(sheetID) references StudentSheet(sheetID)
);
create table Course
(
	cid int primary key auto_increment,
    uid int,
    courseName varchar(20),
    startDates varchar(50),
    numberOfWeeks int,
    constraint User_Course foreign key(uid) references User(uid)
);

create table SignInfo
(
	signID int not null primary key auto_increment,
	cid int not null,
    sid int not null,
    signDetail varchar(100),
    signTimes int,
    lastSignPhoto varchar(50),
    constraint Course_Sign foreign key(cid) references Course(cid),
    constraint Student_Sign foreign key(sid) references Student(sid)
);
alter table User modify column pass varchar(32);//password use MD5
