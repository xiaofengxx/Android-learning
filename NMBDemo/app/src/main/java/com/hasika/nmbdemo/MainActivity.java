package com.hasika.nmbdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hasika.nmbdemo.Bean.Content;
import com.hasika.nmbdemo.Bean.Fgroup;
import com.hasika.nmbdemo.Bean.Forum;
import com.hasika.nmbdemo.Bean.The_Type;
import com.hasika.nmbdemo.DAO.GetForumList;
import com.hasika.nmbdemo.DAO.ParamsCreat;
import com.hasika.nmbdemo.DAO.ShowForum;
import com.hasika.nmbdemo.DAO.To_Deal;
import com.hasika.nmbdemo.View.ContentView_Holder;
import com.hasika.nmbdemo.View.ForumView_Holder;
import com.hasika.nmbdemo.util.NMBCallBcak;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    private ListView listView;
    private ListView main_listView;
    private static DrawerLayout drawerLayout;
    private static TextView title;
    private MyAdapter myAdapter = new MyAdapter();
    private static Handler handler;
    /**
     * handler the what;
     */
    private static final int SET_ADAPTER  = 1;
    private static final int NEW_CONTENT  = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        title = (TextView) findViewById(R.id.toolbar_title);
        main_listView = (ListView) findViewById(R.id.the_contents);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            View pre;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View view1 = view.findViewById(R.id.forum_item);
                ForumView_Holder forumView_holder = (ForumView_Holder) view1.getTag();
                if(!myAdapter.clickable(position))
                    return;
                pre = myAdapter.getPreView();
                if(pre != null){
                    ((ForumView_Holder)pre.getTag()).H2O2();
                }else{
                    System.out.println("per 是空的");
                }
                // 改变下一个view
                forumView_holder.changed();
                //给mian list 传入值

                // 后续的处理
                myAdapter.setPre(position);
                myAdapter.setPewView(view1);
                CloseDrawerLayout();
            }
        });

    }
    public static Handler MyHandlerManager(){return handler;}
    public static Context MyContextManager(){return context;}
    public static DrawerLayout CloseDrawerLayout(){
        drawerLayout.closeDrawers();
        return drawerLayout;}
    public static TextView ChangeTitle(String txt){
        title.setText(txt);
        return title;
    }

    // Forum
    class MyAdapter extends BaseAdapter{
        int pre;
        int count;
        View preview;
        List<Fgroup> fgroups;

        public MyAdapter(){
            pre = 1;
        }
        public View getPreView(){
            return preview;
        }
        public MyAdapter setPewView(View view){
            this.preview = view;
            return this;
        }
        public void setPre(int pre){
            this.pre = pre;
        }
        public boolean clickable(int position){
            The_Type the_type = getFromposition(position);
            return the_type.get_type() == The_Type._Forum;
        }
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
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         *   WAITING!!!!!!!!!!!!!!!!!!!!!
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //ForumView_Holder forumView_holder = new ForumView_Holder(context,R.layout.forum_item_view);
            ForumView_Holder forumView_holder = null;
            The_Type the_type = getFromposition(position);
            if(convertView == null)
                convertView = new ForumView_Holder(context,R.layout.forum_item_view).set_type(the_type.get_type()).getmRootview();
            else if(((The_Type)convertView.getTag()).get_type() != the_type.get_type())
                convertView = new ForumView_Holder(context,R.layout.forum_item_view).set_type(the_type.get_type()).getmRootview();
            forumView_holder = (ForumView_Holder) convertView.getTag();
            if(position != pre)
                forumView_holder.H2O2();
            else {
                forumView_holder.changed();
                setPewView(forumView_holder.getmRootview());
            }
            forumView_holder.setText(R.id.forum_showname,the_type.getName());
            if(the_type.get_type() == The_Type._Forum)
                forumView_holder.setText(R.id.forum_id,((Forum)the_type).getId());
            else {
                TextView textView = forumView_holder.getView(R.id.forum_showname);
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            return convertView;
        }
        private The_Type getFromposition(int position){
            The_Type the_type = null;
            for(int i = 0; position >= 0 ; i++){
                Fgroup fgroup = fgroups.get(i);
                if(position == 0) {
                    the_type = fgroup;
                    break;
                }
                position -- ;
                if(position < fgroup.getForums_list().size())
                    the_type = fgroup.getForums_list().get(position);
                position -= fgroup.getForums_list().size();
            }
            return the_type;
        }


    }

    // showf
    class MyAdapter_main extends BaseAdapter{
        private List<Content> contents;
        public MyAdapter_main setList(List<Content> contents){
            this.contents = contents;
            return this;
        }
        public List<Content> getContents() {
            return contents;
        }
        @Override
        public int getCount() {
            return contents.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Content content = contents.get(position);
            if(convertView == null)
                convertView = new ContentView_Holder(context,R.layout.content_item_layout).getmRootView();
            ContentView_Holder contentView_holder = (ContentView_Holder) convertView.getTag();
            contentView_holder.setText(R.id.content_item_id,content.getId());
            contentView_holder.setText(R.id.content_item_content,content.getContent());
            contentView_holder.setText(R.id.content_item_userid,content.getUserid());
            return convertView;
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
                    // 开始加载默认的列表 综合1
                    Deal_CONTENT("4",0);
                    break;
                case NEW_CONTENT :
                    main_listView.setAdapter((ListAdapter) msg.obj);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void Deal_CONTENT(final String id, final int page){
        new To_Deal(new ShowForum(), ParamsCreat.getShowf(id, page), new NMBCallBcak() {
            @Override
            public void run(Object... objects) {
                if(objects == null)
                    System.out.println("objects 也tm是空的");
                List<Content> contents = (List<Content>) objects[0];
                List<Content> recontents = null;
                if(contents == null){
                    System.out.println("contents 为空啦");
                    return;
                }
                for(Content content : contents)
                    content.setPage(page);
                MyAdapter_main myAdapter_main = (MyAdapter_main) main_listView.getAdapter();
                if(myAdapter_main == null)
                    myAdapter_main = new MyAdapter_main();
                recontents = myAdapter_main.getContents();
                if(recontents == null)
                    recontents = new ArrayList<Content>();
                int index = recontents.size();
                if(recontents.size() == 0||page < recontents.get(0).getPage())
                    index = 0;
                for(Content content :contents)
                    recontents.add(index ++ ,content);
                Message msg = Message.obtain();
                msg.what = NEW_CONTENT;
                msg.obj = myAdapter_main.setList(recontents);
                handler.sendMessage(msg);
            }
        }).start();
    }

}
