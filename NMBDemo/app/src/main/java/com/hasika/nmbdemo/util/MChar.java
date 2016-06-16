package com.hasika.nmbdemo.util;

/**
 * Created by HaSiKa on 2016/5/19.
 *
 * 待修改
 */
public class MChar {


    /**
    *Unicode-16 转码为 UTF-8
    *@param unicode_String 需要转码的字符串
    * */
    static public String Unicode_16_(String unicode_String){
        if(unicode_String == null)
            return null;
        StringBuffer stringBuilder = new StringBuffer();
        int length = unicode_String.length();
        char point;
        int count = 0;
        for(int i = 0 ;i < length; i++){
            point = unicode_String.charAt(i);
            if('\\' == point&&i+1<length){
                if(i>count){
                    stringBuilder.append(unicode_String.substring(count,i));
                }
                switch (unicode_String.charAt(i+1)){
                    case 'u':
                        i+=5;
                        char u = (char) Integer.parseInt(unicode_String.substring(i-3,i+1), 16);
                        stringBuilder.append(u);
                        break;
                    case '/':
                        i++;
                        stringBuilder.append(unicode_String.charAt(i));
                        break;
                    case '\"':
                        i++;
                        stringBuilder.append(unicode_String.charAt(i));
                        break;
                    default:
                        stringBuilder.append(point);
                        break;
                }
                count = i+1;
            }
        }
        return stringBuilder.toString();
    }


    /**
     *去掉转义符
     * 废弃
     * */
    static public String DelTheChar(String the_String){
        return Unicode_16_(the_String);
    }

}
