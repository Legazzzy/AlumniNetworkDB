package com.example.alumninetworkcase.services.topic;

import com.example.alumninetworkcase.exceptions.TopicNotFoundException;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.repositories.StudentRepo;
import com.example.alumninetworkcase.repositories.TopicRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class TopicServiceImplements implements TopicService{

    private final TopicRepo topicRepo;
    private final StudentRepo studentRepo;


    public TopicServiceImplements(TopicRepo topicRepo, StudentRepo studentRepo) {
        this.topicRepo = topicRepo;
        this.studentRepo = studentRepo;
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
    public Topic addStudentToTopic (String student_id, Topic topic){
        //Adds first student if set is empty
        if(topic.getStudents() == null){
            Set<Student> students = new HashSet<Student>(){
                {
                    add(studentRepo.findById(student_id).get());
                }
            };
            topic.setStudents(students);
            System.out.println(topic.getStudents());
        }
        return topic;
    }

    @Override
    public boolean isStudentInTopic (String student_id, Topic topic) {
        for (Student stud : topic.getStudents()) {
            if(stud.getId().equals(student_id)) {
                return true;
            }
        }
        return false;
    }
}
