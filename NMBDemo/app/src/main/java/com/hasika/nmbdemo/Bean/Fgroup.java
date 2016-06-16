package com.hasika.nmbdemo.Bean;

import java.util.List;

/**
 * Created by HaSiKa on 2016/6/10.
 */
public class Fgroup {
    private String id;
    private String sort;
    private String name;
    private String status;
    private List<Forum> forums_list;
    private String forums;
    private Boolean is_Fgroup;

    public Boolean isFgroup(){
        return is_Fgroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForums() {
        return forums;
    }

    public void setForums(String forums) {
        this.forums = forums;
    }

    public List<Forum> getForums_list() {
        return forums_list;
    }

    public void setForums_list(List<Forum> forums_list) {
        this.forums_list = forums_list;
    }
}
