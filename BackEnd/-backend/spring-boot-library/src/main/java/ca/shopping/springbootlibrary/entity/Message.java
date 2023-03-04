package ca.shopping.springbootlibrary.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
public class Message {

    public Message(){}

    public Message(String title, String question) {
        this.title = title;
        this.question = question;
    }
// TODO
//  This is a Java code snippet that defines a class named "Ticket" with fields that represent the columns of a database table. The fields are annotated with JPA annotations to map the fields to columns in a database table.
//  @Id annotates the id field as the primary key of the table.
//  @GeneratedValue(strategy = GenerationType.IDENTITY) annotates the id field to use an auto-incrementing strategy for generating values.
//  @Column(name = "column_name") annotates the fields to map them to columns with the specified names in the database table.
//  The other fields (userEmail, title, question, adminEmail, response, closed) are also annotated with @Column to map them to columns in the database table with the same names as the fields.
//  These annotations are used by JPA to perform CRUD (Create, Read, Update, Delete) operations on the table in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="title")
    private String title;

    @Column(name="question")
    private String question;

    @Column(name="admin_email")
    private String adminEmail;

    @Column(name="response")
    private String response;

    @Column(name="closed")
    private boolean closed;
}













