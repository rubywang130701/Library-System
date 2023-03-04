package ca.shopping.springbootlibrary.controller;

import ca.shopping.springbootlibrary.entity.Book;
import ca.shopping.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import ca.shopping.springbootlibrary.service.BookService;
import ca.shopping.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// TODO
//  This is an annotation in Java for enabling Cross-Origin Resource Sharing (CORS).
//  By adding @CrossOrigin with the origin "http://localhost:3000",
//  the endpoint is allowing HTTP requests from the specified origin (in this case "http://localhost:3000") to access its resources.
//  This is useful when making requests to the endpoint from a different domain or port.
//  The use of @CrossOrigin can help to avoid issues with CORS policies that may prevent requests from being processed.
@CrossOrigin("http://localhost:3000")
@RestController
// TODO
//  This is a Java annotation for defining a common URL prefix for a group of endpoint methods.
//  The @RequestMapping annotation with the value /api/books maps all the endpoint methods within the class to URLs that start with /api/books.
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
// TODO
//  API /secure/currentloans
//  This is a Java code fragment for an endpoint that returns the current loans of a user.
//  The endpoint URL is /secure/currentloans and it uses the HTTP GET method.
//  The endpoint expects a token in the header of the request with the key "Authorization".
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "sub" (subject) claim from it,
//  which represents the user email. The bookService.currentLoans method is then invoked, passing the user email as a parameter,
//  and returns a list of ShelfCurrentLoansResponse objects, which represent the current loans of the user.
    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token)
        throws Exception
    {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoans(userEmail);
    }
// TODO
//  API /secure/currentloans/count
//  This is a Java code fragment for an endpoint that returns the count of the current loans of a user.
//  The endpoint URL is /secure/currentloans/count and it uses the HTTP GET method.
//  The endpoint expects a token in the header of the request with the key "Authorization".
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "sub" (subject) claim from it,
//  which represents the user email. The bookService.currentLoansCount method is then invoked,
//  passing the user email as a parameter, and returns an integer, which represents the count of the current loans of the user.
    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }
// TODO
//  API /secure/ischeckedout/byuser
//  This is a Java code fragment for an endpoint that returns whether a user has checked out a book or not.
//  The endpoint URL is /secure/ischeckedout/byuser and it uses the HTTP GET method.
//  he endpoint expects a token in the header of the request with the key "Authorization" and a bookId as a request parameter.
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "sub" (subject) claim from it,
//  which represents the user email. The bookService.checkoutBookByUser method is then invoked,
//  passing the user email and book ID as parameters, and returns a boolean,
//  which represents whether the user has checked out the book or not.
    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,
                                      @RequestParam Long bookId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }
// TODO
//  API /secure/checkout
//  This is a REST API endpoint that handles a PUT request to the URL "/secure/checkout".
//  The API is secured with an authorization token passed as a request header with the key "Authorization".
//  The purpose of this endpoint is to allow a user to checkout a book.
//  The user's email address is extracted from the authorization token using the ExtractJWT.payloadJWTExtraction() method,
//  which takes the token and a string argument representing the key to extract the value for.
//  In this case, the email address is the subject of the JWT, represented by the key "sub".
//  The book ID to be checked out is passed as a request parameter with the key "bookId".
//  The checkoutBook method on the bookService object is then called with the user's email and book ID as arguments.
//  The method returns a Book object, which is the book that was checked out.
    @PutMapping("/secure/checkout")
    public Book checkoutBook (@RequestHeader(value = "Authorization") String token,
                              @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }
// TODO
//  API /secure/return
//  This code defines a RESTful API endpoint for returning a book.
//  It requires a JWT token to be passed in the request header with the key "Authorization".
//  The token is passed to the method ExtractJWT.payloadJWTExtraction(token, "\"sub\"") to extract the user's email from the JWT payload.
//  The extracted email is used as a parameter in the returnBook method of the bookService object.
//  The method returnBook takes two parameters: the user's email and the book's id, and performs the operation of returning a book.
    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value = "Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail, bookId);
    }
// TODO
//  API /secure/renew/loan
//  This is a REST API endpoint using the HTTP PUT method,
//  mapped to the URL "/secure/renew/loan". It takes in a request header "Authorization" which contains a JWT token,
//  and a request parameter "bookId".
//  The token is first passed to the "ExtractJWT.payloadJWTExtraction" method to extract the "sub" claim,
//  which is expected to be the email of the user.
//  The "bookService.renewLoan" method is then called with the extracted email and the bookId to renew the loan for the user.
//  If an exception is thrown, it will propagate up the call stack and needs to be handled by the caller.
    @PutMapping("/secure/renew/loan")
    public void renewLoan(@RequestHeader(value = "Authorization") String token,
                          @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.renewLoan(userEmail, bookId);
    }

}












