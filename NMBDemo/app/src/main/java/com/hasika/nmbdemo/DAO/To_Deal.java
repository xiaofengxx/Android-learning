package com.hasika.nmbdemo.DAO;

import com.hasika.nmbdemo.DAO.Need_Deal;
import com.hasika.nmbdemo.MainActivity;
import com.hasika.nmbdemo.util.MChar;
import com.hasika.nmbdemo.util.NMBCallBcak;
import com.hasika.nmbdemo.util.Netget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by HaSiKa on 2016/6/10.
 */
public class To_Deal extends Thread{

    private Need_Deal need_deal;
    private String the_result;
    private String params;

    public To_Deal(Need_Deal need_deal,String params,NMBCallBcak callBcak){
        this.need_deal = need_deal;
        need_deal.setCallbcak(callBcak);
        this.params = params;
    }
    public To_Deal(Need_Deal need_deal,NMBCallBcak callBcak){
        this(need_deal,"",callBcak);
    }

    private String  getURL(){
        return need_deal.get_the_Get_Tag()+params;
    }

    @Override
    public void run() {
        String the_head,the_content;
        //the_result = MChar.Unicode_16_(Netget.HTTPSGET(getURL()));
        the_result = Netget.HTTPSGET(getURL());
/*        byte[] bb = the_result.getBytes();
        String path = MainActivity.MyContextManager().getFilesDir().getAbsolutePath();
        path +="/sss.txt";
        File ss = new File(path);
        OutputStream os =null;
        try {
            os = new FileOutputStream(ss);
            os.write(bb,0,bb.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/
        int temp = the_result.indexOf(Netget.split_by);
        the_head = the_result.substring(0,temp);
        the_content = the_result.substring(temp+8,the_result.length());
        need_deal.set_byJson(the_content);
        need_deal.set_HeaderField(the_head);
        need_deal.deal();
    }
}
