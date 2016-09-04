package ca.cary.pokemons.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.Date;

import ca.cary.pokemons.contracts.PokemonContract;
import ca.cary.pokemons.contracts.SniperContract;
import ca.cary.pokemons.dtos.PokemonDto;
import ca.cary.pokemons.dtos.SniperDto;
import ca.cary.pokemons.objects.Pokemon;

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

        values.put(SniperContract.Sniper.SNIPER_POKEMON_ICON, sniperDto.getIcon());

        Date now = new Date();
        values.put(SniperContract.Sniper.SNIPER_DATE_CREATED, now.getTime());

        return values;
    }

    public static ContentValues pokemonToContentValues(Pokemon pokemon) {
        if (pokemon == null) {
            Log.e(TAG, "(pokemon == null)");
            return null;
        }

        ContentValues values = new ContentValues();

        values.put(PokemonContract.Pokemon.POKEMON_NAME, pokemon.getName());
        values.put(PokemonContract.Pokemon.POKEMON_TYPE, pokemon.getType());
        values.put(PokemonContract.Pokemon.POKEMON_RARITY, pokemon.getRarity());

        Date now = new Date();
        values.put(PokemonContract.Pokemon.POKEMON_DATE_CREATED, now.getTime());

        return values;
    }

    public static ContentValues pokemonIconToContentValues(byte[] pokemonIcon) {
        if (pokemonIcon == null) {
            Log.e(TAG, "(pokemonIcon == null)");
            return null;
        }

        ContentValues values = new ContentValues();

        values.put(PokemonContract.Pokemon.POKEMON_ICON, pokemonIcon);

        return values;
    }

    public static PokemonDto parsePokemonDto(Cursor cursor) {
        if (cursor == null) {
            Log.e(TAG, "(cursor == null)");
            return null;
        }

        PokemonDto pokemonDto = new PokemonDto();
        Pokemon pokemon = null;

        int index = cursor.getColumnIndex(SniperContract.Sniper.SNIPER_POKEMON_NAME);
        if (index != -1 && !cursor.isNull(index)) {
            pokemon = new Pokemon(cursor.getString(index));
        }

        index = cursor.getColumnIndex(SniperContract.Sniper.SNIPER_LATITUDE);
        if (index != -1 && !cursor.isNull(index)) {
            pokemonDto.setLatitude(cursor.getDouble(index));
        }

        index = cursor.getColumnIndex(SniperContract.Sniper.SNIPER_LONGITUDE);
        if (index != -1 && !cursor.isNull(index)) {
            pokemonDto.setLongitude(cursor.getDouble(index));
        }

        index = cursor.getColumnIndex(SniperContract.Sniper.SNIPER_DATE_DISAPPEAR);
        if (index != -1 && !cursor.isNull(index)) {
            pokemonDto.setDisappearDate(new Date(cursor.getLong(index)));
        }

        index = cursor.getColumnIndex(SniperContract.Sniper.SNIPER_POKEMON_ICON);
        if (index != -1 && !cursor.isNull(index)) {
            pokemonDto.setIconPath(cursor.getString(index));
        }

        index = cursor.getColumnIndex(PokemonContract.Pokemon.POKEMON_RARITY);
        if (index != -1 && !cursor.isNull(index) && pokemon != null) {
            pokemon.setRarity(cursor.getString(index));
        }

        index = cursor.getColumnIndex(PokemonContract.Pokemon.POKEMON_ICON);
        if (index != -1 && !cursor.isNull(index)) {
            pokemonDto.setIcon(cursor.getBlob(index));
        }

        if (pokemon == null) {
            Log.e(TAG, "(pokemon == null)");
            return null;
        }

        pokemonDto.setPokemon(pokemon);

        return pokemonDto;
    }

}
