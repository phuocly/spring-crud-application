package phuocly.springrestapiwebapp.RESTcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phuocly.springrestapiwebapp.dto.BookDTO;
import phuocly.springrestapiwebapp.service.BookService;

import java.util.List;
import java.util.Objects;

@RestController
public class BookRESTController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/api/book")
    public ResponseEntity<List<BookDTO>> getAllBook() {
        List<BookDTO> listBook = bookService.getAllBook();
        return new ResponseEntity<List<BookDTO>>(listBook, HttpStatus.OK);
    }

    @GetMapping(path = "/api/book/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") int bookId) {
        BookDTO book = bookService.getBookById(bookId);
        return new ResponseEntity<BookDTO>(book, HttpStatus.OK);
    }

    @PostMapping(path = "/api/book")
    public ResponseEntity<Void> createBook(@RequestBody BookDTO book) {
        try {
            bookService.createBook(book);
        }
        catch(Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/api/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") int bookId, @RequestBody BookDTO bookRequest) {
        BookDTO book = bookService.getBookById(bookId);
        if(Objects.isNull(book)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        book.setBookName(bookRequest.getBookName());
        book.setBookContent(bookRequest.getBookContent());

        try {
            bookService.updateBook(book);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        try {
            bookService.deleteBook(bookId);
        }
        catch(Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(path = "/api/book/content/{bookContent}")
    public ResponseEntity<List<BookDTO>> findBookByContent(@PathVariable("bookContent") String bookContent) {
        List<BookDTO> listBook = bookService.findBookByContent(bookContent);
        return new ResponseEntity<List<BookDTO>>(listBook, HttpStatus.OK);
    }
}
