package ca.shopping.springbootlibrary.controller;

import ca.shopping.springbootlibrary.requestmodels.AddBookRequest;
import ca.shopping.springbootlibrary.service.AdminService;
import ca.shopping.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//  The @RequestMapping annotation with the value /api/admin maps all the endpoint methods within the class to URLs that start with /api/admin.
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
// TODO
//  API /secure/increase/book/quantity
//  This is a Java code fragment that implements an endpoint for increasing the quantity of a book.
//  The endpoint expects a token in the header of the request with the key "Authorization".
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "userType" from it.
//  If the extracted "userType" is not equal to "admin", an Exception is thrown with the message "Administration page only".
//  If the extracted "userType" is equal to "admin", the endpoint invokes the adminService.increaseBookQuantity method, passing the bookId as a parameter.
    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
    }
// TODO
//  API /secure/decrease/book/quantity
//  This is a Java code fragment for a similar endpoint for decreasing the quantity of a book.
//  The code is almost identical to the previous code fragment,
//  except for the endpoint URL (/secure/decrease/book/quantity) and the method being invoked (adminService.decreaseBookQuantity).
//  The endpoint expects a token in the header of the request with the key "Authorization".
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "userType" from it.
//  If the extracted "userType" is not equal to "admin", an Exception is thrown with the message "Administration page only".
//  If the extracted "userType" is equal to "admin", the endpoint invokes the adminService.decreaseBookQuantity method, passing the bookId as a parameter.
    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.decreaseBookQuantity(bookId);
    }
// TODO
//  API /secure/add/book
//  This is a Java code fragment for an endpoint for adding a new book.
//  The endpoint URL is /secure/add/book and it uses the HTTP POST method.
//  The endpoint expects a token in the header of the request with the key "Authorization" and a addBookRequest object in the request body.
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "userType" from it. If the extracted "userType" is not equal to "admin", an Exception is thrown with the message "Administration page only".
//  If the extracted "userType" is equal to "admin", the endpoint invokes the adminService.postBook method, passing the addBookRequest object as a parameter.
    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value="Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
    }
// TODO
//  API /secure/delete/book
//  This is a Java code fragment for an endpoint for deleting a book.
//  The endpoint URL is /secure/delete/book and it uses the HTTP DELETE method.
//  The endpoint expects a token in the header of the request with the key "Authorization" and a bookId in the request parameters.
//  The token is then passed to the ExtractJWT.payloadJWTExtraction method to extract the "userType" from it.
//  If the extracted "userType" is not equal to "admin", an Exception is thrown with the message "Administration page only".
//  If the extracted "userType" is equal to "admin", the endpoint invokes the adminService.deleteBook method, passing the bookId as a parameter.
    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
    }

}












