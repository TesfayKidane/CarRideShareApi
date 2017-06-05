 
INSERT INTO Authentication(user,password,enabled) VALUES ('guest','guest', TRUE);
INSERT INTO Authentication(user,password,enabled) VALUES ('admin','admin', TRUE);
 
INSERT INTO Authority (username, authority) VALUES ('guest', 'ROLE_USER');
INSERT INTO Authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO Authority (username, authority) VALUES ('admin', 'ROLE_USER');

INSERT INTO  `USERS` (firstname, lastname,email, version, is_admin,rank, userid) VALUES ('Curious','George','abeni.alex28@gmail.com',0,0,0,'admin');
INSERT INTO `USERS` (firstname, lastname,email,version,is_admin,rank,userid) VALUES ('Allen','Rench','abeni.alex28@gmail.com',0,0,0,'guest');

INSERT INTO `RIDE` (`RIDE_ID`, `AVAILABLE_SEATS`, `CREATED`, `DESCRIPTION`, `MAX_LUGAGGE`, `phone`, `REMAINING_SEATS`, `rideOriginCity`, `rideDestinationCity`, `RIDE_OWNER_ID`, `ridePrice`, `rideOwnerId`, `OBJ_VERSION`) VALUES ('1', '2', '2017-05-26 12:00:00', 'Ride to Iowa City', '3', '1234567890', '2', 'Fairfield', 'Iowa City', '1', '20', '1', '1')
					