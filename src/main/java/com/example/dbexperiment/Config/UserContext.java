package com.example.dbexperiment.Config;

import lombok.Getter;

@Getter
public class UserContext {
    private static final UserContext INSTANCE = new UserContext();
    private String username;

    private UserContext() {}

    public static UserContext getInstance() {
        return INSTANCE;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
