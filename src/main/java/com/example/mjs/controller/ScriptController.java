package com.example.mjs.controller;

import com.example.mjs.dto.ScriptDTO;
import com.example.mjs.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/script")
public class ScriptController {
    private final ScriptService scriptService;
    @GetMapping("/")
    public String getScriptPage() {
        return "/script/scriptPage";
    }
    @GetMapping("/save")
    public String saveForm() {
        return "/script/scriptSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ScriptDTO scriptDTO) throws IOException {
        System.out.println("scriptDTO = " + scriptDTO);
        scriptService.save(scriptDTO);
        return "/script/scriptPage";
    }

//    @GetMapping("/")
//    public String findAll(Model model) {
//        List<ScriptDTO> scriptDTOList = scriptService.findAll();
//        model.addAttribute("scriptList", scriptDTOList);
//        return "list";
//    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        scriptService.updateHits(id);
        ScriptDTO scriptDTO = scriptService.findById(id);
        model.addAttribute("script", scriptDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/script/scriptDetail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        ScriptDTO scriptDTO = scriptService.findById(id);
        model.addAttribute("scriptUpdate", scriptDTO);
        return "/script/scriptUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ScriptDTO scriptDTO, Model model) {
        ScriptDTO script = scriptService.update(scriptDTO);
        model.addAttribute("script", script);
        return "/script/scriptDetail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        scriptService.delete(id);
        return "redirect:/script/paging";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<ScriptDTO> scriptList = scriptService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < scriptList.getTotalPages()) ? startPage + blockLimit - 1 : scriptList.getTotalPages();
        model.addAttribute("scriptList", scriptList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/script/scriptPaging";
    }

    @GetMapping("/search")
    public String searchByTitle(String searchTerm, Pageable pageable, Model model) {
        Page<ScriptDTO> searchResults = scriptService.searchByTitle(searchTerm, pageable);

        model.addAttribute("scriptList", searchResults);

        int blockLimit = 5;
        int startPage = ((searchResults.getNumber() / blockLimit) * blockLimit) + 1;
        int endPage = Math.min(startPage + blockLimit - 1, searchResults.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchTerm", searchTerm);

        return "/script/scriptPaging";
    }
}