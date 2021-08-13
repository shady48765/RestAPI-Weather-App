package com.amankumar.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
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

    public interface ForecastByIDResponse {
        void onError(String message);


        void onResponse(List<WeatherReportModel> weatherReportModels);
    }


    public void getForecastByid(String code, ForecastByIDResponse forecastByIDResponse){
    List<WeatherReportModel> reportModelList = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_By_ID + code;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");

                    for(int i=0;i<consolidated_weather_list.length();i++) {
                        WeatherReportModel one_day = new WeatherReportModel();
                        JSONObject first_day_data = (JSONObject) consolidated_weather_list.get(i);
                        one_day.setId(first_day_data.getInt("id"));
                        one_day.setWeather_state_name(first_day_data.getString("weather_state_name"));
                        one_day.setWeather_state_abbr(first_day_data.getString("weather_state_abbr"));
                        one_day.setWind_direction_compass(first_day_data.getString("wind_direction_compass"));
                        one_day.setCreated(first_day_data.getString("created"));
                        one_day.setApplication_date(first_day_data.getString("applicable_date"));
                        one_day.setMin_temp(first_day_data.getLong("min_temp"));
                        one_day.setMax_temp(first_day_data.getLong("max_temp"));
                        one_day.setThe_temp(first_day_data.getLong("the_temp"));
                        one_day.setWind_speed(first_day_data.getLong("wind_speed"));
                        one_day.setWind_direction(first_day_data.getLong("wind_direction"));
                        one_day.setAir_pressure(first_day_data.getInt("air_pressure"));
                        one_day.setHumidity(first_day_data.getInt("humidity"));
                        one_day.setVisibility(first_day_data.getLong("visibility"));
                        one_day.setPredictability(first_day_data.getInt("predictability"));
                        reportModelList.add(one_day);
                    }
                    forecastByIDResponse.onResponse(reportModelList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingelton.getInstance(context).addToRequestQueue(request);
    }
}
