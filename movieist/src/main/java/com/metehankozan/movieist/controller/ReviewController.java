package com.metehankozan.movieist.controller;

import com.metehankozan.movieist.model.Review;
import com.metehankozan.movieist.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")));
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam List<ObjectId> ids) {
        return new ResponseEntity<>(reviewService.getReviews(ids), HttpStatus.OK);
    }
}
