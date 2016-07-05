package com.hasika.nmbdemo.DAO;

import com.hasika.nmbdemo.util.NMBCallBcak;

/**
 * Created by HaSiKa on 2016/6/12.
 */
public  abstract class Need_Deal {

    private String HeaderField,json;
    private NMBCallBcak callBcak;

    public abstract String get_the_Get_Tag();
    public abstract void deal();

    public void set_byJson(String json){
        this.json = json;
    }
    public void set_HeaderField(String headerField){
        this.HeaderField = headerField;
    }
    public void setCallbcak(NMBCallBcak callbcak){
        this.callBcak = callbcak;
    }
    public NMBCallBcak getCallBcak(){
        return callBcak;
    }
    public String get_Json() {
        return json;
    }
    public String get_HeaderField() {
        return HeaderField;
    }

}
