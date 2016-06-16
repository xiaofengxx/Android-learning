package com.hasika.nmbdemo.Control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hasika.nmbdemo.util.MD5;
import com.hasika.nmbdemo.util.Netget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HaSiKa on 2016/6/15.
 */
public class NetorFile {
    private String img_path;  // cache中
    private Context context;
    private String save_path;  // files 中
    private String cookies;
    public NetorFile(Context context){
        this.context = context;
        init();
    }
    private void init(){
        img_path = context.getCacheDir().getAbsolutePath()+"/img/";
        File img = new File(img_path);
        if(!img.exists())
            img.mkdirs();
        save_path = context.getFilesDir().getAbsolutePath()+"/save/";
        cookies = save_path + "cookies.dde";
    }

    public Bitmap getImg(String img_url){
        InputStream is = null;
        try {
            is = getImgInputStream(img_url);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
        }finally{
            if(is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    private InputStream getImgInputStream(String img_url){
        InputStream is = null;
        try {
            if(!findImgcache(img_url)){
                addImgcache(img_url, Netget.GetImgInputStream(img_url));
            }
            File img = new File(img_path+MD5.GetMD5Code(img_url));
            is = new FileInputStream(img);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return is;
    }

    private boolean findImgcache(String img_url){
        String img_md5 = MD5.GetMD5Code(img_url);
        File img = new File(img_path+img_md5);
        if(img.exists())
            return true;
        else
            return false;
    }
    private Boolean addImgcache(String img_url,InputStream is){
        OutputStream os  = null;
        File img = new File(img_path + MD5.GetMD5Code(img_url));
        try {
            img.createNewFile();
            os = new FileOutputStream(img);
            byte[] b = new byte[1024*128];
            int len;
            while((len = is.read(b,0,b.length)) != -1)
                os.write(b,0,len);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return true;
    }


}
