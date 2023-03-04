package ca.shopping.springbootlibrary.responsemodels;

import ca.shopping.springbootlibrary.entity.Book;
import lombok.Data;
// TODO
//  This is a Java code snippet that defines a class named "ShelfCurrentLoansResponse". This class represents the response returned by a service that provides the current loans of a user's shelf.
//  The class has two fields: book and daysLeft.
//  The book field is an instance of the Book class and represents the details of a book that has been checked out.
//  The daysLeft field is an integer and represents the number of days left until the book is due to be returned.
//  This class can be used as a response model in a RESTful API to provide the details of the books that have been checked out by a user. The properties of an instance of this class can be used to return the information about a book and the number of days left until it is due.
@Data
public class ShelfCurrentLoansResponse {

    public ShelfCurrentLoansResponse(Book book, int daysLeft) {
        this.book = book;
        this.daysLeft = daysLeft;
    }

    private Book book;

    private int daysLeft;
}
