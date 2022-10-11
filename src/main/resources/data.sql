-- Students
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Jack', 'https://upload.wikimedia.org/wikipedia/commons/e/ec/Jack_Nicholson_2001.jpg',
                                                                   'Hotel manager', 'I lived at a hotel once', 'Totally not crazy');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Tony', 'https://upload.wikimedia.org/wikipedia/en/1/19/Tony_Montana_in_Scarface_%281983%29%2C_portrayed_by_Al_Pacino.jpg',
                                                                   'Kingpin', 'Dominated the theaters in the 80s', 'I get really angry when people do not greet my little friend');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Trond. E. Seim', 'https://m.media-amazon.com/images/M/MV5BMTQxNDQ3OTM2Ml5BMl5BanBnXkFtZTcwNTU0MzkxOA@@._V1_.jpg',
                                                                        'Actor', 'I come from Bergen, the rain is my friend', 'Likes solving murder mysteries');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Joelene', 'https://resourceconsulting.com/wp-content/uploads/2022/09/headshot-j-aylor-C.png',
                                                                        'Farmer', 'I dont know how I got here, but Im here now', 'People keep singing my name');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Rihanna', 'https://pbs.twimg.com/profile_images/1133109643734130688/BwioAwkz_400x400.jpg',
                                                                        'Singer', 'I like singing', 'The years at Noroff Accelerate were the best years of my life');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Shakira', 'https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTgwMTgyMTgzOTk3MDg4ODkw/gettyimages-483195065.jpg',
                                                                        'Singer', 'I like singing more', 'Here thanks to my friend Rihanna');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Bill Gates', 'https://upload.wikimedia.org/wikipedia/commons/a/a8/Bill_Gates_2017_%28cropped%29.jpg',
                                                                   'Working with charities', 'My name is Gates, I have earned a lot of money', 'I didnt found this course, but I sure do own it');
INSERT INTO student (name, picture, status, bio, fun_fact) VALUES ('Nic Cage', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6GtcitsAnDUTkujF21rBWXnVCw-7P-SczYYIBzmJyKj0IWRlZn1nEfYvvtqfDYSJdv94&usqp=CAU',
                                                                   'Playing in my 12107543 movie', 'I act like my life depend on it', 'I looked for the holy grail once');


-- AlumniGroups
INSERT INTO alumni_group(description, is_private, name) VALUES ('A club for cool people only', true, 'The Cool Club');
INSERT INTO alumni_group(description, is_private, name) VALUES ('A group for those who love mysteries', true, 'Gloomy True Crime Fans');
INSERT INTO alumni_group(description, is_private, name) VALUES ('For those who love to sing', false, 'Singers of Accelerate');
INSERT INTO alumni_group(description, is_private, name) VALUES ('We like movies', false, 'Accelerate Movie Club');


-- Reference students to groups
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (1,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (2,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (3,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (8,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (3,2);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (5,3);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (6,3);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (3,4);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (8,4);

-- Events
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES (5, 3, 'Singing Contest', 'Singing contest for all accelerate veterans', true, 'img-link',  '2022-10-23', '2022-10-24', '2022-10-10');
INSERT INTO alumni_event(student_id, alumni_group_id, name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES (8, 4, 'Watching my new film', 'Nic Cage invites everyone to view his newest film', true, 'img-link',  '2022-10-23', '2022-10-24', '2022-10-10');

-- Reference students to events
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES (4,1);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES (5,1);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES (3,2);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES (4,2);
INSERT INTO student_alumni_event(student_id, alumni_event_id) VALUES (5,2);

-- Topics
INSERT INTO topic(name, description) VALUES ('Movies', 'This topic is all about movies');
INSERT INTO topic(name, description) VALUES ('Tech', 'The future is now');
INSERT INTO topic(name, description) VALUES ('Politics', 'Stay updated on world events and recent happenings');
INSERT INTO topic(name, description) VALUES ('Economy', 'Make the nest financial decisions');
INSERT INTO topic(name, description) VALUES ('Music', 'Stay hip with the youth by knowing all up and coming artists');

-- Linking students to topics (students have subscribed to these topics)
INSERT INTO student_topic(student_id, topic_id) VALUES (3,1);
INSERT INTO student_topic(student_id, topic_id) VALUES (8,1);
INSERT INTO student_topic(student_id, topic_id) VALUES (7,2);
INSERT INTO student_topic(student_id, topic_id) VALUES (1,3);
INSERT INTO student_topic(student_id, topic_id) VALUES (2,3);
INSERT INTO student_topic(student_id, topic_id) VALUES (7,3);
INSERT INTO student_topic(student_id, topic_id) VALUES (2,4);
INSERT INTO student_topic(student_id, topic_id) VALUES (7,4);
INSERT INTO student_topic(student_id, topic_id) VALUES (8,4);
INSERT INTO student_topic(student_id, topic_id) VALUES (4,5);
INSERT INTO student_topic(student_id, topic_id) VALUES (5,5);
INSERT INTO student_topic(student_id, topic_id) VALUES (6,5);