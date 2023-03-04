package ca.shopping.springbootlibrary.requestmodels;

import lombok.Data;
// TODO
//  This is a Java code snippet that defines a class named "AddBookRequest". It is a simple POJO (Plain Old Java Object) class that represents a request for adding a book to a library system.
//  @Data is a Lombok annotation that generates getters, setters, toString, hashCode, and equals methods for all the fields.
//  The fields (title, author, description, copies, category, img) represent the information that a client needs to provide to add a book to the library system.
//  This class can be used as a request model in a RESTful API to create a new book in the library system. The properties of an instance of this class can be used to create a new Book entity in the database.

@Data
public class AddBookRequest {

    private String title;

    private String author;

    private String description;

    private int copies;

    private String category;

    private String img;

}
