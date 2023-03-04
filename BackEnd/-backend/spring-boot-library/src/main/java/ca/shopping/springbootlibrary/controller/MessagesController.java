package ca.shopping.springbootlibrary.controller;

import ca.shopping.springbootlibrary.entity.Message;
import ca.shopping.springbootlibrary.requestmodels.AdminQuestionRequest;
import ca.shopping.springbootlibrary.service.MessagesService;
import ca.shopping.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// TODO
//  This is an annotation for Cross-Origin Resource Sharing (CORS) and allows to configure how the API responds to cross-origin requests.
//  The value "http://localhost:3000" specifies that requests from the origin "http://localhost:3000" will be allowed to access the API resources.
@CrossOrigin("http://localhost:3000")
@RestController
// TODO
//  This line of code is declaring a request mapping for a REST API endpoint with the URL "/api/messages".
//  This means that any HTTP requests sent to this URL will be handled by the API endpoint that is mapped to this URL.
//  This can be used to handle GET, POST, PUT, DELETE, etc. requests from a client and perform the corresponding actions.
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }
// TODO
//  API /secure/add/message
//  This is a method for a REST API endpoint that handles a POST request to add a message.
//  It is secured with a JSON Web Token (JWT) in the Authorization header.
//  The method first extracts the user email from the JWT using the ExtractJWT.payloadJWTExtraction method.
//  Then it calls the messagesService.postMessage method with the message request and the extracted user email.
    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }
// TODO
//  API /secure/admin/message
//  This code is for a REST API endpoint for putting a message in response to a question asked by a user.
//  The request is made by an admin and requires a JSON Web Token (JWT) in the header of the request with key "Authorization".
//  The JWT is extracted and its payload is checked to see if the user is indeed an admin with "userType" value of "admin".
//  If the check fails, an exception with message "Administration page only" is thrown.
//  If the check passes, the "putMessage" method of the "messagesService" object is called with two parameters: the admin's response to the question (in the "AdminQuestionRequest" object) and the user's email extracted from the JWT payload.
    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value="Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only.");
        }
        messagesService.putMessage(adminQuestionRequest, userEmail);
    }

}














