package ca.cary.pokemons.helpers;

import android.app.Activity;
import android.widget.Toast;

import ca.cary.pokemons.R;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class ServiceResponseHelper {

    public static final String TAG = ServiceResponseHelper.class.getName();

    public static void handleNoConnectivity(Activity activity) {
        UIHelper.showToastOnUIThread(activity, R.string.no_connection, Toast.LENGTH_SHORT);
    }

}
