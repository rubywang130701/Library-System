package ca.shopping.springbootlibrary.requestmodels;

import lombok.Data;

import java.util.Optional;
// TODO
//  This is a Java code snippet that defines a class named "ReviewRequest". It is a simple POJO (Plain Old Java Object) class that represents a request to create a new book review.
//  @Data is a Lombok annotation that generates getters, setters, toString, hashCode, and equals methods for all the fields.
//  The fields (rating, bookId, reviewDescription) represent the information needed to create a new book review.
//  The reviewDescription is of type Optional<String>, which means that it can either have a value or be empty.
//  This class can be used as a request model in a RESTful API to create a new review. The properties of an instance of this class can be used to create a new "Review" entity in the database.
@Data
public class ReviewRequest {

    private double rating;

    private Long bookId;

    private Optional<String> reviewDescription;
}
