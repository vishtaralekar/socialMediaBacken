# socialMediaBacken
This is sample social media application in java

for skipping test cases
-Dmaven.test.skip=true



INSERT INTO relationship1 (relation_ship_id,ruserid1, ruserid2, status, action_user_id) VALUES
(1,1, 2, 1, 1),
(2,1, 3, 1, 3),
(3,1, 4, 1, 4),
(4,1, 5, 0, 5),
(5,1, 6, 3, 1),
(6,2, 3, 1, 2),
(7,2, 4, 1, 4),
(8,3, 5, 1, 3),
(9,1, 7, 0, 1)


INSERT INTO `user` (`user_id`, `create_dt`, `email_id`, `followed`, `friends`, `last_update_dt`, `username`) VALUES
(1, '2018-06-19 08:59:42', 'user1@gmail.com', 'true', 'pavan', '2018-06-19 08:59:42', 'user6'),
(2, '2018-06-19 08:59:44', 'user2@gmail.com', 'true', 'pavan', '2018-06-19 08:59:44', 'user6'),
(3, '2018-06-19 08:59:46', 'user3@gmail.com', 'true', 'pavan', '2018-06-19 08:59:46', 'user6'),
(4, '2018-06-19 08:59:47', 'user4@gmail.com', 'true', 'pavan', '2018-06-19 08:59:47', 'user6'),
(5, '2018-06-19 08:59:48', 'user5@gmail.com', 'true', 'pavan', '2018-06-19 08:59:48', 'user6'),
(6, '2018-06-19 09:00:48', 'user6@gmail.com', 'true', 'pavan', '2018-06-19 09:00:48', 'user6');