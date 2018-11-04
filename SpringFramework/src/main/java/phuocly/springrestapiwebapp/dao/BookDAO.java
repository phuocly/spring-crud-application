package phuocly.springrestapiwebapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phuocly.springrestapiwebapp.dto.BookDTO;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<BookDTO, Integer>{

    @Query(value = "SELECT b.* FROM book b WHERE b.book_name LIKE %:name%", nativeQuery = true)
    List<BookDTO> findBookByName(@Param("name") String name);

    @Query(value = "SELECT b.* FROM book b WHERE b.book_content LIKE %:content%", nativeQuery = true)
    List<BookDTO> findBookByContent(@Param("content") String content);
}
