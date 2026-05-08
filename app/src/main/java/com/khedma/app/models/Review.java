package com.khedma.app.models;

public class Review {
    private String id;
    private String demandId;
    private float rating;
    private String comment;

    public Review() {
    }

    public Review(String id, String demandId, float rating, String comment) {
        this.id = id;
        this.demandId = demandId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getDemandId() {
        return demandId;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
