package com.example.androidgeolocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecent extends AppCompatActivity {

    ListView listView;
    List<Establishment> EstablishmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        listView = findViewById(R.id.list1);

        EstablishmentList = new ArrayList<>();

        establishmentInfoList();
    }

    private void establishmentInfoList(){

        String JSON_URL = "http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=show_recent";

        StringRequest stringrequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject establishmentObject = jarray.getJSONObject(i);

                                Establishment establishments = new Establishment(establishmentObject.getString("BusinessName"),
                                        establishmentObject.getString("PostCode"),
                                        establishmentObject.getString("RatingValue"),
                                        establishmentObject.getString("AddressLine1"),
                                        establishmentObject.getString("AddressLine2"));


                                EstablishmentList.add(establishments);
                            }
                            ListViewAdapter adapter = new ListViewAdapter(EstablishmentList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringrequest);
    }
}
