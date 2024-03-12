package com.example.mjs.dto.Client.response;

import com.example.mjs.model.Client;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ClientResponse {

    private String id;
    private String pw;
    private String name;
    private String email;
    private String gender;
    private String birthday;


    public static ClientResponse toResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setPw(client.getPw());
        response.setName(client.getName());
        response.setBirthday(client.getBirthday());
        response.setGender(client.getGender());
        response.setEmail(client.getEmail());
        return response;
    }
}
