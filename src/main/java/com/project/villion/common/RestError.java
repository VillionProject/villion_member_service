package com.project.villion.common;

import org.springframework.core.StandardReflectionParameterNameDiscoverer;

public class RestError {
    private String id;
    private String message;

    public RestError(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
