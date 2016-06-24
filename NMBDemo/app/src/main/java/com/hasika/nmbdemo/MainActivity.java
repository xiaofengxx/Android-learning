package com.hasika.nmbdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.hasika.nmbdemo.Bean.Fgroup;
import com.hasika.nmbdemo.Control.NetorFile;
import com.hasika.nmbdemo.DAO.GetForumList;
import com.hasika.nmbdemo.DAO.To_Deal;
import com.hasika.nmbdemo.util.MChar;
import com.hasika.nmbdemo.util.MainBrige;
import com.hasika.nmbdemo.util.NMBCallBcak;
import com.hasika.nmbdemo.util.Netget;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new To_Deal(new GetForumList(),new NMBCallBcak(){
            @Override
            public void run(Object... objects) {
                List<Fgroup> fgroups = (List<Fgroup>) objects[0];
            }
        }).start();
        System.out.println("END-------------------------END");

/*        try {
            JSONObject jsonObject = new JSONObject(getString(R.string.test));
            Iterator<String>  iterator = jsonObject.keys();
            while(iterator.hasNext()) {
                String temp = iterator.next();
                System.out.println(temp + "---------" );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }


    public static Context MyContextManager(){
        return context;
    }
}
