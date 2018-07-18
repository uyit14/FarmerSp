package com.example.uytai.farmersp.thuonglai.nongsan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.IconGenerator;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng> latLngList = new ArrayList<>();
    private Polyline mPolyline;
    LatLng here;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        requestGetListNongSan();
        //install google api client
        GoogleAuthenController.getInstance().install(this, this, this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addAllDatatomarker() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (com.example.uytai.farmersp.thuonglai.nongsan.LatLng latLng : latLngList) {
                    LatLng placeLatLng = new LatLng(latLng.getLatRS(), latLng.getLngRS());
                    TextView text = new TextView(getApplicationContext());
                    text.setText(latLng.getTennongsanRS());
                    text.setTextColor(getResources().getColor(R.color.cl_black));
                    text.setTypeface(Typeface.DEFAULT_BOLD);
                    text.setBackground(getResources().getDrawable(R.color.colorMain));
                    IconGenerator generator = new IconGenerator(getApplicationContext());
                    //generator.setBackground(getResources().getDrawable(R.drawable.custom_marker));
                    generator.setContentView(text);
                    Bitmap icon = generator.makeIcon();
                    MarkerOptions tp = new MarkerOptions().position(placeLatLng).icon(BitmapDescriptorFactory.fromBitmap(icon));
                    mMap.addMarker(tp);

//                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.friuts_48);
//                    mMap.addMarker(new MarkerOptions().position(placeLatLng).title(latLng.getTennongsanRS()).icon(icon));
                }
            }
        }, 2000);
    }


    //------GET DATA FROM SERVER------//
    private void requestGetListNongSan() {
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng>> call = thuonglaiService.getLatLng();
        call.enqueue(new Callback<List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng>>() {
            @Override
            public void onResponse(Call<List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng>> call, Response<List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng>> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        latLngList.addAll(response.body());
                        addAllDatatomarker();
                    } else {
                        Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<com.example.uytai.farmersp.thuonglai.nongsan.LatLng>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Tải thông tin thất bại, xin thử lại sau!", Toast.LENGTH_SHORT).show();

            }
        });
    }

//    9.700333, 106.250709
//    9.698241, 106.252648
//    9.567392, 106.417317
    //TG
//    10.357059, 106.688125
//    10.384720, 106.150211
//    10.542453, 106.226759
    //BMT
//    12.698556, 107.982309
//    12.571696, 108.048204
    //Playkue
//    13.926823, 107.982584
    //Gialai
//    13.603500, 107.916934


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng here = new LatLng(10.8030111, 106.721324);
//        mMap.addMarker(new MarkerOptions().position(here).title("You are here"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 15));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("GOOGLE_API_CLIENT", "CONNECTED");
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        GoogleAuthenController.getInstance().getGoogleApiClient().connect();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(GoogleAuthenController.getInstance().getGoogleApiClient());
        if (lastLocation != null) {

            here = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions().position(here).title("Bạn ở đây"));
            marker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 15));
            //move camera
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 13));
        } else {
            //mTextViewStartLocation.setText(getResources().getString(R.string.booking_pic_start_location));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        LatLng mDestLocation = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        if (mPolyline != null)
            mPolyline.remove();

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(here, mDestLocation);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);
        return true;
    }


    ///
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

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = null;
                    position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                //lineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.colorMain));
                lineOptions.geodesic(true);

            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null)
                mPolyline = mMap.addPolyline(lineOptions);

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

}
