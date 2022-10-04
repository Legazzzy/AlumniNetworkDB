package com.example.alumninetworkcase.services.event;

import com.example.alumninetworkcase.models.Event;
import com.example.alumninetworkcase.repositories.EventRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EventServiceImplements implements EventService{

    private final EventRepo eventRepo;

    public EventServiceImplements(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Event> findAll() {
        return null;
    }

    @Override
    public Event add(Event entity) {
        return null;
    }

    @Override
    public Event update(Event entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Event entity) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
