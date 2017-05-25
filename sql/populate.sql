 
INSERT INTO Authentication(user,password,enabled) VALUES ('guest','guest', TRUE);
INSERT INTO Authentication(user,password,enabled) VALUES ('admin','admin', TRUE);
 
INSERT INTO authority (username, authority) VALUES ('guest', 'ROLE_USER');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_USER');

INSERT INTO  `USERS` (firstname, lastname,email, version, is_admin,rank, userid) VALUES ('Curious','George','abeni.alex28@gmail.com',0,0,0,'admin');
INSERT INTO `USERS` (firstname, lastname,email,version,is_admin,rank,userid) VALUES ('Allen','Rench','abeni.alex28@gmail.com',0,0,0,'guest');

--INSERT INTO `ride` (`RIDE_ID`, `AVAILABLE_SEATS`, `DESCRIPTION`, `MAX_LUGAGGE`, `phone`, `REMAINING_SEATS`, `rideOriginCity`, `rideDestinationCity`, `RIDE_OWNER_ID`, `ridePrice`, `rideOwnerId`, `OBJ_VERSION`) VALUES ('1', '2', 'aa', '3', 'ss', '2', 'Faifield', 'Ottumwa', '1', '23', '1', '1')
					