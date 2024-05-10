package com.metehankozan.movieist.service;

import com.metehankozan.movieist.exception.MovieNotFoundException;
import com.metehankozan.movieist.model.Movie;
import com.metehankozan.movieist.model.Review;
import com.metehankozan.movieist.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Movie movie = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("imdbId").is(imdbId)), Movie.class);
        if (movie == null) {
            throw new MovieNotFoundException(imdbId);
        }

        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();

        return review;
    }

    public List<Review> getReviews(List<ObjectId> ids){
        return reviewRepository.findByIdIsIn(ids);
    }
}
