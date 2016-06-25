package com.hasika.nmbdemo.Bean;

import java.util.List;

/**
 * Created by HaSiKa on 2016/6/10.
 */
public class Forum implements The_Type{
    private String id;
    private String sort;
    private String name;
    private String status;
    private Fgroup fgroup;
    private String showName;
    private String msg;
    private String interval;
    private String createdAt;
    private String updateAt;

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

    public Fgroup getFgroup() {
        return fgroup;
    }

    public void setFgroup(Fgroup fgroup) {
        this.fgroup = fgroup;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public int get_type() {
        return _Forum;
    }
}


