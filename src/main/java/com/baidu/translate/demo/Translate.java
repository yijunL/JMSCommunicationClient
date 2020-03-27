package com.baidu.translate.demo;

import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;


public class Translate {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20200327000406737";
    private static final String SECURITY_KEY = "0QFJUZ7LwfEsW_Xqn3kB";

    public static String translate(String query,String to) throws UnsupportedEncodingException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String str= api.getTransResult(query, "auto", to);
        JSONObject jsonObject=JSONObject.fromObject(str);
        String ss= jsonObject.getString("trans_result");
        JSONArray jsonArray=JSONArray.fromObject(ss);
        jsonObject=jsonArray.getJSONObject(0);
        return jsonObject.getString("dst");
    }

}
