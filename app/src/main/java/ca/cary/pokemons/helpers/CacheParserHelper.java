package ca.cary.pokemons.helpers;

import android.content.ContentValues;
import android.util.Log;

import java.util.Date;

import ca.cary.pokemons.contracts.SniperContract;
import ca.cary.pokemons.dtos.SniperDto;

/**
 * Created by jiayaohuang on 2016-08-29.
 */
public class CacheParserHelper {

    public static final String TAG = CacheParserHelper.class.getName();

    public static ContentValues sniperDtoToContentValues(SniperDto sniperDto) {
        if (sniperDto == null) {
            Log.e(TAG, "(sniperDto == null)");
            return null;
        }

        ContentValues values = new ContentValues();

        values.put(SniperContract.Sniper.SNIPER_ID, sniperDto.getSniperId());

        if (sniperDto.getPokemon() == null) {
            Log.e(TAG, "(sniperDto.getPokemon() == null)");
        } else {
            values.put(SniperContract.Sniper.SNIPER_POKEMON_NAME, sniperDto.getPokemon().getName());
        }

        values.put(SniperContract.Sniper.SNIPER_LATITUDE, sniperDto.getLatitude());
        values.put(SniperContract.Sniper.SNIPER_LONGITUDE, sniperDto.getLongitude());

        if (sniperDto.getDisappearDate() == null) {
            Log.e(TAG, "(sniperDto.getDisappearDate() == null)");
        } else {
            values.put(SniperContract.Sniper.SNIPER_DATE_DISAPPEAR, sniperDto.getDisappearDate().getTime());
        }

        Date now = new Date();
        values.put(SniperContract.Sniper.SNIPER_DATE_CREATED, now.getTime());

        return values;
    }

}
