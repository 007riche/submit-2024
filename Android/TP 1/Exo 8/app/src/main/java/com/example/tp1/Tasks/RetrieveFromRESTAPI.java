//package com.example.tp1.Tasks;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.util.JsonReader;
//
//import com.example.tp1.MainActivity;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//
//import javax.net.ssl.HttpsURLConnection;
//
//public class RetrieveFromRESTAPI extends AsyncTask<String, String, HashMap<String, String> > {
//
//    /**
//     * Override this method to perform a computation on a background thread. The
//     * specified parameters are the parameters passed to {@link #execute}
//     * by the caller of this task.
//     * <p>
//     * This will normally run on a background thread. But to better
//     * support testing frameworks, it is recommended that this also tolerates
//     * direct execution on the foreground thread, as part of the {@link #execute} call.
//     * <p>
//     * This method can call {@link #publishProgress} to publish updates
//     * on the UI thread.
//     *
//     * @param String The parameters of the task.
//     * @return A result, defined by the subclass of this task.
//     * @see #onPreExecute()
//     * @see #onPostExecute
//     * @see #publishProgress
//     */
//    @Override
//    protected HashMap<String, String> doInBackground(String... String) {
//        HashMap<String, String> results = new HashMap<String, String>();
//
//        URL apiEndpoint = null;
//        try {
//            //  SNCF_AUTH_API_KEY="d92767f5-de68-462b-b92b-8357a733ae98"
//
//            // Authentication in request
//            // https://d92767f5-de68-462b-b92b-8357a733ae98@api.navitia.io/v1/coverage/sncf/places?q=paris
//
//
//            // https://api.navitia.io/v1/coverage?key=3b036afe-0110-4202-b9ed-99718476c2e0
//
//            // Endpoints for places to find stop_areas for sncf coverage
//            // https://api.navitia.io/v1/coverage/sncf/places?q=paris
//            // https://api.navitia.io/v1/coverage/sncf/places?q={string}
//            // Be carefull to only retrieve object with "stop_area" subfield, needed for later
//
//            // Endpoint to plan a journey from the stop_area of a place to the another from a date
//            // https://api.navitia.io/v1/coverage/sncf/journeys?from=stop_area%3ASNCF%3A87688887&to=stop_area%3ASNCF%3A87547000&datetime=20230222T000000
//            // https://api.navitia.io/v1/coverage/sncf/journeys?from={place.id}&to={place.id}&datetime={datetime} ,   {place.id}: can be the stop_area
//
//
//            apiEndpoint = new URL("https://api.navitia.io/v1/");
//
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            HttpsURLConnection connection = (HttpsURLConnection) apiEndpoint.openConnection();
//            connection.setRequestProperty("User-agent", "");
//            if (connection.getResponseCode() == 200) {
//
//            } else {
//
//            }
//
//            InputStream responseBody = connection.getInputStream();
//            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
//
//            JsonReader jsonReader = new JsonReader(responseBodyReader);
//
//            jsonReader.beginObject();
//            while (jsonReader.hasNext()) {
//                String key = jsonReader.nextName();
//                if (key.equals("places")) {
//
//                } else {
//                    jsonReader.skipValue();
//                }
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return results;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        // display a progress dialog for good user experiance
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("processing results");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//    }
//
//}
