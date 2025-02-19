package com.metehankozan.movieist.repository;

import com.metehankozan.movieist.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    List<Review> findByIdIsIn(List<ObjectId> ids);
}
