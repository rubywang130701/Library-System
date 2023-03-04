package ca.shopping.springbootlibrary.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
@Data
public class Review {
// TODO
//  This is a Java code snippet that defines a class named "Review" with fields that represent the columns of a database table. The fields are annotated with JPA annotations to map the fields to columns in a database table.
//  @Id annotates the id field as the primary key of the table.
//  @GeneratedValue(strategy = GenerationType.IDENTITY) annotates the id field to use an auto-incrementing strategy for generating values.
//  @Column(name = "column_name") annotates the fields to map them to columns with the specified names in the database table.
//  @CreationTimestamp annotates the date field to automatically set the current date and time when a new record is inserted into the table.
//  The other fields (userEmail, rating, bookId, reviewDescription) are also annotated with @Column to map them to columns in the database table with the same names as the fields.
//  These annotations are used by JPA to perform CRUD (Create, Read, Update, Delete) operations on the table in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "date")
    @CreationTimestamp
    private Date date;

    @Column(name = "rating")
    private double rating;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "review_description")
    private String reviewDescription;

}
