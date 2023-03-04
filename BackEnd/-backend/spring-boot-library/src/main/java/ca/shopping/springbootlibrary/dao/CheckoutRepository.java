package ca.shopping.springbootlibrary.dao;

import ca.shopping.springbootlibrary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA.
//  The method takes two parameters: "userEmail" of type String and "bookId" of type Long.
//  The method returns an object of the type this repository is managing.
//  The name "findByUserEmailAndBookId" implies that this method will find and return a specific record from the repository based on a combination of the "userEmail" and "bookId" fields.
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA.
//  The method takes a single parameter "userEmail" of type String.
//  The method returns a list of objects of type "Checkout".
//  The name "findBooksByUserEmail" implies that this method will find and return a list of "Checkout" objects for a specific user,
//  identified by the "userEmail" parameter.
    List<Checkout> findBooksByUserEmail(String userEmail);
// TODO
//  This is a Java method that uses the @Query annotation to specify a custom delete operation in Spring Data JPA.
//  The method is annotated with @Modifying to indicate that this is a modifying query,
//  and @Query to specify the SQL statement that should be executed.
//  The method takes a single parameter "bookId" annotated with @Param,
//  which is the ID of the book to delete. The method returns void as it is expected to just perform the deletion operation and return nothing.
//  The query deletes all records from the "Checkout" table where the "book_id" field is equal to the "bookId" parameter.
    @Modifying
    @Query("delete from Checkout where book_id in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
