package ca.shopping.springbootlibrary.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {

    public Checkout() {}

    public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId) {
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }
// TODO
//  This is a Java code snippet that defines a class named "Checkout" with fields that represent the columns of a database table.
//  The fields are annotated with JPA annotations to map the fields to columns in a database table.
//  @Id annotates the id field as the primary key of the table.
//  @GeneratedValue(strategy = GenerationType.IDENTITY) annotates the id field to use an auto-incrementing strategy for generating values.
//  @Column(name = "column_name") annotates the fields to map them to columns with the specified names in the database table.
//  The other fields (userEmail, checkoutDate, returnDate, bookId) are also annotated with @Column to map them to columns in the database table with the same names as the fields.
//  These annotations are used by JPA to perform CRUD (Create, Read, Update, Delete) operations on the table in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "book_id")
    private Long bookId;
}
