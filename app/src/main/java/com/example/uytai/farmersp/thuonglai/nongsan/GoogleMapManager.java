package com.example.uytai.farmersp.thuonglai.nongsan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.uytai.farmersp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 09/07/2018
 */
public class GoogleMapManager implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnSuccessListener<Location>, GoogleMap.OnMarkerClickListener {
    private static final String TAG = GoogleMapManager.class.getName();

    private Context mContext;
    private GoogleMap mGoogleMap;
    private MapEventListener mMapEvent;
    private MapView mMapView;
    private GoogleApiClient mGoogleApiClient;
    //private MarkerManager mMarkerManager;
    public LatLng mCurrentLocation;
    private LatLng mDestLocation;
    private Polyline mPolyline;
    public FusedLocationProviderClient mFusedLocationClient;

    private String distanceDetail;
    private String durationDetail;
    //private DetailLocation mLocation;
    private MapEventClickMarkerListener mMapEventClickMarkerListener;

    //Data test
    private ArrayList<LatLng> coordinates;


    public GoogleMapManager(Context context, MapView mapView) {
        this.mContext = context;
        this.mMapView = mapView;
        mMapView.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);


        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

//    public void customStyleMap() {
//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = mGoogleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            mContext, R.raw.mapstyle));
//
//            if (!success) {
//                Log.e(TAG, "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e(TAG, "Can't find style. Error: ", e);
//        }
//    }

    public void setMapEventClickMarkerListener(MapEventClickMarkerListener mMapEventClickMarkerListener) {
        this.mMapEventClickMarkerListener = mMapEventClickMarkerListener;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
       // customStyleMap();
        mGoogleApiClient.connect();
      //  mMarkerManager = new MarkerManager(mGoogleMap);
        intData();
        addBoxMarker();
        mGoogleMap.setOnMarkerClickListener(this);
//        mGoogleMap.setOnMarkerClickListener(marker -> {
//            mDestLocation = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
//            if (mPolyline != null)
//                mPolyline.remove();
//
//            // Getting URL to the Google Directions API
//            String url = getDirectionsUrl(mCurrentLocation, mDestLocation);
//            DownloadTask downloadTask = new DownloadTask();
//            downloadTask.execute(url);
//            return true;
//        });
    }

    public GoogleMap getMap() {
        return mGoogleMap;
    }


    public void moveMap(LatLng latLng) {
        /**
         * Creating the latlng object to store lat, long coordinates
         * move the camera with animation
         */

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));


    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            //Getting longitude and latitude
            mCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());

            //moving the map to location
            moveMap(mCurrentLocation);
        }
    }

    public void moveCurrentLocation() {
        if (mCurrentLocation != null)
            moveMap(mCurrentLocation);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Init data test
    public void intData() {
        coordinates = new ArrayList<>();
        coordinates.add(new LatLng(10.806100, 106.611325));
        coordinates.add(new LatLng(10.769845, 106.603086));
        coordinates.add(new LatLng(10.771531, 106.635530));
        coordinates.add(new LatLng(10.756522, 106.702993));
        coordinates.add(new LatLng(10.732669, 106.660184));
        coordinates.add(new LatLng(10.763956, 106.679985));
        coordinates.add(new LatLng(10.784813, 106.690601));
        coordinates.add(new LatLng(10.777994, 106.694888));
        coordinates.add(new LatLng(10.736877, 106.719207));
        coordinates.add(new LatLng(10.780844, 106.696702));
    }


    /**
     * Add box location
     */
    public void addBoxMarker() {
        for (LatLng cor : coordinates) {
           // mMarkerManager.createBoxMarker(cor.latitude, cor.longitude);
        }
    }

    /**
     * get current location success
     * move map to current location
     *
     * @param location current location
     */
    @Override
    public void onSuccess(Location location) {
        if (location != null) {
            //Getting longitude and latitude
            mCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            //moving the map to location
            moveMap(mCurrentLocation);
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mDestLocation = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        if (mPolyline != null)
            mPolyline.remove();

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mCurrentLocation, mDestLocation);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);
        return true;
    }


    //-------------------------------------------------------------------------------------------------
    //Drawing policy
    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();


            parserTask.execute(result);

        }
    }


    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            if (result == null) {
                return;
            }
            ArrayList points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                //Get distance and duration between two point
                List<HashMap<String, String>> path = result.get(i);
                HashMap<String, String> detail = path.get(0);
//                mLocation = new DetailLocation();
//                mLocation.setDuration(detail.get("duration"));
//                mLocation.setDistance(detail.get("distance"));
//                mLocation.setStartAddress(detail.get("startAddress"));
//                mLocation.setEndAddress(detail.get("endAddress"));

                for (int j = 3; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = null;
                    position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                //lineOptions.color(ContextCompat.getColor(mContext.getApplicationContext(), R.color.polyOptions));
                lineOptions.geodesic(true);

            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null)
                mPolyline = mGoogleMap.addPolyline(lineOptions);
//
//            if (mMapEventClickMarkerListener != null && mLocation != null) {
//                mMapEventClickMarkerListener.onMarkerClicked(mLocation);
////                mMapEventClickMarkerListener.onClickMarker();
//            }

        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @SuppressLint("MissingPermission")
    public void firstInitMap() {
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this);
    }

}
