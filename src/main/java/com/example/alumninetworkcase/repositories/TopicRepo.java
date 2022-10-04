package com.example.alumninetworkcase.repositories;

import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Integer> {
}
