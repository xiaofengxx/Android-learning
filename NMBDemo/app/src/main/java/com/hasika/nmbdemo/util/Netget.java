package com.hasika.nmbdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by HaSiKa on 2016/5/18.
 */
public class Netget {

//    https://h.nimingban.com/
//    http://img1.nimingban.com/thumb/
//    http://img1.nimingban.com/image/
    private static final String img_url_thumb = "http://img1.nimingban.com/thumb/";
    private static final String img_url_image = "http://img1.nimingban.com/image/";
    private static final String NMB_host = "https://h.nimingban.com";
    private static final String NMB_AppID = "/appid/TEST_NIMINGBAN";
    public static final String split_by = "/##_1/##";
    public static String HTTPSGET(String param){
        return HTTPSGET(param,false);
    }
    public static String HTTPSGET(String param,boolean head){
        return HTTPSGET(param,head,null);
    }
    public static String HTTPSGET(String param,NMBCallBcak nmbCallBcak){
        return HTTPSGET(param,false,nmbCallBcak);
    }
    public static String HTTPSGET(String param,boolean head,NMBCallBcak nmbCallBcak){
        return HTTPSGET(NMB_host,param,head,nmbCallBcak);
    }
    public static String HTTPSGET(String host,String param,boolean head,NMBCallBcak nmbCallBcak){
        BufferedReader br = null;
        HttpsURLConnection conn = null;
        try {
            URL url = new URL(host+param+NMB_AppID);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("User-Agent","HavfunClient-Nexus-5X");
            /*
            * 回调
            * */
            if(nmbCallBcak != null)
                nmbCallBcak.run(conn);

            if(conn.getResponseCode() == 200){
                StringBuffer stringBuffer = new StringBuffer();
                String key = null;
                if(head != false)
                    for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++){
                    stringBuffer.append(key+":");
                    stringBuffer.append(conn.getHeaderField(key) + "\r\n");
                }
                stringBuffer.append(split_by);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String k;
                while((k = br.readLine()) != null)
                    stringBuffer.append(k + "\r\n");
                return stringBuffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(conn != null)
                conn.disconnect();
        }
        return null;
    }

    public static InputStream GetImgInputStream(String img_url){
        InputStream is = null;
        try {
            URL url =  new URL(img_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            if(conn.getResponseCode() == 200){
                is = conn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return is;
    }
    public static String getImg_url_thumb(String img,String ext){
        return img_url_thumb+ img + ext;
    }
    public static String getImg_url_image(String img,String ext){
        return img_url_image + img + ext;
    }

}
