package com.travelapi.travelapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String method;

    public Route() {
    }

    public Route(Long id, String path, String method) {
        this.id = id;
        this.path = path;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String route) {
        this.path = route;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
