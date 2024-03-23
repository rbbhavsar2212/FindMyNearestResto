package com.example.findmynearestresto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadURL {
    public String retriveURL(String url) throws IOException{
        String urlData = "";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try{
            URL getURL = new URL(url);
            httpURLConnection = (HttpURLConnection) getURL.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            urlData = stringBuffer.toString();
            bufferedReader.close();
        }catch (Exception e){

        }finally {
            inputStream.close();
            httpURLConnection.disconnect();
        }
        return urlData;
    }
}
