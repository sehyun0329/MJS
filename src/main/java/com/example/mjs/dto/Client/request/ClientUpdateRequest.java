package com.example.mjs.dto.Client.request;

import lombok.Getter;
import lombok.Setter;

public class ClientUpdateRequest {

    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String birthday;
}
