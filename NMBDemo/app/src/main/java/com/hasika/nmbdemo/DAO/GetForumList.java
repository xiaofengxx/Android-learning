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
        List<Fgroup> fgroups = null;
        try {
            JSONArray jsonArray = new JSONArray(get_Json());
            fgroups = new ArrayList<Fgroup>();
            for(int i = 0 ; i < jsonArray.length(); i++){
                fgroup = new Fgroup();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                fgroup.setId(jsonObject.getString("id"));
                fgroup.setSort(jsonObject.getString("sort"));
                fgroup.setName(jsonObject.getString("name"));
                fgroup.setStatus(jsonObject.getString("status"));
                fgroup.setForums(jsonObject.getString("forums"));
                JSONArray array = jsonObject.getJSONArray("forums");
                List<Forum> list = new ArrayList<Forum>();
                for(int j = 0 ; j < array.length(); j++){
                    JSONObject object = array.getJSONObject(j);
                    Forum forum = new Forum();
                    forum.setId(object.getString("id"));
                    forum.setSort(object.getString("sort"));
                    forum.setName(object.getString("name"));
                    forum.setStatus(object.getString("status"));
                    forum.setStatus(object.getString("showName"));
                    forum.setMsg(object.getString("msg"));
                    forum.setInterval(object.getString("interval"));
                    forum.setCreatedAt(object.getString("createdAt"));
                    forum.setUpdateAt(object.getString("updateAt"));
                    forum.setFgroup(fgroup);
                    list.add(forum);
                }
                fgroup.setForums_list(list);
                fgroups.add(fgroup);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            getCallBcak().run(fgroups);
        }
    }
}
