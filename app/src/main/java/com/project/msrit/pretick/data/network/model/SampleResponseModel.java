package com.project.msrit.pretick.data.network.model;

/**
 * Created by dhamini-poorna-chandra on 26/11/2017.
 */

public class SampleResponseModel {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public SampleResponseModel withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SampleResponseModel withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SampleResponseModel withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SampleResponseModel withBody(String body) {
        this.body = body;
        return this;
    }
}
