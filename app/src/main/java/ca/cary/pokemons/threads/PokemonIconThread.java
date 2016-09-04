package ca.cary.pokemons.threads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ca.cary.pokemons.customs.FlushedInputStream;
import ca.cary.pokemons.helpers.BitmapHelper;
import ca.cary.pokemons.helpers.CacheHelper;
import ca.cary.pokemons.helpers.SyncHelper;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public class PokemonIconThread extends BaseThread {

    public static final String TAG = PokemonIconThread.class.getName();

    public static final int POKEMON_ICON_WIDTH = 100;
    public static final int POKEMON_ICON_HEIGHT = 100;
    public static final int ICON_SAVING_QUALITY = 100;

    public static void run(Activity activity, String pokemonName, String iconPath, Marker marker) {
        String tag = getTag(TAG, pokemonName);

        if (SyncHelper.getInstance().isProcessing(tag)) {
            return;
        }

        new Thread(getRunnable(activity, pokemonName, iconPath, marker, tag)).start();
    }

    private static Runnable getRunnable(final Activity activity, final String pokemonName, final String iconPath, final Marker marker, final String tag) {
        return new Runnable() {
            @Override
            public void run() {
                SyncHelper.getInstance().setProcessing(tag, true);

                if (activity != null) {
                    final Bitmap bitmap;
                    try {
                        URL url = new URL(iconPath);
                        URLConnection urlConnection = url.openConnection();
                        urlConnection.setConnectTimeout(5000);
                        urlConnection.setReadTimeout(5000);

                        bitmap = BitmapHelper.resizeImages(new FlushedInputStream(urlConnection.getInputStream()), POKEMON_ICON_WIDTH, POKEMON_ICON_HEIGHT);
                    } catch (MalformedURLException e) {
                        Log.e(TAG, "MalformedURLException: " + e.getMessage());
                        SyncHelper.getInstance().setProcessing(tag, false);
                        return;
                    } catch (IOException e) {
                        Log.e(TAG, "IOException" + e.getMessage());
                        SyncHelper.getInstance().setProcessing(tag, false);
                        return;
                    }

                    if (bitmap != null) {
                        try {
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, ICON_SAVING_QUALITY, stream);
                            new CacheHelper(activity).savePokemonIcon(pokemonName, stream.toByteArray());
                            stream.flush();
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (marker != null && bitmap != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                            }
                        });
                    }
                }

                SyncHelper.getInstance().setProcessing(tag, false);
            }
        };
    }

}
