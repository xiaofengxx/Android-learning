package com.hasika.nmbdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hasika.nmbdemo.Bean.Fgroup;
import com.hasika.nmbdemo.DAO.GetForumList;
import com.hasika.nmbdemo.DAO.To_Deal;
import com.hasika.nmbdemo.View.ForumView_Holder;
import com.hasika.nmbdemo.util.NMBCallBcak;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private ListView listView;
    private MyAdapter myAdapter = new MyAdapter();
    private static Handler handler;
    /**
     * handler the what;
     */
    private static final int SET_ADAPTER  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        handler = new Myhandler();
        new To_Deal(new GetForumList(),new NMBCallBcak(){
            @Override
            public void run(Object... objects) {
                List<Fgroup> fgroups = (List<Fgroup>) objects[0];
                myAdapter.setList(fgroups);
                Message msg = Message.obtain();
                msg.what = SET_ADAPTER;
                msg.obj = myAdapter;
                handler.sendMessage(msg);
            }
        }).start();
        listView = (ListView) findViewById(R.id.forum_list);
    }
    public static Handler MyHandlerManager(){return handler;}
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
            ForumView_Holder forumView_holder = new ForumView_Holder(context,R.layout.forum_item_view);
            return forumView_holder.getmRootview();
        }

    }
    class Myhandler extends Handler{
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case SET_ADAPTER:
                    if(listView == null)
                        this.sendMessageAtTime(msg,100);
                    listView.setAdapter((ListAdapter) msg.obj);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


}
