package com.hasika.nmbdemo.View;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasika.nmbdemo.Bean.The_Type;
import com.hasika.nmbdemo.R;

/**
 * Created by HaSiKa on 2016/6/30.
 */
public class ContentView_Holder implements The_Type{
    private int the_type;
    private View mRootView;
    private SparseArray<View> sparseArray;
    public ContentView_Holder(@NonNull Context context,@LayoutRes int layoutResid){
        this(context,layoutResid,null);
    }
    public ContentView_Holder(@NonNull Context context, @LayoutRes int layoutResid, ViewGroup rootView){
        mRootView  =  View.inflate(context,layoutResid,rootView);
        mRootView.setTag(this);
        getView(R.id.content_item_name).setVisibility(View.GONE);
        getView(R.id.content_item_email).setVisibility(View.GONE);
        getView(R.id.content_item_title).setVisibility(View.GONE);
    }
    public View getmRootView() {
        return mRootView;
    }
    public <V extends View> V getView(@IdRes int viewIdRes){
        if(sparseArray == null)
            sparseArray = new SparseArray<>();
        View view = sparseArray.get(viewIdRes,null);
        if(view == null) {
            view = mRootView.findViewById(viewIdRes);
            sparseArray.put(viewIdRes,view);
        }
        return (V) view;
    }
    public ContentView_Holder setText(@IdRes int viewIdRes,String txt){
        TextView textView = getView(viewIdRes);
        if(textView != null)
            textView.setText(txt);
        return this;
    }
    public ContentView_Holder set_type(int the_type) {
        this.the_type = the_type;
        return this;
    }
    @Override
    public int get_type() {
        return the_type;
    }

    @Override
    public String getName() {
        return null;
    }
}
