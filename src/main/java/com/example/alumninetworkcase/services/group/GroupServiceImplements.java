package com.example.alumninetworkcase.services.group;

import com.example.alumninetworkcase.exceptions.PostNotFoundException;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.repositories.GroupRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class GroupServiceImplements implements GroupService{

    private final GroupRepo groupRepo;

    public GroupServiceImplements(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public Group findById(Integer id) {
        return groupRepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Collection<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public Group add(Group entity) {
        return groupRepo.save(entity);
    }

    @Override
    public Group update(Group entity) {
        return groupRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        groupRepo.deleteById(id);
    }

    @Override
    public void delete(Group entity) {
        groupRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return groupRepo.existsById(id);
    }
}
