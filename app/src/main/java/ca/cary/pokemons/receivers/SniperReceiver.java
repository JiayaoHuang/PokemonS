package ca.cary.pokemons.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ca.cary.pokemons.services.SniperService;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class SniperReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SniperService.startSniperService(context);
    }

}
