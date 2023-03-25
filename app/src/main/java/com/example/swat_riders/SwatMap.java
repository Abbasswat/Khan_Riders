package com.example.swat_riders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.swat_riders.databinding.ActivitySwatMapBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SwatMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySwatMapBinding binding;
    boolean isPermissionGranted;
    ImageView ImageViewSearch;
    EditText InputLocation;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySwatMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageViewSearch = findViewById(R.id.imageViewSearch);
        InputLocation = findViewById(R.id.inputLocation);


        checkPermission();

        if (checkGooglePlaySercices()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            if (isPermissionGranted) {
                checkGps();
            }
        } else {
            Toast.makeText(this, "Google play services is not available", Toast.LENGTH_SHORT).show();
        }

        ImageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = InputLocation.getText().toString();
                if (location == null) {
                    Toast.makeText(SwatMap.this, "Type anyLocation Name", Toast.LENGTH_SHORT).show();


                } else {

                    Geocoder geocoder = new Geocoder(SwatMap.this, Locale.getDefault());
                    try {
                        List<Address> listAddress = geocoder.getFromLocationName(location, 1);
                        if (listAddress.size() > 0) {
                            LatLng swat = new LatLng(listAddress.get(0).getLatitude(), listAddress.get(0).getLongitude());
//                            LatLng swat = new LatLng(-34.79280476799321, 34.79280476799321);
                            mMap.addMarker(new MarkerOptions().position(swat).title("my search position"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(swat));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(swat);
                            mMap.animateCamera(cameraUpdate);
                        } else {
                            Toast.makeText(SwatMap.this, "true", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private boolean checkGooglePlaySercices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(SwatMap.this, "User Cancelled dialog", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

        return false;
    }

    private void checkPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranted = true;
                Toast.makeText(SwatMap.this, "Permission is Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng swat = new LatLng(-34.79280476799321, 34.79280476799321);
        mMap.addMarker(new MarkerOptions().position(swat).title("Marker in Swat"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(swat));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(swat);
        googleMap.animateCamera(cameraUpdate);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//        googleMap.getUiSettings().setCompassEnabled(true);
//        googleMap.getUiSettings().setZoomGesturesEnabled(true);
//        googleMap.getUiSettings().setScrollGesturesEnabled(true);
//        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

    }

    private void checkGps() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority((LocationRequest.PRIORITY_HIGH_ACCURACY));
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true);
        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(getApplicationContext()).
                checkLocationSettings(builder.build());
        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(SwatMap.this, "GPS is Alresdy Enable", Toast.LENGTH_SHORT).show();
                    GetCurrentUpdate();

                    // Request location from device
                } catch (ApiException e) {
                    if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(SwatMap.this, 101);
                        } catch (IntentSender.SendIntentException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                    if (e.getStatusCode() == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                        Toast.makeText(SwatMap.this, "Settings not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void GetCurrentUpdate() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SwatMap.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Toast.makeText(SwatMap.this, "Location"+ locationResult.getLastLocation().getLongitude()+":  "+locationResult.getLastLocation().getLatitude(), Toast.LENGTH_SHORT).show();
            }
        }, Looper.getMainLooper());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==101){
            if (resultCode==RESULT_OK){
                Toast.makeText(this, "Now GPS is enable", Toast.LENGTH_SHORT).show();
            }if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Denied GPS enable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_types,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.noneMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                return true;
            case R.id.NormalMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.SateliteMap:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.MapHybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.MapTerrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}