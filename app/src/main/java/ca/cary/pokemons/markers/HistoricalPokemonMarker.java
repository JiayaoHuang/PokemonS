package ca.cary.pokemons.markers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.cary.pokemons.R;
import ca.cary.pokemons.dtos.PokemonDto;
import ca.cary.pokemons.threads.PokemonIconThread;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public class HistoricalPokemonMarker {

    public static final String TAG = HistoricalPokemonMarker.class.getName();

    public static final String DATE_FORMAT = "MMM dd, yyyy - hh:mm:ss";

    private static HistoricalPokemonMarker mHistoricalPokemonMarker;

    private List<Marker> mMarkers;

    private HistoricalPokemonMarker() {
        mMarkers = new ArrayList<>();
    }

    public static HistoricalPokemonMarker getInstance() {
        if (mHistoricalPokemonMarker == null) {
            mHistoricalPokemonMarker = new HistoricalPokemonMarker();
        }

        return mHistoricalPokemonMarker;
    }

    public void showHistoricalPokemon(GoogleMap map, Activity activity, List<PokemonDto> pokemonDtos) {
        if (map != null && activity != null) {
            clearAllMarkers();

            if (pokemonDtos == null) {
                Log.e(TAG, "(pokemonDtos == null)");
                return;
            }

            for (PokemonDto pokemonDto : pokemonDtos) {
                LatLng latLng = new LatLng(pokemonDto.getLatitude(), pokemonDto.getLongitude());

                if (pokemonDto.getPokemon() == null) {
                    Log.e(TAG, "(pokemonDto.getPokemon() == null)");
                }
                String pokemonName = pokemonDto.getPokemon().getName();

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                String disappearDate = simpleDateFormat.format(pokemonDto.getDisappearDate());

                Marker marker;
                if (pokemonDto.getIcon() == null) {
                    marker = map.addMarker(new MarkerOptions().position(latLng)
                            .title(pokemonName)
                            .snippet(activity.getString(R.string.disappear_date) + " " + disappearDate)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                    PokemonIconThread.run(activity, pokemonName, pokemonDto.getIconPath(), marker);
                } else {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(pokemonDto.getIcon(), 0, pokemonDto.getIcon().length);

                    marker = map.addMarker(new MarkerOptions().position(latLng)
                            .title(pokemonName)
                            .snippet(activity.getString(R.string.disappear_date) + " " + disappearDate)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                }

                mMarkers.add(marker);
            }
        }
    }

    private void clearAllMarkers() {
        for (Marker marker : mMarkers) {
            marker.remove();
        }

        mMarkers.clear();
    }

}
