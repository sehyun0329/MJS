package com.example.mjs.service;

import com.example.mjs.model.RequestBook;
import com.example.mjs.repository.RequestBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestBookService {
    @Autowired
    private RequestBookRepository requestBookRepository;
    public void request(RequestBook requestBook){
        requestBookRepository.save(requestBook);
    }

    public Page<RequestBook> getRequestBookList(Pageable pageable){
        return requestBookRepository.findAll(pageable);
    }
    public Page<RequestBook> searchRequestBook(String keyword, String selectOption, Pageable pageable) {
        switch (selectOption) {
            case "title":
                return requestBookRepository.findByBookNameContaining(keyword, pageable);
            case "author":
                return requestBookRepository.findByAuthorContaining(keyword, pageable);
            case "publisher":
                return requestBookRepository.findByPublisherContaining(keyword, pageable);
            default:
                return null;
        }
    }

    public RequestBook getRequestBook(Integer id) {
        return requestBookRepository.findById(id).orElse(null);
    }
    public boolean deleteRequestBook(Integer id, String password){
        int result = requestBookRepository.isPasswordValid(id, password);
        if(result>0) {
            requestBookRepository.deleteById(id);
            return true;
        } else return false;
    }
}
