package com.example.alumninetworkcase.services.rsvp;

import com.example.alumninetworkcase.exceptions.PostNotFoundException;
import com.example.alumninetworkcase.exceptions.RSVPNotFoundException;
import com.example.alumninetworkcase.models.Post;
import com.example.alumninetworkcase.models.RSVP;
import com.example.alumninetworkcase.repositories.PostRepo;
import com.example.alumninetworkcase.repositories.RSVPRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RSVPServiceImplements implements RSVPService {

    private final RSVPRepo rsvpRepo;

    public RSVPServiceImplements(RSVPRepo rsvpRepo) {
        this.rsvpRepo = rsvpRepo;
    }

    @Override
    public RSVP findById(Integer id) {
        return rsvpRepo.findById(id)
                .orElseThrow(() -> new RSVPNotFoundException(id));
    }

    @Override
    public Collection<RSVP> findAll() {
        return rsvpRepo.findAll();
    }

    @Override
    public RSVP add(RSVP entity) {
        return rsvpRepo.save(entity);
    }

    @Override
    public RSVP update(RSVP entity) {
        return rsvpRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        rsvpRepo.deleteById(id);
    }

    @Override
    public void delete(RSVP entity) {
        rsvpRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return rsvpRepo.existsById(id);
    }
}
