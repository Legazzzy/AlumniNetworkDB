-- Students
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('a','Jack', 'https://upload.wikimedia.org/wikipedia/commons/e/ec/Jack_Nicholson_2001.jpg',
                                                                   'Hotel manager', 'I lived at a hotel once', 'Totally not crazy', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('b','Tony', 'https://upload.wikimedia.org/wikipedia/en/1/19/Tony_Montana_in_Scarface_%281983%29%2C_portrayed_by_Al_Pacino.jpg',
                                                                   'Kingpin', 'Dominated the theaters in the 80s', 'I get really angry when people do not greet my little friend', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('c','Trond. E. Seim', 'https://m.media-amazon.com/images/M/MV5BMTQxNDQ3OTM2Ml5BMl5BanBnXkFtZTcwNTU0MzkxOA@@._V1_.jpg',
                                                                        'Actor', 'I come from Bergen, the rain is my friend', 'Likes solving murder mysteries', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('d','Joelene', 'https://resourceconsulting.com/wp-content/uploads/2022/09/headshot-j-aylor-C.png',
                                                                        'Farmer', 'I dont know how I got here, but Im here now', 'People keep singing my name', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('e','Rihanna', 'https://pbs.twimg.com/profile_images/1133109643734130688/BwioAwkz_400x400.jpg',
                                                                        'Singer', 'I like singing', 'The years at Noroff Accelerate were the best years of my life', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('f','Shakira', 'https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTgwMTgyMTgzOTk3MDg4ODkw/gettyimages-483195065.jpg',
                                                                        'Singer', 'I like singing more', 'Here thanks to my friend Rihanna', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('g','Bill Gates', 'https://upload.wikimedia.org/wikipedia/commons/a/a8/Bill_Gates_2017_%28cropped%29.jpg',
                                                                   'Working with charities', 'My name is Gates, I have earned a lot of money', 'I didnt found this course, but I sure do own it', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('abcdefghijklmnopqrstuvwxyz','Nic Cage', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6GtcitsAnDUTkujF21rBWXnVCw-7P-SczYYIBzmJyKj0IWRlZn1nEfYvvtqfDYSJdv94&usqp=CAU',
                                                                   'Playing in my 12107543 movie', 'I act like my life depend on it', 'I looked for the holy grail once', false);
INSERT INTO student (id, name, picture, status, bio, fun_fact, complete) VALUES ('JohnnyBravo','Johnny Bravo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6GtcitsAnDUTkujF21rBWXnVCw-7P-SczYYIBzmJyKj0IWRlZn1nEfYvvtqfDYSJdv94&usqp=CAU',
                                                                   'Looking good', 'Styling my hair', 'my Hair is mostly made of spaghetti', false);

-- AlumniGroups
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('a', 'A club for cool people only', false, 'The Cool Club');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('b', 'A group for those who love mysteries', true, 'Gloomy True Crime Fans');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('c', 'For those who love to sing', false, 'Singers of Accelerate');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('d', 'We like movies', false, 'Accelerate Movie Club');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('JohnnyBravo', 'Only cool guys', false, 'COOL Guys Club');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('abcdefghijklmnopqrstuvwxyz', 'Action Figures are for us', false, 'Action Figures for Life');
INSERT INTO alumni_group(student_id, description, _private, name) VALUES ('d', 'Al types of animals are allowed here', false, 'Animal Lovers');



-- Reference students to groups
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('a',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('b',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('c',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('d',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('e',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('f',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('g',1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('e',2);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('f',3);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('g',3);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('a',4);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('d',4);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('JohnnyBravo',5);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('d',5);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('b',4);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('abcdefghijklmnopqrstuvwxyz',4);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES ('c',6);


-- Reference students to groups
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('a',1, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('b',1, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('d',1, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('d',1, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('c',2, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('e',3, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('f',3, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('g',4, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('g',4, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('g',3, 'Accepted');

INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('JohnnyBravo',4, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('abcdefghijklmnopqrstuvwxyz',3, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('g',6, 'Accepted');
INSERT INTO membership_invite(student_id, alumni_group_id, status) VALUES ('c',5, 'Accepted');



-- Events
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES ('d', 3, 'Singing Contest', 'Singing contest for all accelerate veterans', true, 'img-link',  '2022-10-23', '2022-10-24', '2022-10-10');
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES ('e', 4, 'Watching my new film', 'Nic Cage invites everyone to view his newest film', true, 'img-link',  '2022-10-23', '2022-10-24', '2022-10-10');

INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES ('JohnnyBravo', 5, 'Racing for flowers', 'Winner gets a rose', true, 'img-link',  '2022-11-23', '2022-11-23', '2022-09-10');
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES ('abcdefghijklmnopqrstuvwxyz', 1, 'Month of Madness', 'Sale on GitHub for 30 days, WILD', true, 'img-link',  '2022-10-29', '2022-11-29', '2022-10-10');
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES ('c', 2, 'Costume Part', 'Coolest costume will get free food', true, 'img-link',  '2022-10-31', '2022-10-31', '2022-10-10');

-- Reference students to events
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('a',1);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('g',1);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('b',2);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('g',2);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('d',2);

INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('JohnnyBravo',4);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('abcdefghijklmnopqrstuvwxyz',4);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES ('a',4);


-- Topics
INSERT INTO topic(name, description) VALUES ('Movies', 'This topic is all about movies');
INSERT INTO topic(name, description) VALUES ('Tech', 'The future is now');
INSERT INTO topic(name, description) VALUES ('Politics', 'Stay updated on world events and recent happenings');
INSERT INTO topic(name, description) VALUES ('Economy', 'Make the nest financial decisions');
INSERT INTO topic(name, description) VALUES ('Music', 'Stay hip with the youth by knowing all up and coming artists');
INSERT INTO topic(name, description) VALUES ('Gaming', 'Gamers unite');
INSERT INTO topic(name, description) VALUES ('Diving', 'Blob blob blob blob');
INSERT INTO topic(name, description) VALUES ('Rock Climbing', 'I think i ca see some mountain goats');


-- Linking students to topics (students have subscribed to these topics)
INSERT INTO student_topic(student_id, topic_id) VALUES ('a',1);
INSERT INTO student_topic(student_id, topic_id) VALUES ('b',1);
INSERT INTO student_topic(student_id, topic_id) VALUES ('c',2);
INSERT INTO student_topic(student_id, topic_id) VALUES ('g',3);
INSERT INTO student_topic(student_id, topic_id) VALUES ('d',3);
INSERT INTO student_topic(student_id, topic_id) VALUES ('a',3);
INSERT INTO student_topic(student_id, topic_id) VALUES ('b',4);
INSERT INTO student_topic(student_id, topic_id) VALUES ('g',4);
INSERT INTO student_topic(student_id, topic_id) VALUES ('d',4);
INSERT INTO student_topic(student_id, topic_id) VALUES ('e',5);
INSERT INTO student_topic(student_id, topic_id) VALUES ('g',5);
INSERT INTO student_topic(student_id, topic_id) VALUES ('f',5);
INSERT INTO student_topic(student_id, topic_id) VALUES ('f',6);
INSERT INTO student_topic(student_id, topic_id) VALUES ('a',6);
INSERT INTO student_topic(student_id, topic_id) VALUES ('g',6);
INSERT INTO student_topic(student_id, topic_id) VALUES ('c',6);
INSERT INTO student_topic(student_id, topic_id) VALUES ('JohnnyBravo',7);
INSERT INTO student_topic(student_id, topic_id) VALUES ('f',7);
INSERT INTO student_topic(student_id, topic_id) VALUES ('d',8);
INSERT INTO student_topic(student_id, topic_id) VALUES ('c',8);
INSERT INTO student_topic(student_id, topic_id) VALUES ('g',8);


-- Links events to topics
INSERT INTO alumni_event_topic(alumni_event_id, topic_id) VALUES (1,5);
INSERT INTO alumni_event_topic(alumni_event_id, topic_id) VALUES (2,1);

-- Posts
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('b',1, '2022-10-24', 'Hello guys!');
INSERT INTO post(sender_student_id, reply_post_id, alumni_group_id, timestamp, content) VALUES ('b',1,1, '2022-10-24', 'Lol, you always beat us Tony!');
INSERT INTO post(sender_student_id, reply_post_id, alumni_group_id, timestamp, content) VALUES ('a',2,1, '2022-10-24', 'I know, being so cool makes you speedy');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('b',1, '2022-10-24', 'Hey man!');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('c',1, '2022-10-24', 'How are you doing!');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('b',1, '2022-10-24', 'Reeeeel good man, where is Rihanna?');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('e',1, '2022-10-24', 'Im here, just joined, stoked to have our own group');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('d',1, '2022-10-24', 'Yeah, its nice... ');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('b',1, '2022-10-24', 'So what you guys doing tonight?');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('g',1, '2022-10-24', 'Nothing much');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('g',1, '2022-10-24', 'Down to watch a movie at my place?');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('b',1, '2022-10-24', 'Im super down!');
INSERT INTO post(sender_student_id, alumni_group_id, timestamp, content) VALUES ('c',1, '2022-10-24', 'Sorry, I have a concert today :(');
