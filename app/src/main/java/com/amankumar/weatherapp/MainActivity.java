package com.amankumar.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        //UI reference of textView
        final AutoCompleteTextView LocationsAdapter = findViewById(R.id.locationTextView);

        // create list of customer
        ArrayList<String> Locationlist = getLocationList();
        ArrayList<String> LocationCode = getLocationListcode();
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, Locationlist){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ((TextView) v).setTextSize(20) ;
                ((TextView) v) .setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);

                return v;
            }
        };

        //Set adapter
        LocationsAdapter.setAdapter(adapter);

        //submit button click event registration
        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View  view) {
                int index = ((Locationlist.indexOf(LocationsAdapter.getText().toString())));
                if (index == -1) {
                    Toast.makeText(MainActivity.this, "Incorrect Option", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,LocationCode.get(index) , Toast.LENGTH_SHORT).show();
                    testing();
                }
            }

        });
    }

    private void testing() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.metaweather.com/api/location/search/?query=london";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this,"Response is: "+ response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"ERR", Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private ArrayList<String> getLocationListcode() {
        ArrayList<String> Location_code = new ArrayList<>();
        Location_code.add("2295402");
        Location_code.add("2295420");
        Location_code.add("2295424");
        Location_code.add("20070458");
        Location_code.add("2295414");
        Location_code.add("2295386");
        Location_code.add("2295411");
        Location_code.add("2295412");
        Location_code.add("2295405");
        Location_code.add("2295410");
        return Location_code;
    }

    private ArrayList<String> getLocationList() {
        ArrayList<String> Locations = new ArrayList<>();


        Locations.add("Ahmedabad");
        Locations.add("Bangalore");
        Locations.add("Chennai");
        Locations.add("Delhi");
        Locations.add("Hyderabad");
        Locations.add("Kolkata");
        Locations.add("Mumbai");
        Locations.add("Pune");
        Locations.add("Surat");
        Locations.add("Thane");

        return Locations;
    }
}