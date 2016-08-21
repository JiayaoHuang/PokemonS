package ca.cary.pokemons.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONObject;

import ca.cary.pokemons.fragments.SettingsFragment;
import ca.cary.pokemons.helpers.ServiceHelper;
import ca.cary.pokemons.helpers.SniperAlarmHelper;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class SniperService extends IntentService {

    public static final String TAG = SniperService.class.getName();

    public static final String POKEMON_SNIPER_URL = "http://www.pokesnipers.com/api/v1/pokemon.json";

    private static boolean mBlock = false;

    private static Intent service;

    public SniperService() {
        super("SniperService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String delayValue = intent.getStringExtra(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME);
        Long delayTime = null;
        try {
            delayTime = Long.valueOf(delayValue);
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage() + " : Long.valueOf(delayValue)");
        }

        if (delayTime != null) {
            JSONObject sniperJSON = ServiceHelper.httpGetJSONRequest(POKEMON_SNIPER_URL);
            if (sniperJSON != null) {
                Log.d(TAG, sniperJSON.toString());
            } else {
                Log.e(TAG, "(sniperJSON != null)");
            }

            SniperAlarmHelper.getInstance().setAlarm(this, delayTime);
        }
    }

    public static void startSniperService(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isOn = sharedPreferences.getBoolean(SettingsFragment.SNIPER_SERVICE_ON_OFF, true);
        String delayValue = sharedPreferences.getString(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME,
                SniperAlarmHelper.SNIPER_SERVICE_CALL_DEFAULT_DELAY_TIME);

        if (isOn && !isBlock()) {
            service = new Intent(context, SniperService.class);
            service.putExtra(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME, delayValue);
            context.startService(service);
        }
    }

    public static void stopSniperService(Context context) {
        if (service != null && isBlock()) {
            context.stopService(service);
            blockTask(false);
        }

        SniperAlarmHelper.getInstance().endAlarm();
    }

    private static boolean isBlock() {
        return mBlock;
    }

    private static void blockTask(boolean block) {
        mBlock = block;
    }

}
