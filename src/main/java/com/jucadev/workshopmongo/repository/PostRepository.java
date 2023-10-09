package com.jucadev.workshopmongo.repository;

import com.jucadev.workshopmongo.domain.Post;
import com.jucadev.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    
}
