package com.rookies3.MySpringBootLab.controller;
import com.rookies3.MySpringBootLab.entity.Book;
import com.rookies3.MySpringBootLab.exception.BusinessException;
import com.rookies3.MySpringBootLab.repository.BookRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // [POST] 도서 등록
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // [GET] 전체 도서 목록
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = bookRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // [GET] ID로 도서 조회 - BusinessException 적용
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // [GET] ISBN으로 도서 조회 - BusinessException 적용
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // [PUT] 도서 정보 수정 - BusinessException 적용
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrice(updatedBook.getPrice());
        book.setPublishDate(updatedBook.getPublishDate());

        Book saved = bookRepository.save(book);
        return ResponseEntity.ok(saved);
    }

    // [DELETE] 도서 삭제 - BusinessException 적용
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}