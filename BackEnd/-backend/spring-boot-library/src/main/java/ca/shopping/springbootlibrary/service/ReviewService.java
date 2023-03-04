package ca.shopping.springbootlibrary.service;

import ca.shopping.springbootlibrary.dao.ReviewRepository;
import ca.shopping.springbootlibrary.entity.Review;
import ca.shopping.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
// TODO
//  This is a Java method for posting a review for a book.
//  The method first checks if the review has already been created for the same book by the same user (using the user's email and book id).
//  If so, it throws an exception with the message "Review already created."
//  If not, it creates a new Review object with the information from the ReviewRequest object and sets the date to the current date.
//  Finally, it saves the review to the repository.
    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }
// TODO
//  This is a Java method for checking if a review for a book has been created by a user (specified by email).
//  It retrieves a review from the repository using the user's email and book id, and if it finds a review,
//  it returns true. If not, it returns false.
    public Boolean userReviewListed(String userEmail, Long bookId) {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateReview != null) {
            return true;
        } else {
            return false;
        }
    }

}









