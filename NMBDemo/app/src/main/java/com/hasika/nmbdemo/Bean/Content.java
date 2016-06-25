package com.hasika.nmbdemo.Bean;

import java.util.List;

/**
 * Created by HaSiKa on 2016/6/10.
 */
public class Content implements The_Type{
    private String id;
    private String img;
    private String ext;
    private String now;
    private String userid;
    private String name;
    private String email;
    private String title;
    private String content;
    private Boolean admin;
    private String replyCount;
    private List<Content> replys;
    private int page;

    public Content(){

    }

    public List<Content> getReplys() {
        return replys;
    }

    public void setReplys(List<Content> replys) {
        this.replys = replys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public int get_type() {
        return _Content;
    }
}
