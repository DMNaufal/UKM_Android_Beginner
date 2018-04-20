package app_project.healthapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListener {
    //MaterialSearchView searchView;

    private GPSTracker gps;
    private Location mLocation;
    double lati , logi;
    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    String mPlaceType="hospital";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //get location from gpstracker
        gps = new GPSTracker(HomeActivity.this);
        mLocation = gps.getLocation();
        lati = mLocation.getLatitude();
        logi = mLocation.getLongitude();

        Button btnneary = (Button) findViewById(R.id.btnneary);
        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);

        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        btnneary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String type = mPlaceType;

                StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                sb.append("location="+lati+","+logi);
                sb.append("&radius=2000");
                sb.append("&types="+type);
                sb.append("&sensor=true");
                sb.append("&key=AIzaSyA4j-N2dp3j4YCyXYA5tSoDj4oGLxAND7U");

                // Creating a new non-ui thread task to download Google place json data
                PlacesTask placesTask = new PlacesTask();

                // Invokes the "doInBackground()" method of the class PlaceTask
                placesTask.execute(sb.toString());

            }
        });
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        try {
            List<Address> addresses = geocoder.getFromLocation(lati, logi, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                etOrigin.setText(strReturnedAddress.toString());

            }
            else{
                etOrigin.setText("No Address returned!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            etOrigin.setText("Canont get Address!");
        }

    }

    /** A method to download json data from url */
    public String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);


            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }


/** A class, to download Google Places */
public class PlacesTask extends AsyncTask<String, Integer, String>{

    String data = null;

    // Invoked by execute() method of this object
    @Override
    protected String doInBackground(String... url) {
        try{
            data = downloadUrl(url[0]);
        }catch(Exception e){
            Log.d("Background Task",e.toString());
        }
        return data;
    }

    // Executed after the complete execution of doInBackground() method
    @Override
    protected void onPostExecute(String result){
        ParserTask parserTask = new ParserTask();

        // Start parsing the Google places in JSON format
        // Invokes the "doInBackground()" method of the class ParseTask
        parserTask.execute(result);
    }

}

/** A class to parse the Google Places in JSON format */
private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

    JSONObject jObject;

    // Invoked by execute() method of this object
    @Override
    protected List<HashMap<String,String>> doInBackground(String... jsonData) {

        List<HashMap<String, String>> places = null;
        PlaceJSONParser placeJsonParser = new PlaceJSONParser();

        try{
            jObject = new JSONObject(jsonData[0]);

            /** Getting the parsed data as a List construct */
            places = placeJsonParser.parse(jObject);

        }catch(Exception e){
            Log.d("Exception",e.toString());
        }
        return places;
    }

    // Executed after the complete execution of doInBackground() method
    @Override
    protected void onPostExecute(List<HashMap<String,String>> list){

        // Clears all the existing markers
        mMap.clear();

        for(int i=0;i<5;i++){

            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();

            // Getting a place from the places list
            HashMap<String, String> hmPlace = list.get(i);

            // Getting latitude of the place
            double lat = Double.parseDouble(hmPlace.get("lat"));

            // Getting longitude of the place
            double lng = Double.parseDouble(hmPlace.get("lng"));

            // Getting name
            String name = hmPlace.get("place_name");

            // Getting vicinity
            String vicinity = hmPlace.get("vicinity");

            LatLng latLng = new LatLng(lat, lng);

            // Setting the position for the marker
            markerOptions.position(latLng);

            // Setting the title for the marker.
            //This will be displayed on taping the marker
            markerOptions.title(name + " : " + vicinity);


            // Placing a marker on the touched position
            mMap.addMarker(markerOptions);
            LatLng tr = new LatLng(lati, logi);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr, 13));

        }

    }
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

         LatLng tr = new LatLng(lati, logi);
        mMap.addMarker(new MarkerOptions().position(tr)
                .title("Anda"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tr, 15.5f));
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

            mMap.setMyLocationEnabled(true);
        }

    }
    private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();

        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        mMap.clear();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.endLocation, 14));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                  //.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                  //.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }


}



