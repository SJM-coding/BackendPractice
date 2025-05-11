package com.rookies3.MySpringBootLab.service;

import com.rookies3.MySpringBootLab.controller.dto.BookDTO.*;
import com.rookies3.MySpringBootLab.entity.Book;
import com.rookies3.MySpringBootLab.exception.BusinessException;
import com.rookies3.MySpringBootLab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    // 도서 등록
    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        Book savedBook = bookRepository.save(request.toEntity());
        return BookResponse.from(savedBook);
    }

    // 전체 조회
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    // ID로 도서 조회
    public BookResponse getBookById(Long id) {
        Book book = findByIdOrThrow(id);
        return BookResponse.from(book);
    }

    // ISBN으로 도서 조회
    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
        return BookResponse.from(book);
    }

    // 도서 정보 수정
    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = findByIdOrThrow(id);

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getPublishDate() != null) book.setPublishDate(request.getPublishDate());
        if (request.getPrice() != null) book.setPrice(request.getPrice());

        return BookResponse.from(book); // 영속성 컨텍스트에 의해 자동 반영됨
    }

    // 삭제
    @Transactional
    public void deleteBook(Long id) {
        Book book = findByIdOrThrow(id);
        bookRepository.delete(book);
    }

    // 공통 예외 처리
    private Book findByIdOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found", HttpStatus.NOT_FOUND));
    }
}
