package com.example.alumninetworkcase.services.topic;

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
        return null;
    }

    @Override
    public Collection<Topic> findAll() {
        return null;
    }

    @Override
    public Topic add(Topic entity) {
        return null;
    }

    @Override
    public Topic update(Topic entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Topic entity) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
