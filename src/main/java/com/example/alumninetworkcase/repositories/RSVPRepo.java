package com.example.alumninetworkcase.repositories;
import com.example.alumninetworkcase.models.Post;
import com.example.alumninetworkcase.models.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSVPRepo extends JpaRepository<RSVP, Integer>{
}
