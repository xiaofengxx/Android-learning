package com.hasika.nmbdemo.DAO;

import com.hasika.nmbdemo.Bean.Fgroup;
import com.hasika.nmbdemo.Bean.Forum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaSiKa on 2016/6/10.
 */
public class GetForumList extends Need_Deal{

    public String get_the_Get_Tag() {
        return "/Api/getForumList";
    }

    public void deal() {
        Fgroup fgroup = null;
        List<Fgroup> fgroups;
        try {
            JSONArray jsonArray = new JSONArray(get_Json());
            fgroups = new ArrayList<Fgroup>();
            for(int i = 0 ; i < jsonArray.length(); i++){


                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            getCallBcak().run(fgroup);
        }
    }
}
