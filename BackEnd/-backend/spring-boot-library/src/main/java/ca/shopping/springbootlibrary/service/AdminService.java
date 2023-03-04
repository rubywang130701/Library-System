package ca.shopping.springbootlibrary.service;

import ca.shopping.springbootlibrary.dao.BookRepository;
import ca.shopping.springbootlibrary.dao.CheckoutRepository;
import ca.shopping.springbootlibrary.dao.ReviewRepository;
import ca.shopping.springbootlibrary.entity.Book;
import ca.shopping.springbootlibrary.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;
    private CheckoutRepository checkoutRepository;

    @Autowired
    public AdminService (BookRepository bookRepository,
                         ReviewRepository reviewRepository,
                         CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.checkoutRepository = checkoutRepository;
    }
// TODO
//  This is a method to increase the quantity of a book in the library system.
//  It first retrieves the book using its id and if the book is not found, it throws an exception "Book not found".
//  Then it increases the number of copies available and total number of copies of the book.
//  Finally, it saves the updated book information to the database.
    public void increaseBookQuantity(Long bookId) throws Exception {

        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {
            throw new Exception("Book not found");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);
        book.get().setCopies(book.get().getCopies() + 1);

        bookRepository.save(book.get());
    }
// TODO
//  This Java code is a method that decreases the quantity of a book in a library system. It first retrieves the book using the given book ID from the bookRepository object, which is assumed to be a repository for book entities.
//  If the book is not found or if the number of available copies is already zero, it throws an exception with the message "Book not found or quantity locked".
//  Otherwise, it decrements the CopiesAvailable and Copies properties of the book by 1 and saves the updated book information in the repository using the bookRepository.save method.
    public void decreaseBookQuantity(Long bookId) throws Exception {

        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent() || book.get().getCopiesAvailable() <= 0 || book.get().getCopies() <= 0) {
            throw new Exception("Book not found or quantity locked");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        book.get().setCopies(book.get().getCopies() - 1);

        bookRepository.save(book.get());
    }
// TODO
//  This Java code is a method that adds a book to the library system.
//  It creates a new Book object and sets its properties using values from the addBookRequest object,
//  which is assumed to be a request object for adding a book.
//  The properties being set are:
//  Title
//  Author
//  Description
//  Copies
//  CopiesAvailable
//  Category
//  Img
//  Finally, the method saves the new book in the bookRepository object,
//  which is assumed to be a repository for book entities, using the bookRepository.save method.
    public void postBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopies(addBookRequest.getCopies());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        bookRepository.save(book);
    }
// TODO
//  This is a method in Java that deletes a book with a given ID.
//  It first tries to find the book using the bookRepository.findById method and checks if the result is present.
//  If the book is not found, it throws an exception with the message "Book not found".
//  If the book is found, it deletes it from the bookRepository, and also deletes all related checkouts and reviews using checkoutRepository.deleteAllByBookId and reviewRepository.deleteAllByBookId respectively.
    public void deleteBook(Long bookId) throws Exception {

        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {
            throw new Exception("Book not found");
        }

        bookRepository.delete(book.get());
        checkoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }
}
