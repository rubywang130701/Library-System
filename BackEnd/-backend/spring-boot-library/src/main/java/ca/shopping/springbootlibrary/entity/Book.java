package ca.shopping.springbootlibrary.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {
// TODO
//  This is a Java code snippet that defines a class named "Book" with fields that represent the columns of a database table.
//  The fields are annotated with JPA annotations to map the fields to columns in a database table.
//  @Id annotates the id field as the primary key of the table.
//  @GeneratedValue(strategy = GenerationType.IDENTITY) annotates the id field to use an auto-incrementing strategy for generating values.
//  @Column(name = "column_name") annotates the fields to map them to columns with the specified names in the database table.
//  The other fields (title, author, description, copies, copiesAvailable, category, img) are also annotated with @Column to map them to columns in the database table with the same names as the fields.
//  These annotations are used by JPA to perform CRUD (Create, Read, Update, Delete) operations on the table in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "copies")
    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @Column(name = "category")
    private String category;

    @Column(name = "img")
    private String img;
}
