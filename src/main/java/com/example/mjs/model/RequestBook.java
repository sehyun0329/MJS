package com.example.mjs.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Data
public class RequestBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "isbn")
    private Long isbn;
    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();
    @Column(name = "completion")
    private boolean completion = false;
    @Column(name ="user_id")
    private String user_id;
    @Column(name = "password")
    @Getter(AccessLevel.NONE)
    private String password;

    public String getFormattedDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return this.date.format(formatter);
    }
}
