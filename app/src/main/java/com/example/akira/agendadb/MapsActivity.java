package com.example.akira.agendadb;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String localidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        Bundle getBundleLocal = null;
        getBundleLocal = this.getIntent().getExtras();
        localidade = getBundleLocal.getString("endereco");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        Geocoder endereco = new Geocoder(this);
        List<Address> addressList = null;
        try {
            addressList = endereco.getFromLocationName(localidade,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = addressList.get(0);


        LatLng teste = new LatLng(address.getLatitude(),address.getLongitude()); //new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(teste).title(localidade));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(teste,14.0f));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

        map.setMyLocationEnabled(true);

    }
}
