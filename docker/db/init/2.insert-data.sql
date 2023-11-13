-- delete
--DELETE FROM CHATLOG;
--DELETE FROM JOINROOM;
--DELETE FROM ROOMS;
--DELETE FROM USERS;
--DELETE FROM ROOMADMINS;
--DELETE FROM FAILEDLOG;
--COMMIT;

-- insert
INSERT INTO USERS (USERID, USERNAME, MAILADDRESS, PASSWORD, LASTLOGIN_AT, LOCKED)
    VALUES ('U0000', 'Admin', 'admin@swack.com', 'swack0000', CURRENT_TIMESTAMP, FALSE);
INSERT INTO USERS (USERID, USERNAME, MAILADDRESS, PASSWORD, LASTLOGIN_AT, LOCKED)
    VALUES ('U0001', '情報太郎', 'taro@swack.com', 'swack0001', CURRENT_TIMESTAMP, FALSE);
INSERT INTO USERS (USERID, USERNAME, MAILADDRESS, PASSWORD, LASTLOGIN_AT, LOCKED)
    VALUES ('U0002', '情報花子', 'hanako@swack.com', 'swack0002', CURRENT_TIMESTAMP, FALSE);
INSERT INTO USERS (USERID, USERNAME, MAILADDRESS, PASSWORD, LASTLOGIN_AT, LOCKED)
    VALUES ('U0003', '情報次郎', 'jiro@swack.com', 'swack0003', CURRENT_TIMESTAMP, FALSE);

INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0000', 'everyone', 'U0000', FALSE, FALSE);
INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0001', 'random', 'U0000', FALSE, FALSE);
INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0002', '連絡板', 'U0001', FALSE, TRUE);
INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0003', 'PU0001,U0002', 'U0000', TRUE, TRUE);
INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0004', 'PU0001,U0003', 'U0000', TRUE, TRUE);
INSERT INTO ROOMS (ROOMID, ROOMNAME, CREATEDUSERID, DIRECTED, PRIVATED)
	VALUES ('R0005', 'PU0002,U0003', 'U0000', TRUE, TRUE);

INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0000', 'U0001');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0000', 'U0002');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0000', 'U0003');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0001', 'U0001');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0001', 'U0002');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0002', 'U0001');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0003', 'U0001');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0003', 'U0002');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0004', 'U0001');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0004', 'U0003');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0005', 'U0002');
INSERT INTO JOINROOM (ROOMID, USERID)
	VALUES ('R0005', 'U0003');

INSERT INTO CHATLOG (CHATLOGID, ROOMID, USERID, MESSAGE, CREATED_AT)
	VALUES (nextval('CHATLOGID_SEQ'), 'R0000', 'U0001', 'はじめまして。Swackへようこそ。', CURRENT_TIMESTAMP);
INSERT INTO CHATLOG (CHATLOGID, ROOMID, USERID, MESSAGE, CREATED_AT)
	VALUES (nextval('CHATLOGID_SEQ'), 'R0000', 'U0002', 'こんにちは。よろしくお願いします。', CURRENT_TIMESTAMP);
INSERT INTO CHATLOG (CHATLOGID, ROOMID, USERID, MESSAGE, CREATED_AT)
	VALUES (nextval('CHATLOGID_SEQ'), 'R0001', 'U0001', '雑談をはじめましょう！', CURRENT_TIMESTAMP);
INSERT INTO CHATLOG (CHATLOGID, ROOMID, USERID, MESSAGE, CREATED_AT)
	VALUES (nextval('CHATLOGID_SEQ'), 'R0002', 'U0001', '連絡用の部屋です。', CURRENT_TIMESTAMP);

INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0000', 'U0000');
INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0001', 'U0000');
INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0002', 'U0001');
INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0003', 'U0000');
INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0004', 'U0000');
INSERT INTO ROOMADMINS (ROOMID, USERID)
	VALUES ('R0005', 'U0000');

COMMIT;
