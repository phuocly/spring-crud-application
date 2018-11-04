package phuocly.springrestapiwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phuocly.springrestapiwebapp.dao.BookDAO;
import phuocly.springrestapiwebapp.dto.BookDTO;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<BookDTO> getAllBook() {
        return bookDAO.findAll();
    }

    public BookDTO getBookById(int bookId) {
        return bookDAO.getOne(bookId);
    }

    public BookDTO createBook(BookDTO book) {
        return bookDAO.save(book);
    }

    public BookDTO updateBook(BookDTO book) {
        return bookDAO.save(book);
    }

    public void deleteBook(int bookId) {
        bookDAO.deleteById(bookId);
    }

    public List<BookDTO> findBookByName(String name) {
        return bookDAO.findBookByName(name);
    }

    public List<BookDTO> findBookByContent(String content) {
        return bookDAO.findBookByContent(content);
    }
}
