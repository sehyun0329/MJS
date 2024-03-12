package com.example.mjs.controller;

import com.example.mjs.model.Client;
import com.example.mjs.dto.Client.request.ClientLoginRequest;
import com.example.mjs.dto.Client.request.ClientSignUpRequest;
import com.example.mjs.dto.Client.response.ClientResponse;
import com.example.mjs.repository.ClientRepository;
import com.example.mjs.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientRepository clientRepository;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "mainPage/loginPage";
    }
    @GetMapping("/signUpPage")
    public String signUpPage(){
        return "mainPage/signUpPage";
    }

    @PostMapping("/login")
    public String loginClient(@ModelAttribute ClientLoginRequest request, HttpSession session, Model model){
        System.out.println("Controller.login");
        ClientResponse response = clientService.loginClient(request);
        System.out.println(session.getId());
        if(response!=null){
            session.setAttribute("loginClient", new Client(response.getId(), response.getPw(), response.getName(), response.getEmail(), response.getGender(), response.getBirthday()));
            Client client = (Client) session.getAttribute("loginClient");
            System.out.println(client.getName());
            model.addAttribute("message","Login success");
            model.addAttribute("result", true);
        } else{
            model.addAttribute("message","Login failed");
            model.addAttribute("result", false);
        }
        return "message/loginMessage";
    }

    @GetMapping("/checkSession")
    public ResponseEntity<String> checkSession(HttpServletRequest request){
        Client loginClient = (Client) request.getSession().getAttribute("loginClient");
        if(loginClient != null){
            return ResponseEntity.ok("logged-in");
        }else{
            return ResponseEntity.ok("logged-out");
        }
    }
    @GetMapping("/logout")
    public String logOut(HttpSession session,Model model){
        session.removeAttribute("loginClient");
        model.addAttribute("message", "success to Log out");
        model.addAttribute("result", true);
        return "message/loginMessage";
    }


    @PostMapping("/signUp")
    public String createClient(@ModelAttribute ClientSignUpRequest request, Model model){
        Client client = clientRepository.findById(request.getId());
        if(client==null) {
            clientRepository.save(new Client(request.getId(), request.getPw(), request.getName(), request.getEmail(), request.getGender(), request.getBirthday()));
            client = clientRepository.findById(request.getId());
        }else{
            model.addAttribute("message", "already exists id");
            model.addAttribute("result", false);
        }
        if(client!=null){
            model.addAttribute("message", "success");
            model.addAttribute("result", true);
        }
        else{
            model.addAttribute("message", "fail");
            model.addAttribute("result", false);
        }
        return "message/loginMessage";
    }

}
