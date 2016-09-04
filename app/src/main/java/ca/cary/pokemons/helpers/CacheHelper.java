package ca.cary.pokemons.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.cary.pokemons.contracts.PokemonContract;
import ca.cary.pokemons.contracts.SniperContract;
import ca.cary.pokemons.dtos.PokemonDto;
import ca.cary.pokemons.dtos.SniperDto;
import ca.cary.pokemons.fragments.SettingsFragment;
import ca.cary.pokemons.objects.Pokemon;

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
        if (sniperDtos == null) {
            Log.e(TAG, "(sniperDtos == null)");
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {
            for (SniperDto sniperDto : sniperDtos) {
                ContentValues values = CacheParserHelper.sniperDtoToContentValues(sniperDto);
                db.insertWithOnConflict(SniperContract.Sniper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                savePokemon(sniperDto.getPokemon());
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteException! " + e.getMessage());
        }
    }

    public void savePokemon(Pokemon pokemon) {
        if (pokemon == null) {
            Log.e(TAG, "(pokemon == null)");
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {
            ContentValues values = CacheParserHelper.pokemonToContentValues(pokemon);
            db.insertWithOnConflict(PokemonContract.Pokemon.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteException! " + e.getMessage());
        }
    }

    public void savePokemonIcon(String pokemonName, byte[] pokemonIcon) {
        if (pokemonName == null) {
            Log.e(TAG, "(pokemonName == null)");
            return;
        }

        if (pokemonIcon == null) {
            Log.e(TAG, "(pokemonIcon == null)");
            return;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        try {
            ContentValues values = CacheParserHelper.pokemonIconToContentValues(pokemonIcon);
            db.update(PokemonContract.Pokemon.TABLE_NAME, values,
                    PokemonContract.Pokemon.POKEMON_NAME + " = ?",
                    new String[] {pokemonName});
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteException! " + e.getMessage());
        }
    }

    public List<PokemonDto> getHistoricalPokemonDtos(double latitude, double longitude) {
        List<PokemonDto> pokemonDtos = new ArrayList<>();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT "
                    + "a." + SniperContract.Sniper.SNIPER_POKEMON_NAME
                    + ", a." + SniperContract.Sniper.SNIPER_LATITUDE
                    + ", a." + SniperContract.Sniper.SNIPER_LONGITUDE
                    + ", a." + SniperContract.Sniper.SNIPER_DATE_DISAPPEAR
                    + ", a." + SniperContract.Sniper.SNIPER_POKEMON_ICON
                    + ", b." + PokemonContract.Pokemon.POKEMON_RARITY
                    + ", b." + PokemonContract.Pokemon.POKEMON_ICON
                    + " FROM "
                    + SniperContract.Sniper.TABLE_NAME + " a LEFT JOIN " + PokemonContract.Pokemon.TABLE_NAME + " b ON "
                    + "a." + SniperContract.Sniper.SNIPER_POKEMON_NAME + " = b." + PokemonContract.Pokemon.POKEMON_NAME
                    + " WHERE "
                    + "a." + SniperContract.Sniper.SNIPER_LATITUDE + " >= ? AND "
                    + "a." + SniperContract.Sniper.SNIPER_LATITUDE + " <= ? AND "
                    + "a." + SniperContract.Sniper.SNIPER_LONGITUDE + " >= ? AND "
                    + "a." + SniperContract.Sniper.SNIPER_LONGITUDE + " <= ?";

            String[] parameters = new String[] {
                    String.valueOf(latitude - SettingsFragment.REGION_DECIMAL_DEGREES_OFFSET),
                    String.valueOf(latitude + SettingsFragment.REGION_DECIMAL_DEGREES_OFFSET),
                    String.valueOf(longitude - SettingsFragment.REGION_DECIMAL_DEGREES_OFFSET),
                    String.valueOf(longitude + SettingsFragment.REGION_DECIMAL_DEGREES_OFFSET)
            };

            cursor = db.rawQuery(query, parameters);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pokemonDtos.add(CacheParserHelper.parsePokemonDto(cursor));
                cursor.moveToNext();
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteException! " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return pokemonDtos;
    }

}
