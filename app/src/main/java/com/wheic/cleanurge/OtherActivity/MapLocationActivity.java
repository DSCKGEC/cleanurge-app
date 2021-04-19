package com.wheic.cleanurge.OtherActivity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wheic.cleanurge.R;

public class MapLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat, lng;
    private Button goBackBtn;
    private String beaconCode, beaconAddress, beaconLevel;
    private TextView beaconCodeText, beaconAddressText, beaconLevelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        lat = getIntent().getDoubleExtra("BeaconLat", 0);
        lng = getIntent().getDoubleExtra("BeaconLng", 0);
        beaconCode = getIntent().getStringExtra("BeaconCode");
        beaconAddress = getIntent().getStringExtra("BeaconAddress");
        beaconLevel = getIntent().getStringExtra("BeaconLevel");

        goBackBtn = findViewById(R.id.goBackBtn);
        beaconCodeText = findViewById(R.id.beaconCodeText);
        beaconAddressText = findViewById(R.id.beaconAddressText);
        beaconLevelText = findViewById(R.id.beaconLevelText);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MapLocationActivity.this, BeaconFragment.class));
                finish();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng beaconLoc = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(beaconLoc).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beaconLoc));

        beaconCodeText.setText("Beacon: " + beaconCode);
        beaconAddressText.setText("Address: " + beaconAddress);
        beaconLevelText.setText("Level: " + beaconLevel);

    }
}