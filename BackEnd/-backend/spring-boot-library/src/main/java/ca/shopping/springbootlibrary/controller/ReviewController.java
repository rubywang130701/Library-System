package ca.shopping.springbootlibrary.controller;

import ca.shopping.springbootlibrary.requestmodels.ReviewRequest;
import ca.shopping.springbootlibrary.service.ReviewService;
import ca.shopping.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;
// TODO
//  This is a CORS(Cross-Origin Resource Sharing) annotation for a REST API endpoint in a Spring Boot application.
//  It specifies that the API is allowing requests from the origin "http://localhost:3000".
//  This allows a client, such as a single-page application, running on that domain to make requests to this API.
//  Without this, the browser would block such requests due to the same-origin policy.
@CrossOrigin("http://localhost:3000")
@RestController
// TODO
//  The "@RequestMapping" annotation in this example is a Spring MVC annotation used to map a web request to a specific handler method.
//  In this case, the annotation maps all requests that match the "/api/reviews" URL pattern to a specific handler method.
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }
// TODO
//  API /secure/user/book
//  This code is a Java method in a Spring MVC web application.
//  It is annotated with "@GetMapping" to handle HTTP GET requests to the "/secure/user/book" URL.
//  The method has two parameters:"token" is passed in as a request header with the name "Authorization".
//  "bookId" is passed in as a request parameter.
//  The method performs the following actions:
//  Extracts the user email from the "Authorization" header using the "ExtractJWT" class.
//  If the extracted user email is null, it throws an exception with the message "User email is missing".
//  Returns the result of calling the "reviewService.userReviewListed" method, which checks if the user has reviewed the book with the given ID.
//  The method returns a Boolean value indicating if the user has reviewed the book.
    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }
// TODO
//  API /secure
//  This is a Java code snippet for a RESTful API endpoint.
//  The endpoint is mapped to the URL "/secure" using the @PostMapping annotation, and it is accessible only via a secure HTTP POST request.
//  The postReview method takes in two parameters, a token from the request header with the key "Authorization" and a review request in the request body.
//  The token parameter is used to extract the user's email from the JSON Web Token (JWT) payload using the ExtractJWT.
//  payloadJWTExtraction method. If the extracted email is null, an exception with the message "User email is missing" is thrown.
//  Finally, the reviewService.postReview method is called with the user's email and the review request as arguments.
    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}
