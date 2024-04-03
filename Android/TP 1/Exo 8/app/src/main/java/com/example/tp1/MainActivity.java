package com.example.tp1;


import static java.lang.Thread.sleep;
import static java.time.LocalDateTime.now;

//import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1.models.Journeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_SUBMISSION_CONFIRMATION = 1;
    private static String baseApiEndpointString = "https://api.navitia.io/v1/coverage/sncf/";
    private  static String API_KEY = "d92767f5-de68-462b-b92b-8357a733ae98";

    //  SNCF_AUTH_API_KEY="d92767f5-de68-462b-b92b-8357a733ae98"

    // Authentication in request
    // https://d92767f5-de68-462b-b92b-8357a733ae98@api.navitia.io/v1/coverage/sncf/places?q=paris

    private ProgressDialog progressDialog ;

    private EditText departureCityEditText;
    private EditText destinationCityEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        departureCityEditText = (EditText) findViewById(R.id.departureCityEditText);
        destinationCityEditText = (EditText) findViewById(R.id.destinationCityEditText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrieveFromRESTAPI journeysTask = new RetrieveFromRESTAPI();
                journeysTask.execute();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUBMISSION_CONFIRMATION && resultCode == Activity.RESULT_OK) {
            String choice = data.getStringExtra("choice");
            System.out.println("LE RESULTAT DE LA BOITE DE DIALOGUE: "+choice);
        }

    }

    public class RetrieveFromRESTAPI extends AsyncTask<String, String, List<Journeys> > {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param String The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected List<Journeys> doInBackground(String... String) {
            List<Journeys> results = new ArrayList<Journeys>();

            String from = departureCityEditText.getText().toString();
            String to = destinationCityEditText.getText().toString();

            results = getJourney(API_KEY, baseApiEndpointString, from, to);
            System.out.println("Size data received in BACKGROUND: "+results.size());
            return  results;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("En cours de traitements...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Journeys> journeys) {
            progressDialog.dismiss();
            super.onPostExecute(journeys);

            Intent displayResultIntent = new Intent(MainActivity.this, DisplayDataActivity.class);
            System.out.println("Size data from Main activity: "+journeys.size());
            displayResultIntent.putExtra("journeys", (Serializable) journeys);
            startActivity(displayResultIntent);
            finish();

        }
    }

    public static HashMap<String, String> getPlaceStopAreas(String API_KEY,String baseApiEndpointString,String place) throws JSONException {
        HashMap<String, String> areas = new HashMap<String, String>();
        String requestEndPoint = "places?q=";
//        URLEncoder.encode()
        String requestUrlString = null;
        try {
            requestUrlString = baseApiEndpointString + requestEndPoint+  URLEncoder.encode(place.trim().toLowerCase(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL apiRequestUrl = null;
        HttpsURLConnection connection;
        String result = "";
        try {
            apiRequestUrl = new URL(requestUrlString);
            System.out.println("Url: " + apiRequestUrl);
            try {
                connection = (HttpsURLConnection) apiRequestUrl.openConnection();
                connection.setRequestProperty("Authorization", API_KEY);
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(1000);

                InputStream responseBody = connection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody);

                int data = responseBodyReader.read();

                while (data != -1) {
                    result += (char) data;
                    data = responseBodyReader.read();
                }


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.has("places")) {
                    JSONArray places = jsonObject.getJSONArray("places");
                    for (int i=0; i< places.length(); i++) {
                        JSONObject currentPlace = places.getJSONObject(i);
                        if(currentPlace.has("stop_area")) {
                            areas.put(currentPlace.get("name").toString().trim(),currentPlace.get("id").toString().trim());
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return areas;

    }



    public static List<Date> getNextJourneyDepartures(String API_KEY, String baseApiEndpointString, String from, String to, String time) {
        List<Date> retrievedJourneyDepartures = new ArrayList<Date>();


        String requestUrlString = null;
        try {
            requestUrlString = baseApiEndpointString +"journeys?from=" +
                    URLEncoder.encode(String.format("%s",from.trim()), StandardCharsets.UTF_8.toString())+
                    "&to="+URLEncoder.encode(String.format("%s",to.trim()), StandardCharsets.UTF_8.toString())+
                    "&datetime="+URLEncoder.encode(String.format("%s",time.trim()), StandardCharsets.UTF_8.toString())+"&count=3";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ URL STRING: "+requestUrlString);

        URL apiRequestUrl = null;
        HttpsURLConnection connection;
        String result = "";
        try {
            apiRequestUrl = new URL(requestUrlString);
            System.out.println(" get journey api request Url: " + apiRequestUrl);
            try {
                connection = (HttpsURLConnection) apiRequestUrl.openConnection();
                connection.setRequestProperty("Authorization", API_KEY);
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(1000);

                InputStream responseBody = connection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody);

                int data = responseBodyReader.read();

                while (data != -1) {
                    result += (char) data;
                    data = responseBodyReader.read();
                }

                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("journeys")) {
                    JSONArray journeys = jsonObject.getJSONArray("journeys");

                    for (int i=0; i< journeys.length(); i++) {
                        JSONObject currentPlace = journeys.getJSONObject(i);
                        if(currentPlace.has("departure_date_time")) {

                            //

//                            DateTimeFormatter objectFormatter =  DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

                            Date currentDate = new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse(currentPlace.get("departure_date_time").toString());
                            System.out.println(" **************************** Next departures: "+currentPlace.get("departure_date_time"));
                            System.out.println(" **************************** Parsed Next departures: "+currentDate);
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//                            System.out.println(" **************************** Next departures: "+new Date(currentPlace.get("departure_date_time").toString()));
                            retrievedJourneyDepartures.add( currentDate);
//                            }

                        }
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return retrievedJourneyDepartures;

    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Journeys> getJourney(String API_KEY, String baseApiEndpointString, String fromPlace, String toPlace) {
        HashMap<String, String> departureAreas = new HashMap<String, String>();
        HashMap<String, String> arrivalAreas = new HashMap<String, String>();
        String baseEndpointString = baseApiEndpointString;

        List<Journeys> journeys = new ArrayList<Journeys>();

        // Getting Stop_areas
        try {
            departureAreas = getPlaceStopAreas( API_KEY, baseEndpointString, fromPlace.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            arrivalAreas=getPlaceStopAreas( API_KEY, baseEndpointString, toPlace.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Departure Size: "+departureAreas.size());
        System.out.println("Arrival Size: "+arrivalAreas.size());
        // Getting the search timeStamp
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        System.out.println("Temp a partitr de Date(): "+formatter.format(new Date()));
//        LocalDateTime now = null;
        String time=formatter.format(new Date());

//            now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//            Date date = new Date
//            System.out.println("Time genereated: "+now);
//            time= now.toString().replace(":", "").replace("-","").replace(".","");


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ EXECUTING GET NEXT JOURNEYS");

        HashMap<String, String> finalArrivalAreas = arrivalAreas;


        String finalTime = time;
        departureAreas.forEach((departureKey, departureValue) -> {
            String departure = departureValue;
            for (Map.Entry<String, String> entry : finalArrivalAreas.entrySet()) {
                String arrivalKey = entry.getKey();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Arrival key: "+arrivalKey);
                String arrivalValue = entry.getValue();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Arrival value: "+arrivalValue);
                List<Date> currentJourneyDepartures = new ArrayList<Date>();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    currentJourneyDepartures = getNextJourneyDepartures(API_KEY, baseEndpointString, departure, arrivalValue, finalTime);
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ in JOURNEYS: current journeys= "+currentJourneyDepartures.size());
//                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentJourneyDepartures.forEach((journeyDeparture)->{
                    journeys.add(new Journeys(journeyDeparture, departureKey, arrivalKey));
                });

            }
        });

        return journeys;

    }


}



