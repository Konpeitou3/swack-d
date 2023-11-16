-- drop
--DROP TABLE CHATLOG;
--DROP TABLE JOINROOM;
--DROP TABLE ROOMS;
--DROP TABLE USERS;
--DROP TABLE ROOMADMINS;
--DROP TABLE FAILEDLOG;
--DROP SEQUENCE CHATLOGID_SEQ;

-- create
CREATE TABLE USERS(
  USERID CHAR(5) PRIMARY KEY,
  USERNAME VARCHAR(40) NOT NULL,
  MAILADDRESS VARCHAR(100) UNIQUE NOT NULL,
  PASSWORD VARCHAR(300) NOT NULL,
  LASTLOGIN_AT TIMESTAMP,
  LOCKED BOOLEAN DEFAULT FALSE
);

CREATE TABLE ROOMS(
  ROOMID VARCHAR(5) PRIMARY KEY,
  ROOMNAME VARCHAR(50) UNIQUE NOT NULL,
  CREATEDUSERID CHAR(5) NOT NULL,
  DIRECTED BOOLEAN DEFAULT FALSE,
  PRIVATED BOOLEAN DEFAULT FALSE
);

CREATE TABLE JOINROOM(
  ROOMID VARCHAR(5),
  USERID CHAR(5),
  PRIMARY KEY(ROOMID, USERID)
);

CREATE TABLE CHATLOG(
  CHATLOGID INTEGER PRIMARY KEY,
  ROOMID VARCHAR(5) NOT NULL,
  USERID CHAR(5) NOT NULL,
  MESSAGE VARCHAR(500),
  CREATED_AT TIMESTAMP,
  FOREIGN KEY (ROOMID) REFERENCES ROOMS(ROOMID),
  FOREIGN KEY (USERID) REFERENCES USERS(USERID)
);

CREATE TABLE ROOMADMINS(
  ROOMID VARCHAR(5),
  USERID CHAR(5),
  PRIMARY KEY(ROOMID, USERID)
);

CREATE TABLE FAILEDLOG(
  FAILEDLOGID INTEGER PRIMARY KEY,
  USERID CHAR(5) NOT NULL,
  FAILED_AT TIMESTAMP
);

CREATE SEQUENCE CHATLOGID_SEQ
  START WITH 1
  INCREMENT BY 1;
   
CREATE SEQUENCE FAILLOGID_SEQ
  START WITH 1
  INCREMENT BY 1;
