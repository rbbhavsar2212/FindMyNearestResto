package com.example.findmynearestresto;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class FetchData extends AsyncTask<Object,String,String> {
    String googleNearByPlace;
    GoogleMap googleMap;
    String url;

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jParam = new JSONObject();
            JSONArray jsonArray = jParam.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject getLocation = jsonObject.getJSONObject("geometry").getJSONObject("location");
                String lat = getLocation.getString("lat");
                String lang = getLocation.getString("lng");

                JSONObject getName = jsonArray.getJSONObject(i);
                String name = getName.getString("name");

                LatLng latLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(lang));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(name);
                markerOptions.position(latLng);
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            }
        }catch (Exception e){
            
        }
    }

    @Override
    protected String doInBackground(Object... objects) {
        try {
            googleMap = (GoogleMap) objects[0];
            url = (String) objects[1];
            Log.d("url", "doInBackground: "+url);
            DownLoadURL downLoadURL = new DownLoadURL();
             googleNearByPlace = downLoadURL.retriveURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleNearByPlace;
    }
}
