package com.example.elin.myapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * Created by elin on 2015-05-01.
 */
public class HttpClient {

    public static JSONObject SendHttpPost(String URL, JSONObject jsonObjSend) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);

            StringEntity se;
            se = new StringEntity(jsonObjSend.toString());

            httpPost.setEntity(se);

            HttpResponse response = (HttpResponse) httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
