package com.ouyu.app.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/9/5.
 */
public class QueryTask extends AsyncTask<String, Void, String> {
    Context context;
    TextView tv_result;

    private static final String JUHE_URL_ENVIRONMENT_AIR_PM =
            "http://wscraping.sinaapp.com/";
    //private static final String JUHE_APPKEY = "ƒ„…Í«ÎµƒAPPKEY÷µ";

    public QueryTask(Context context, TextView tv_result) {
        // TODO Auto-generated constructor stub
        super();
        this.context = context;
        this.tv_result = tv_result;
    }

    @Override
    protected String doInBackground(String... params) {
        //String city = params[0];
        String targetUrl = JUHE_URL_ENVIRONMENT_AIR_PM;
        HttpGet httpRequest = new HttpGet(targetUrl);
        try {
            HttpClient httpClient = new DefaultHttpClient();

            HttpResponse httpResponse = httpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String strResult = unescapeUnicode(EntityUtils.toString(httpResponse.getEntity()));
                System.out.println();
                return strResult;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            tv_result.setText(result);
        } else {
            Toast.makeText(context, "≤È—Ø ß∞‹",
                    Toast.LENGTH_LONG).show();
            tv_result.setText("");
        }
    }

    public String unescapeUnicode(String str){
        StringBuffer b=new StringBuffer();
        Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
        while(m.find())
            b.append((char)Integer.parseInt(m.group(1),16));
        return b.toString();
    }

}
