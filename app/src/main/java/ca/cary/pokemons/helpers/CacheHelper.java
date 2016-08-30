package ca.cary.pokemons.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;

import ca.cary.pokemons.contracts.SniperContract;
import ca.cary.pokemons.dtos.SniperDto;

/**
 * Created by jiayaohuang on 2016-08-28.
 */
public class CacheHelper {

    public static final String TAG = CacheHelper.class.getName();

    private DbHelper mDbHelper;

    public CacheHelper(Context context) {
        mDbHelper = DbHelper.getInstance(context);
    }

    public void saveSnipers(List<SniperDto> sniperDtos) {
        if (sniperDtos != null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            try {
                for (SniperDto sniperDto : sniperDtos) {
                    ContentValues values = CacheParserHelper.sniperDtoToContentValues(sniperDto);
                    db.insertWithOnConflict(SniperContract.Sniper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                }
            } catch (SQLiteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

}
