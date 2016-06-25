package com.hasika.nmbdemo.View;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.hasika.nmbdemo.R;

/**
 * Created by HaSiKa on 2016/6/16.
 */
public class ForumView_Holder {
    private SparseArray<View> sparseArray;
    private View mViews;
    public ForumView_Holder(@NonNull Context context, @LayoutRes int layoutResid){
        this(context,layoutResid,null);
    }
    public ForumView_Holder(@NonNull Context context, @LayoutRes int layoutResid,ViewGroup rootview){
        mViews = View.inflate(context,layoutResid,rootview);
        mViews.setTag(this);
        sparseArray = new SparseArray<>();

    }
    public <V extends View> V getView(@IdRes int viewidRes){
        View view = sparseArray.get(viewidRes,null);
        if(view == null) {
            view = mViews.findViewById(viewidRes);
            sparseArray.put(viewidRes,view);
        }
        return (V) view;
    }



}