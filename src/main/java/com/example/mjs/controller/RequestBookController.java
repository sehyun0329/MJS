package com.example.mjs.controller;

import com.example.mjs.model.Client;
import com.example.mjs.model.RequestBook;
import com.example.mjs.service.RequestBookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestBookController {
    @Autowired
    private RequestBookService requestBookService;
    @GetMapping("/requestBook")
    public String requestBookForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("loginClient");

        if(client != null){
            String id = client.getId();
            model.addAttribute("id", id);
            return "/requestBook/requestBook";
        } else{
            model.addAttribute("result",false);
            model.addAttribute("message", "로그인 후에 신청해주세요");
            return "message/loginMessage";
        }

    }
    @GetMapping("/requestBookList")
    public String listRequestBook(Model model, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        Page<RequestBook> list = requestBookService.getRequestBookList(pageable);
        int pageNum = list.getPageable().getPageNumber();
        int lastPage = list.getTotalPages();
        long totalNum = list.getTotalElements();
        model.addAttribute("list", list);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("lastPage",lastPage);
        model.addAttribute("totalNum", totalNum);
        return "/requestBook/requestBookList";
    }
    @PostMapping("/requestBook/request")
    public String requestBook(RequestBook requestBook, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        requestBookService.request(requestBook);
        return "redirect:/requestBookList";
    }
    @GetMapping("/requestBookList/search")
    public String searchRequestBook(@RequestParam String keyword,@RequestParam String selectOption , Model model, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        Page<RequestBook> list = requestBookService.searchRequestBook(keyword, selectOption, pageable);
        int pageNum = list.getPageable().getPageNumber();
        int lastPage = list.getTotalPages();
        long totalNum = list.getTotalElements();
        model.addAttribute("list", list);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("lastPage",lastPage);
        model.addAttribute("totalNum",totalNum);
        return "/requestBook/requestBookList";
    }
    @GetMapping("/requestBookDetail")
    public String listRequestBookDetail(@RequestParam Integer id, Model model){
        RequestBook requestBook = requestBookService.getRequestBook(id);
        model.addAttribute("requestBook", requestBook);
        return "/requestBook/requestBookDetail";
    }

    @PostMapping("/requestBookDetail/delete")
    public String checkRequestBookDelete(Model model, @RequestParam Integer id, String password){
        boolean result = requestBookService.deleteRequestBook(id, password);
        model.addAttribute("result", result);
        model.addAttribute("id",id);
        if(result) model.addAttribute("message","삭제되었습니다");
        else model.addAttribute("message", "비밀번호가 틀렸습니다");
        return "/message/deleteMessage";
    }
}
