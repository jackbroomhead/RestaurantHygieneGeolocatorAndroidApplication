package com.example.androidgeolocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set logo image
        ImageView imageview = findViewById(R.id.mainlogo);
        imageview.setImageResource(R.drawable.foodhyginelogo);

        //opens the activity
        Button button = findViewById(R.id.FindByNearest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    openActivityNearest();
            }
        });
        //opens postcode activity
        Button bpostcode = findViewById(R.id.findByPostCode);
        bpostcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPostcode();

            }
        });
        //opens name activity
        Button bname = findViewById(R.id.findByName2);
        bname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityName();
            }
        });

        Button bnewest = findViewById(R.id.findByRecent);
        bnewest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRecent();
            }
        });






    }

    //sends the long and lat data to the activity nearest class to search via location
    public void openActivityNearest() {

        //required permissions
        String[] requiredPermissions= {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        boolean ok = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this, requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok = false;
            }

        }

        if (!ok) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 1);
            //last parameter mist be > 0 or it fails silently!
            System.exit(0);
        } else {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();

                    String URL = "http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_location&lat=" + lat +"&long=" + lng;
                    Intent intent5 = new Intent(getBaseContext(), ActivityNearest.class);
                    intent5.putExtra("urlkey", URL);
                    startActivity(intent5);
                    // String Latitude = String.valueOf(lat);
                    // String Longitude = String.valueOf(lng);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }

    }
    //sends the user input postcode to the activitypostcode class to search via postcode
    public void openActivityPostcode() {
        Intent intent = new Intent(this, ActivityPostcode.class);
        EditText editText = findViewById(R.id.editTextPostCode);
        String edittextpostcode = editText.getText().toString();
        intent.putExtra("EditTextPostCode", edittextpostcode);
        startActivity(intent);

    }
        //send the user input name to the activityname class to search via name
    public void openActivityName() {
        Intent intent = new Intent(this, ActivityName.class);
        EditText editText = findViewById(R.id.editTextName);
        String edittextname = editText.getText().toString();
        intent.putExtra("EditTextName", edittextname);
        startActivity(intent);

    }

    public void openActivityRecent() {
        Intent intent = new Intent(this, ActivityRecent.class);
        startActivity(intent);
    }


}

