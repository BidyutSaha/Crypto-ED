package com.example.crypto_ed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MyAsyncTasks extends AsyncTask<String, String, String> {
    String TAG = "###";
    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("https://locus-ed.herokuapp.com/dec");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
              //  Log.e(TAG, "11 - url : " + requestURL);

                JSONObject root = new JSONObject();
                root.put("K" , "K");
                root.put("N" , "N");
                root.put("A" , "A");
                root.put("C" , "C");
                root.put("T" , "T");
                String str = root.toString();
                Log.e(TAG, "12 - root : " + root.toString());

                byte[] outputBytes = str.getBytes("UTF-8");
                OutputStream os = urlConnection.getOutputStream();
                os.write(outputBytes);
                int responseCode = urlConnection.getResponseCode();
                Log.e(TAG, "13 - responseCode : " + responseCode);

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    Log.e(TAG, "14 - HTTP_OK");

                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                    Log.e(TAG, "15 - Response - " + response);

                } else {
                    Log.e(TAG, "14 - False - HTTP_OK");
                    response = "";
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
        return response;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog to show the user what is happening
//        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("processing results");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        System.out.println("#################### progressbar is started");

    }


    @Override
    protected void onPostExecute(String s) {
        // show results
        // dismiss the progress dialog after receiving data from API
        //progressDialog.dismiss();
        try {
            JSONObject jsonObject = new JSONObject(s);
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
