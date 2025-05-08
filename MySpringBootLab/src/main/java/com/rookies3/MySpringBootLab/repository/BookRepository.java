package com.rookies3.MySpringBootLab.repository;
import com.rookies3.MySpringBootLab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // ISBN으로 조회 (단건)
    Optional<Book> findByIsbn(String isbn);

    // 저자명으로 조회 (복수 가능)
    List<Book> findByAuthor(String author);
}
