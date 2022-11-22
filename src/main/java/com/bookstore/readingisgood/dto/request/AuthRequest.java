package com.bookstore.readingisgood.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    public AuthRequest()
    {

    }

    public AuthRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
