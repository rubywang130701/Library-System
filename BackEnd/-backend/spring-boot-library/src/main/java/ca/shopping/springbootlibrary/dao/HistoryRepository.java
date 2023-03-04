package ca.shopping.springbootlibrary.dao;

import ca.shopping.springbootlibrary.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface HistoryRepository extends JpaRepository<History, Long> {
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA that returns a paginated result.
//  The method takes two parameters: "userEmail" of type String and "pageable" of type Pageable.
//  The method returns a Page of "History" objects.
//  The "@RequestParam" annotation on the "userEmail" parameter indicates that this value will be provided in the query parameters of the HTTP request.
//  The method retrieves a paginated list of "History" objects for a specific user, identified by the "userEmail" parameter.
//  The "Pageable" parameter is used to specify the pagination information, such as the page number and the page size.
    Page<History> findBooksByUserEmail(@RequestParam("email") String userEmail, Pageable pageable);
}
