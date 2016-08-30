package ca.cary.pokemons.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ca.cary.pokemons.contracts.PokemonContract;
import ca.cary.pokemons.contracts.SniperContract;

/**
 * Created by jiayaohuang on 2016-08-28.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PokemonS.db";

    private static DbHelper mDbHelper;

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DbHelper getInstance(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new DbHelper(context.getApplicationContext());
        }

        return mDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PokemonContract.Pokemon.SQL_INITIATE_ENTRIES);
        db.execSQL(SniperContract.Sniper.SQL_INITIATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PokemonContract.Pokemon.SQL_DELETE_ENTRIES);
        db.execSQL(SniperContract.Sniper.SQL_DELETE_ENTRIES);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
