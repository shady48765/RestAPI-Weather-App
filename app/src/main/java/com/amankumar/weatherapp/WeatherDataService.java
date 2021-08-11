package com.amankumar.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService  {
    private Context context;
    public static final String QUERY_FOR_CITY_WEATHER_By_ID ="https://www.metaweather.com/api/location/";
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(Object response);
    }
    public WeatherDataService(Context context) {
        this.context = context;

    }
    public void getForecastByid(String code, VolleyResponseListener volleyResponseListener){
    List<WeatherReportModel> reportModelList = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_By_ID + code;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingelton.getInstance(context).addToRequestQueue(request);
    }
}
