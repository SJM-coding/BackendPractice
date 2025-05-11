package com.rookies3.MySpringBootLab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author; //수정대상

    @Column(unique = true, nullable = false)
    private String isbn;

    private Integer price; //수정대상
    private LocalDate publishDate;
}
