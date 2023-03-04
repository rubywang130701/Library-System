package ca.shopping.springbootlibrary.dao;

import ca.shopping.springbootlibrary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
// TODO
//  This is a Java method signature for a REST API endpoint.
//  The endpoint will take in a title query parameter in the URL and a Pageable object to specify the pagination information.
//  It returns a Page object containing Book entities whose title contains the given title string.
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
// TODO
//  This is a Java method signature for a REST API endpoint.
//  The endpoint will take in a category query parameter in the URL and a Pageable object to specify the pagination information.
//  It returns a Page object containing Book entities that belong to the given category.
    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);
// TODO
//  This is a Java method signature with a JPA query.
//  The method will fetch a list of Book entities from the database based on the provided book_ids list.
//  The query specifies that the Book entity will be selected where the id of the book is in the provided book_ids list.
//  The book_ids list is passed as a method parameter and is annotated with @Param to specify the name of the parameter in the query.
    @Query("select o from Book o where id in :book_ids")
    List<Book> findBooksByBookIds (@Param("book_ids") List<Long> bookId);
}
