package ca.shopping.springbootlibrary.dao;

import ca.shopping.springbootlibrary.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review, Long> {
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA that returns a paginated result.
//  The method takes two parameters: "bookId" of type Long and "pageable" of type Pageable.
//  The method returns a Page of "Review" objects. The "@RequestParam" annotation on the "bookId" parameter indicates that this value will be provided in the query parameters of the HTTP request.
//  The method retrieves a paginated list of "Review" objects for a specific book, identified by the "bookId" parameter.
//  The "Pageable" parameter is used to specify the pagination information, such as the page number and the page size.
    Page<Review> findByBookId(@RequestParam("book_id") Long bookId,
                              Pageable pageable);
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA that returns a single "Review" object.
//  The method takes two parameters: "userEmail" of type String and "bookId" of type Long.
//  The method returns a single "Review" object. The method retrieves a "Review" object for a specific user and a specific book,
//  identified by the "userEmail" and "bookId" parameters respectively.
//  If multiple "Review" objects exist for the same user and book,only the first one is returned.
//  If no matching "Review" is found,the method returns null.
    Review findByUserEmailAndBookId(String userEmail, Long bookId);
// TODO
//  This is a JPA (Java Persistence API) query method to delete records from a table named "Review" in a database.
//  The query deletes all records where the value of the "book_id" column is in a set of values specified by the method parameter "book_id".
//  The parameter is annotated with @Param to indicate its value should be bound to the named parameter ":book_id" in the query.
    @Modifying
    @Query("delete from Review where book_id in :book_id")
    void deleteAllByBookId(@Param("book_id") Long bookId);
}
