package ca.cary.pokemons.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.List;

import ca.cary.pokemons.dtos.SniperDto;
import ca.cary.pokemons.fragments.SettingsFragment;
import ca.cary.pokemons.helpers.CacheHelper;
import ca.cary.pokemons.helpers.JsonParserHelper;
import ca.cary.pokemons.helpers.ServiceHelper;
import ca.cary.pokemons.helpers.SniperAlarmHelper;
import ca.cary.pokemons.helpers.SyncHelper;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class SniperService extends IntentService {

    public static final String TAG = SniperService.class.getName();

    public static final String POKEMON_SNIPER_URL = "http://www.pokesnipers.com/api/v1/pokemon.json";

    private static Intent service;

    public SniperService() {
        super("SniperService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SyncHelper.getInstance().setProcessing(TAG, true);

        String delayValue = intent.getStringExtra(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME);
        Long delayTime = null;
        try {
            delayTime = Long.valueOf(delayValue);
        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException! " + e.getMessage() + " : Long.valueOf(delayValue)");
        }

        if (delayTime != null) {
            if (!ServiceHelper.checkNetworkConnection(this)) {
                return;
            }

            JSONObject sniperJSON = ServiceHelper.httpGetJSONRequest(POKEMON_SNIPER_URL);
            if (sniperJSON != null) {
                Log.d(TAG, sniperJSON.toString());

                List<SniperDto> sniperDtos = null;
                try {
                    sniperDtos = JsonParserHelper.sniperJsonToSniperDtos(sniperJSON);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException! " + e.getMessage());
                } catch (NumberFormatException e) {
                    Log.e(TAG, "NumberFormatException! " + e.getMessage());
                } catch (ParseException e) {
                    Log.e(TAG, "ParseException! " + e.getMessage());
                }

                new CacheHelper(this).saveSnipers(sniperDtos);
            } else {
                Log.e(TAG, "(sniperJSON != null)");
            }

            SniperAlarmHelper.getInstance().setAlarm(this, delayTime);
        }

        SyncHelper.getInstance().setProcessing(TAG, false);
    }

    public static void startSniperService(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isOn = sharedPreferences.getBoolean(SettingsFragment.SNIPER_SERVICE_ON_OFF, true);
        String delayValue = sharedPreferences.getString(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME,
                SniperAlarmHelper.SNIPER_SERVICE_CALL_DEFAULT_DELAY_TIME);

        if (isOn && !SyncHelper.getInstance().isProcessing(TAG)) {
            service = new Intent(context, SniperService.class);
            service.putExtra(SettingsFragment.SNIPER_SERVICE_CALL_DELAY_TIME, delayValue);
            context.startService(service);
        }
    }

    public static void stopSniperService(Context context) {
        if (service != null && SyncHelper.getInstance().isProcessing(TAG)) {
            context.stopService(service);
            SyncHelper.getInstance().setProcessing(TAG, false);
        }

        SniperAlarmHelper.getInstance().endAlarm();
    }

}
