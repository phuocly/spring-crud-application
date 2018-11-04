package phuocly.springrestapiwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phuocly.springrestapiwebapp.dto.BookDTO;
import phuocly.springrestapiwebapp.service.BookService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/book")
    public String showListBook(Model model) {
        model.addAttribute("listBook", bookService.getAllBook());
        return "BookView";
    }

    @GetMapping(path = "/book/create")
    public String createBook(Model model) {
        model.addAttribute("book", new BookDTO());
        return "ActionBookView";
    }

    @PostMapping(path = "/book/save")
    public String saveAction(@Valid @ModelAttribute("book") BookDTO book,
                             BindingResult result,
                             RedirectAttributes redirect) {
        if(result.hasErrors()) {
            return "ActionBookView";
        }

        if(book.getBookId() < 1) {
            System.out.println("SYSTEM CREATED BOOK");
            bookService.createBook(book);
        }
        else {
            System.out.println("SYSTEM UPDATED BOOK");
            bookService.updateBook(book);
        }
        redirect.addFlashAttribute("success", "Save infomation successfully !");
        return "redirect:/book";
    }

    @GetMapping(path = "/book/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(bookId));
        return "ActionBookView";
    }

    @GetMapping(path = "/book/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") int bookId, RedirectAttributes redirect) {
        bookService.deleteBook(bookId);
        redirect.addFlashAttribute("success", "Deleted successfully !");
        return "redirect:/book";
    }

    @GetMapping(path = "/book/search")
    public String searchBookByName(@RequestParam("searchtext") String searchtext, Model model) {
        if(searchtext.isEmpty()) {
            return "redirect:/book";
        }

        model.addAttribute("listBook", bookService.findBookByName(searchtext));
        return "BookView";
    }
}
