package com.rookies3.MySpringBootLab.controller;
import com.rookies3.MySpringBootLab.entity.Book;
import com.rookies3.MySpringBootLab.exception.BusinessException;
import com.rookies3.MySpringBootLab.repository.BookRepository;
import com.rookies3.MySpringBootLab.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.rookies3.MySpringBootLab.controller.dto.BookDTO.BookResponse;
import com.rookies3.MySpringBootLab.controller.dto.BookDTO.BookCreateRequest;
import com.rookies3.MySpringBootLab.controller.dto.BookDTO.BookUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // [POST] 도서 등록
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request) {
        BookResponse saved = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // [GET] 전체 도서 목록
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // [GET] ID로 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // [GET] ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    // [PUT] 도서 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,
                                                   @Valid @RequestBody BookUpdateRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // [DELETE] 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
