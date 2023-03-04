package ca.shopping.springbootlibrary.dao;

import ca.shopping.springbootlibrary.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessageRepository extends JpaRepository<Message, Long> {
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA that returns a paginated result.
//  The method takes two parameters: "userEmail" of type String and "pageable" of type Pageable.
//  The method returns a Page of "Message" objects.
//  The "@RequestParam" annotation on the "userEmail" parameter indicates that this value will be provided in the query parameters of the HTTP request.
//  The method retrieves a paginated list of "Message" objects for a specific user, identified by the "userEmail" parameter.
//  The "Pageable" parameter is used to specify the pagination information, such as the page number and the page size.
    Page<Message> findByUserEmail(@RequestParam("user_email") String userEmail, Pageable pageable);
// TODO
//  This is a Java method signature for a repository method in Spring Data JPA that returns a paginated result.
//  The method takes two parameters: "closed" of type boolean and "pageable" of type Pageable.
//  The method returns a Page of "Message" objects. The "@RequestParam" annotation on the "closed" parameter indicates that this value will be provided in the query parameters of the HTTP request.
//  The method retrieves a paginated list of "Message" objects where the "closed" field is equal to the "closed" parameter.
//  The "Pageable" parameter is used to specify the pagination information, such as the page number and the page size.
    Page<Message> findByClosed(@RequestParam("closed") boolean closed, Pageable pageable);

}
