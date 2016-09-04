package ca.cary.pokemons.helpers;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class ServiceHelper {

    public static final String TAG = ServiceHelper.class.getName();

    public static boolean checkNetworkConnection(IntentService service) {
        if (service == null) {
            Log.e(TAG, "(service == null)");
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) service.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean checkNetworkConnection(Activity activity) {
        if (activity == null) {
            Log.e(TAG, "(activity == null)");
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            ServiceResponseHelper.handleNoConnectivity(activity);
            return false;
        }
    }

    public static JSONObject httpGetJSONRequest(String URL) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.connect();
            int response = urlConnection.getResponseCode();
            Log.d(TAG, "The response is: " + response);

            if (response == 200 || response == 201) {
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                is.close();

                return new JSONObject(sb.toString());
            }

            return null;
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException! " + e.getMessage());

            return null;
        } catch (IOException e) {
            Log.e(TAG, "IOException Error! " + e.getMessage());

            return null;
        } catch (JSONException e) {
            Log.e(TAG, "JSONException! " + e.getMessage());

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

}
