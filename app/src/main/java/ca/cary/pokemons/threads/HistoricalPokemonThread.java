package ca.cary.pokemons.threads;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import ca.cary.pokemons.dtos.PokemonDto;
import ca.cary.pokemons.helpers.CacheHelper;
import ca.cary.pokemons.helpers.SyncHelper;
import ca.cary.pokemons.markers.HistoricalPokemonMarker;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public class HistoricalPokemonThread extends BaseThread {

    public static final String TAG = HistoricalPokemonThread.class.getName();

    public static void run(Activity activity, double latitude, double longitude, GoogleMap map) {
        String tag = getTag(TAG, latitude + "." + longitude);

        if (SyncHelper.getInstance().isProcessing(tag)) {
            return;
        }

        new Thread(getRunnable(activity, latitude, longitude, map, tag)).start();
    }

    private static Runnable getRunnable(final Activity activity, final double latitude, final double longitude, final GoogleMap map, final String tag) {
        return new Runnable() {
            @Override
            public void run() {
                SyncHelper.getInstance().setProcessing(tag, true);

                if (activity != null) {
                    final List<PokemonDto> pokemonDtos = new CacheHelper(activity).getHistoricalPokemonDtos(latitude, longitude);
                    if (pokemonDtos != null) {
                        Log.i(TAG, "Number of Pokemon: " + pokemonDtos.size());
                    }

                    if (map != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HistoricalPokemonMarker.getInstance().showHistoricalPokemon(map, activity, pokemonDtos);
                            }
                        });
                    }
                }

                SyncHelper.getInstance().setProcessing(tag, false);
            }
        };
    }

}
