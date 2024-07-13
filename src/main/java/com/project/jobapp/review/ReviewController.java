package com.project.jobapp.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isAdded = reviewService.addReview(companyId, review);
        if (isAdded)
            return ResponseEntity.ok("Review added successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not added successfully");
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if (review != null)
            return ResponseEntity.ok(review);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,
            @RequestBody Review review) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (isUpdated)
            return ResponseEntity.ok("Review updated successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not updated successfully");
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted)
            return ResponseEntity.ok("Review deleted successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not deleted successfully");
    }
}
