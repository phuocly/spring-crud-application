package phuocly.springrestapiwebapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookDTO {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "book_name")
    @NotEmpty(message = "Can not empty book name")
    private String bookName;

    @Column(name = "book_content")
    @NotEmpty(message = "Can not empty book content")
    private String bookContent;

    public BookDTO() {
    }

    public BookDTO(int bookId, String bookName, String bookContent) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookContent = bookContent;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookContent='" + bookContent + '\'' +
                '}';
    }
}
