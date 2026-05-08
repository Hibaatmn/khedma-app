package com.khedma.app.models;

public class Demand {
    private String id;
    private String title;
    private String status;

    public Demand() {
    }

    public Demand(String id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
