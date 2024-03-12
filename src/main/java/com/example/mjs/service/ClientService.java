package com.example.mjs.service;

import com.example.mjs.repository.ClientRepository;
import com.example.mjs.model.Client;
import com.example.mjs.dto.Client.request.ClientIDDuplicateRequest;
import com.example.mjs.dto.Client.request.ClientLoginRequest;
import com.example.mjs.dto.Client.request.ClientSignUpRequest;
import com.example.mjs.dto.Client.request.ClientUpdateRequest;
import com.example.mjs.dto.Client.response.ClientResponse;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientResponse loginClient(ClientLoginRequest request){
        Client client = clientRepository.findByIdAndPw(request.getId(),request.getPw());
        if(client!=null){
            ClientResponse response = ClientResponse.toResponse(client);
            return response;
        }else{
            return null;
        }
    }
    public boolean createClient(ClientSignUpRequest request) {
        System.out.println(request.getId());
        clientRepository.save(new Client(request.getId(),request.getPw(),request.getName(),request.getEmail(),request.getGender(),request.getBirthday()));
        Client client = clientRepository.findById(request.getId());
        if(client!=null) return true;
        else return false;
    }

    public void updateClient(ClientUpdateRequest request){

    }

    public boolean checkIDDuplicate(ClientIDDuplicateRequest request) {
        Client client = clientRepository.findById(request.getId());
        if(client!=null)
            return false;
        else return true;
    }
}
