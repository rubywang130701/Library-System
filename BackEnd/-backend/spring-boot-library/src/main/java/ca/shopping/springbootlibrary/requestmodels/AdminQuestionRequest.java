package ca.shopping.springbootlibrary.requestmodels;

import lombok.Data;
// TODO
//  This is a Java code snippet that defines a class named "AdminQuestionRequest". It is a simple POJO (Plain Old Java Object) class that represents a request to answer an administrative question.
//  @Data is a Lombok annotation that generates getters, setters, toString, hashCode, and equals methods for all the fields.
//  The fields (id, response) represent the information that an administrator needs to provide to answer a question.
//  This class can be used as a request model in a RESTful API to answer a question asked by a user in a library system. The properties of an instance of this class can be used to update an existing "Question" entity in the database with the administrator's response.
@Data
public class AdminQuestionRequest {

    private Long id;

    private String response;
}
