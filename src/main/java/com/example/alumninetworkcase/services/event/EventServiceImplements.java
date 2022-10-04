package com.example.alumninetworkcase.services.event;

import com.example.alumninetworkcase.exceptions.EventNotFoundException;
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
        return eventRepo.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    public Collection<Event> findAll() {
        return eventRepo.findAll();
    }

    @Override
    public Event add(Event entity) {
        return eventRepo.save(entity);
    }

    @Override
    public Event update(Event entity) {
        return eventRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepo.deleteById(id);
    }

    @Override
    public void delete(Event entity) {
        eventRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return eventRepo.existsById(id);
    }
}
