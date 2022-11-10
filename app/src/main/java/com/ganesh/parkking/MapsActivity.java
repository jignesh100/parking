package com.ganesh.parkking;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ganesh.parkking.databinding.ActivityMapsBinding;
import android.Manifest;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.location.LocationRequest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE =101;
    private double lat, lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getCurrentLocation();
        LatLng Charusat = new LatLng(22.60264065549007, 72.82141685268847);
        MarkerOptions mrkop1 = new MarkerOptions().position(Charusat).title("Charusat");
        mMap.addMarker(mrkop1);

        LatLng Mahadev = new LatLng(22.66507726534396, 72.86541872044104);
        MarkerOptions mrkop2 = new MarkerOptions().position(Mahadev).title("Mahadev Mandir");
        mMap.addMarker(mrkop2);

        LatLng Dmart = new LatLng(22.547214732694, 72.92912363969033);
        MarkerOptions mrkop3 = new MarkerOptions().position(Dmart).title("Dmart");
        mMap.addMarker(mrkop3);

        LatLng Pramukh = new LatLng(22.66569271220262, 72.8560682689414);
        MarkerOptions mrkop4 = new MarkerOptions().position(Pramukh).title("Pramukhswami");
        mMap.addMarker(mrkop4);

        LatLng Shantinagar = new LatLng(22.68896063439103, 72.8724401689414);
        MarkerOptions mrkop5 = new MarkerOptions().position(Shantinagar).title("Shantinagar");
        mMap.addMarker(mrkop5);

        LatLng DistJail = new LatLng(22.697011267952966, 72.88093293031103);
        MarkerOptions mrkop6 = new MarkerOptions().position(DistJail).title("District Jail");
        mMap.addMarker(mrkop6);

        LatLng MotaBazaar = new LatLng(22.551058710627295, 72.92341756099584);
        MarkerOptions mrkop7 = new MarkerOptions().position(MotaBazaar).title("Mota Bazaar");
        mMap.addMarker(mrkop7);

        LatLng Indira = new LatLng(22.56341878095304, 72.94695583031103);
        MarkerOptions mrkop8 = new MarkerOptions().position(Indira).title("Indira");
        mMap.addMarker(mrkop8);

        LatLng PostOffice = new LatLng(22.549378996800332, 72.92606469592776);
        MarkerOptions mrkop9 = new MarkerOptions().position(PostOffice).title("Post Office");
        mMap.addMarker(mrkop9);

        LatLng GalleriaMall = new LatLng(22.540516407513177, 72.92499728058537);
        MarkerOptions mrkop10 = new MarkerOptions().position(GalleriaMall).title("Galleria Mall");
        mMap.addMarker(mrkop10);




    }

        private void getCurrentLocation()
        {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            }

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(60000);
            locationRequest.setFastestInterval(5000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationCallback locationCallback= new LocationCallback() {

                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Toast.makeText(getApplicationContext(), "location result is=" + locationRequest, Toast.LENGTH_LONG).show();

                    if (locationResult==null){

//                    Toast.makeText(getApplicationContext(), "Current location is null" + locationRequest,
//                            Toast.LENGTH_LONG).show();

                        return;

                    }

                    for(Location location:locationResult.getLocations() )
                    {
                        if (location!=null)
                        {
//                        Toast.makeText(getApplicationContext(), "Current location is " + location.getLongitude(),
//                                Toast.LENGTH_LONG).show();

                        }

                    }

                }
            };

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);


            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null)
                    {

                        lat=location.getLatitude();
                        lng=location.getLongitude();

                        LatLng latLng= new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));




                    }
                }
            });
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            switch(REQUEST_CODE)
            {
                case REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {
                        getCurrentLocation();
                    }
                    break;


            }

        }

    }