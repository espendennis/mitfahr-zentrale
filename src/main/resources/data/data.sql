

INSERT INTO `users` VALUES 
('admin','Dennis','Espen','dennis.espen@hotmail.com','ROLE_ADMIN','$2a$10$loaPLEOAU2D.KzsjSYF0LOcRugrW9EiXTE4RhiQiuYhBGeBShmqYe','5551234'),
('user1','Dennis','Espen','dennis.espen@hotmail.com','ROLE_USER','$2a$10$k7tf/b4dORKN.NKBrvvegePosEY/3ULZuSH1IHBcBY86o/pdHRCgC','5551234');

INSERT INTO `offers`(starting_point, destination, price, date, username) VALUES 
('Berlin','München',30,'2016-10-10 06:10:00','user1'),
('Frankfurt','Hamburg',30,'2016-10-10 06:20:00','user1'),
('Berlin','München',30,'2016-10-10 06:30:00','admin'),
('Berlin','München',30,'2016-10-10 06:40:00','user1'),
('Berlin','München',40,'2016-10-10 06:50:00','user1');

