package com.example.alumninetworkcase.services.topic;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.services.CrudService;

public interface TopicService extends CrudService<Topic, Integer> {

    public Topic addStudentToTopic(int student_id, Topic topic);
    public boolean isStudentInTopic(int student_id, Topic topic);
}
