package com.hasika.nmbdemo.Bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private int replyCount;
    private List<Content> replys;
    private int page;

    public Content(){
        replyCount = -1;
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

    public void setAdmin(int admin) {
        this.admin = !(admin == 0);
    }

    public int getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    @Override
    public int get_type() {
        if(Integer.valueOf(replyCount).intValue() <0)
            return _Content_normal;
        return _Content_root;
    }

    public static Content getThread(JSONObject jsonObject){
        Content content = null;
        try {
            content = getContent(jsonObject);
            content.setReplyCount(jsonObject.getInt("replyCount"));
            if(content.getReplyCount() <= 0)
                return content;
            JSONArray jsonArray = jsonObject.getJSONArray("replys");
            List<Content> list = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.length() ; i++ ){
                list.add(getContent(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(content == null)
            System.out.println("有点奇怪");
        return content;
    }
    public static Content getContent(JSONObject jsonObject){
        Content content = new Content();
        try {
            content.setId(jsonObject.getString("id"));
            content.setImg(jsonObject.getString("img"));
            content.setExt(jsonObject.getString("ext"));
            content.setNow(jsonObject.getString("now"));
            content.setUserid(jsonObject.getString("userid"));
            content.setName(jsonObject.getString("name"));
            content.setEmail(jsonObject.getString("email"));
            content.setTitle(jsonObject.getString("title"));
            content.setContent(jsonObject.getString("content"));
            content.setAdmin(jsonObject.getInt("admin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;
    }


}
