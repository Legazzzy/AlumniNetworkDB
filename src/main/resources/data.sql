-- Students
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Jack', 'https://upload.wikimedia.org/wikipedia/commons/e/ec/Jack_Nicholson_2001.jpg',
                                                                   'Online', 'I lived at a hotel once', 'Totally not crazy');
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Tony', 'https://upload.wikimedia.org/wikipedia/en/1/19/Tony_Montana_in_Scarface_%281983%29%2C_portrayed_by_Al_Pacino.jpg',
                                                                   'Offline', 'Kingpin', 'I get really angry when people do not greet my little friend');
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Trond. E. Seim', 'https://m.media-amazon.com/images/M/MV5BMTQxNDQ3OTM2Ml5BMl5BanBnXkFtZTcwNTU0MzkxOA@@._V1_.jpg',
                                                                        'Online', 'I come from Bergen, the rain is my friend', 'Likes solving murder mysteries');
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Joelene', 'https://resourceconsulting.com/wp-content/uploads/2022/09/headshot-j-aylor-C.png',
                                                                        'Offline', 'I dont know how I got here, but Im here now', 'People keep singing my name');
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Rihanna', 'https://pbs.twimg.com/profile_images/1133109643734130688/BwioAwkz_400x400.jpg',
                                                                        'Online', 'I like singing', 'The years at Noroff Accelerate were the best years of my life');
INSERT INTO student (stud_name, picture, status, bio, fun_fact) VALUES ('Shakira', 'https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTgwMTgyMTgzOTk3MDg4ODkw/gettyimages-483195065.jpg',
                                                                        'Online', 'I like singing more', 'Here thanks to my friend Rihanna');


-- AlumniGroups
INSERT INTO alumni_group(description, is_private, group_name) VALUES ('A club for cool people only', true, 'The Cool Club');
INSERT INTO alumni_group(description, is_private, group_name) VALUES ('A group for those who love mysteries', true, 'Gloomy True Crime Fans');
INSERT INTO alumni_group(description, is_private, group_name) VALUES ('For those who love to sing', false, 'Singers of Accelerate');


-- Reference students to groups
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (1,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (2,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (3,1);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (3,2);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (5,3);
INSERT INTO student_alumnigroup(student_id, alumnigroup_id) VALUES (6,3);

-- Events
INSERT INTO event(student_id, alumni_group_id, event_name, description, allow_guests, banner_img, start_time, end_time, last_updated) VALUES (5, 3, 'Singing Contest', 'Singing contest for all accelerate veterans', true, 'img-link',  '2022-10-23', '2022-10-24', '2022-10-10')