package com.rookies3.MySpringBootLab.repository;
import com.rookies3.MySpringBootLab.entity.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // 테스트 시 DB에 실제로 반영되지 않도록 롤백 처리됨 (기본값)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    // 도서 등록 테스트
    @Test
    @Rollback(false)
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2025, 5, 7));

        Book saved = bookRepository.save(book);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("스프링 부트 입문");
    }

    // ISBN으로 조회 테스트
    @Test
    void testFindByIsbn() {
        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        assertThat(book.getAuthor()).isEqualTo("홍길동");
    }

    // 저자명으로 조회 테스트
    @Test
    void testFindByAuthor() {
        List<Book> books = bookRepository.findByAuthor("홍길동");
        assertThat(books).isNotEmpty();
    }

    // 도서 정보 수정 테스트
    @Test
    @Rollback(false)
    void testUpdateBook() {
        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        book.setPrice(32000); // 엔티티 값 변경
        // save(book); 안 해도 @Transactional 덕분에 반영됨 (dirty checking)

        assertThat(book.getPrice()).isEqualTo(32000);
    }

    // 도서 삭제 테스트
    @Test
    @Rollback(false)
    void testDeleteBook() {
        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        bookRepository.deleteById(book.getId());

        assertThat(bookRepository.findById(book.getId())).isNotPresent();
    }

    // 존재하지 않는 ISBN으로 조회 시 예외 처리 테스트
    @Test
    void testFindByNotFoundException() {
        Book book = bookRepository.findByIsbn("없는ISBN")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        // assertThat(book).isNull(); ← 이 줄은 필요 없음. 위에서 예외로 끝남
    }

    // Optional + orElseGet 사용 예시
    @Test
    void testOptionalUse() {
        Optional<Book> optionalBook = bookRepository.findByIsbn("9788956746425");
        Book book = optionalBook.orElseGet(() -> new Book());
        assertThat(book.getTitle()).isEqualTo("스프링 부트 입문");

        // 존재하지 않는 ISBN일 경우 빈 Book 객체 반환됨
        Book notFoundBook = bookRepository.findByIsbn("없는ISBN")
                .orElseGet(() -> new Book());
        assertThat(notFoundBook.getTitle()).isNull();
    }
}
