# socialMediaBackend
This is sample social media application in java

For any application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case.Usually, applications would start with features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

Technology:
In this exercise, I will create a RESTful API using Spring BOOT and Jpa.

for skipping test cases
-Dmaven.test.skip=true

Create Database Schema in MySQL

INSERT INTO `user` (`user_id`, `create_dt`, `email_id`, `followed`, `friends`, `last_update_dt`, `username`) VALUES
(1, '2018-06-19 08:59:42', 'user1@gmail.com', 'true', 'pavan', '2018-06-19 08:59:42', 'user6'),
(2, '2018-06-19 08:59:44', 'user2@gmail.com', 'true', 'pavan', '2018-06-19 08:59:44', 'user6'),
(3, '2018-06-19 08:59:46', 'user3@gmail.com', 'true', 'pavan', '2018-06-19 08:59:46', 'user6'),
(4, '2018-06-19 08:59:47', 'user4@gmail.com', 'true', 'pavan', '2018-06-19 08:59:47', 'user6'),
(5, '2018-06-19 08:59:48', 'user5@gmail.com', 'true', 'pavan', '2018-06-19 08:59:48', 'user6'),
(6, '2018-06-19 09:00:48', 'user6@gmail.com', 'true', 'pavan', '2018-06-19 09:00:48', 'user6');

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

Relationship Table
The first two fields correspond to the id of the two users.
who is to be related and status represents the relationship status between the two users.

Status Codes
Code	Meaning
0	    Pending
1	    Accepted
2 	    Declined
3    	Blocked


Friend Requiest

INSERT INTO `relationship` (`user_one_id`, `user_two_id`, `status`, `action_user_id`)
VALUES (1, 2, 0, 1)

Accept Friend Requiest

UPDATE `relationship` SET `status` = 1, `action_user_id` = 2
WHERE `user_one_id` = 1 AND `user_two_id` = 2

Checking Friend

SELECT * FROM `relationship`
WHERE `user_one_id` = 1 AND `user_two_id` = 2 AND `status` = 1


Friend List

SELECT * FROM `relationship`
WHERE (`user_one_id` = 1 OR `user_two_id` = 1)
AND `status` = 1

