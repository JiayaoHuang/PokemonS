package ca.cary.pokemons.markers;

import android.app.Activity;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.cary.pokemons.R;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public class CurrentLocationMarker {

    public static final String TAG = CurrentLocationMarker.class.getName();

    public static final LatLng HOME = new LatLng(45.4161077, -75.67217289999996);
    public static final float DEFAULT_ZOOM_LEVEL = 15;

    private static CurrentLocationMarker mCurrentLocationMarker;

    private Marker mMarker;

    private CurrentLocationMarker() {}

    public static CurrentLocationMarker getInstance() {
        if (mCurrentLocationMarker == null) {
            mCurrentLocationMarker = new CurrentLocationMarker();
        }

        return mCurrentLocationMarker;
    }

    public void initiateCurrentLocation(GoogleMap map, Activity activity, Location location) {
        if (map != null && activity != null) {
            LatLng latLng = HOME;
            if (location != null) {
                latLng = new LatLng(location.getLatitude(), location.getLongitude());
            }

            if (mMarker != null) {
                mMarker.remove();
            }
            mMarker = map.addMarker(new MarkerOptions().position(latLng).title(activity.getString(R.string.i_am_here)));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM_LEVEL));
        }
    }

    public void updateCurrentLocation(GoogleMap map, Activity activity, Location location) {
        if (map != null && activity != null) {
            LatLng latLng = HOME;
            if (location != null) {
                latLng = new LatLng(location.getLatitude(), location.getLongitude());
            }

            if (mMarker != null) {
                mMarker.remove();
            }
            mMarker = map.addMarker(new MarkerOptions().position(latLng).title(activity.getString(R.string.i_am_here)));
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void updateCurrentLocation(GoogleMap map, Activity activity, LatLng latLng) {
        if (map != null && activity != null && latLng != null) {
            if (mMarker != null) {
                mMarker.remove();
            }
            mMarker = map.addMarker(new MarkerOptions().position(latLng).title(activity.getString(R.string.i_am_here)));
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

}
