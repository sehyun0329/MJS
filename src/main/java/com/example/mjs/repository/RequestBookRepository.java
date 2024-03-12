package com.example.mjs.repository;

import com.example.mjs.model.RequestBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBookRepository extends JpaRepository<RequestBook, Integer> {
    Page<RequestBook> findByBookNameContaining(String keyword, Pageable pageable);
    Page<RequestBook> findByAuthorContaining(String keyword, Pageable pageable);
    Page<RequestBook> findByPublisherContaining(String keyword, Pageable pageable);
    @Query(value = "SELECT COUNT(*) FROM request_book WHERE request_id = ?1 AND password = ?2 AND completion = false", nativeQuery = true)
    int isPasswordValid(Integer id, String password);
}
