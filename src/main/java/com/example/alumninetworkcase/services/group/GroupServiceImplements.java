package com.example.alumninetworkcase.services.group;

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
        return null;
    }

    @Override
    public Collection<Group> findAll() {
        return null;
    }

    @Override
    public Group add(Group entity) {
        return null;
    }

    @Override
    public Group update(Group entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Group entity) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
