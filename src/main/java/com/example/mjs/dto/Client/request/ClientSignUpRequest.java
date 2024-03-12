package com.example.mjs.dto.Client.request;

import lombok.Getter;
import lombok.Setter;

public class ClientSignUpRequest {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private String pw;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String gender;

    @Getter @Setter
    private String birthday;
}
