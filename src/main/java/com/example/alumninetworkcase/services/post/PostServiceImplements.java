package com.example.alumninetworkcase.services.post;

import com.example.alumninetworkcase.exceptions.PostNotFoundException;
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
        return postRepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Collection<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post add(Post entity) {
        return postRepo.save(entity);
    }

    @Override
    public Post update(Post entity) {
        return postRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        postRepo.deleteById(id);
    }

    @Override
    public void delete(Post entity) {
        postRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return postRepo.existsById(id);
    }
}
