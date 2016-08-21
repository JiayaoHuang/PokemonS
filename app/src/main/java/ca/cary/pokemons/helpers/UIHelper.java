package ca.cary.pokemons.helpers;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class UIHelper {

    public static final String TAG = UIHelper.class.getName();

    public static void showToastOnUIThread(final Activity activity, final int message, final int duration) {
        if (activity == null) {
            Log.e(TAG, "(activity == null)");
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, duration).show();
            }
        });
    }

}
