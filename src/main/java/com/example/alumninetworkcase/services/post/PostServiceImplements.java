package com.example.alumninetworkcase.services.post;

import com.example.alumninetworkcase.models.Post;
import com.example.alumninetworkcase.repositories.PostRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class PostServiceImplements implements PostService{

    private final PostRepo postRepo;

    public PostServiceImplements(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public Post findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Post> findAll() {
        return null;
    }

    @Override
    public Post add(Post entity) {
        return null;
    }

    @Override
    public Post update(Post entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
