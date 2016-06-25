package com.hasika.nmbdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

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
    private ListView listView;
    private MyAdapter myAdapter = new MyAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new To_Deal(new GetForumList(),new NMBCallBcak(){
            @Override
            public void run(Object... objects) {
                List<Fgroup> fgroups = (List<Fgroup>) objects[0];
                myAdapter.setList(fgroups);
            }
        }).start();
        listView = (ListView) findViewById(R.id.forum_list);
    }






    public static Context MyContextManager(){
        return context;
    }
    class MyAdapter extends BaseAdapter{
        int count;
        List<Fgroup> fgroups;
        public MyAdapter setList(List<Fgroup> fgroups){
            this.fgroups =  fgroups;
            return init();
        }
        public MyAdapter init(){
            count = 0;
            for(Fgroup f : fgroups){
                count++;
                count += f.getForums_list().size();
            }
            return this;
        }
        @Override
        public int getCount() {
            if(fgroups == null)
                return 0;
            return count;
        }

        @Override
        public Object getItem(int position) {
            System.out.println("GetItem :this is the position" + position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            System.out.println("GetItemId :this is the position" + position);
            return 0;
        }

        /**
         *   WAITING!!!!!!!!!!!!!!!!!!!!!
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

    }
}
