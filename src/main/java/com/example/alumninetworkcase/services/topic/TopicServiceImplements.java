package com.example.alumninetworkcase.services.topic;

import com.example.alumninetworkcase.exceptions.TopicNotFoundException;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.repositories.TopicRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class TopicServiceImplements implements TopicService{

    private final TopicRepo topicRepo;

    public TopicServiceImplements(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }

    @Override
    public Topic findById(Integer id) {
        return topicRepo.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
    }

    @Override
    public Collection<Topic> findAll() {
        return topicRepo.findAll();
    }

    @Override
    public Topic add(Topic entity) {
        return topicRepo.save(entity);
    }

    @Override
    public Topic update(Topic entity) {
        return topicRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        topicRepo.deleteById(id);
    }

    @Override
    public void delete(Topic entity) {
        topicRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return topicRepo.existsById(id);
    }

    @Override
    public boolean isStudentInTopic (int student_id, Topic topic) {
        for (Student stud : topic.getStudents()) {
            if(stud.getId() == student_id) {
                return true;
            }
        }
        return false;
    }
}
