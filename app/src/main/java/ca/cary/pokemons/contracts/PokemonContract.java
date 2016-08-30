package ca.cary.pokemons.contracts;

import android.provider.BaseColumns;

/**
 * Created by jiayaohuang on 2016-08-26.
 */
public class PokemonContract implements BaseContract {

    private PokemonContract() {}

    public static abstract class Pokemon implements BaseColumns {

        public static final String TABLE_NAME = "pokemon";
        public static final String POKEMON_NAME = "name";
        public static final String POKEMON_TYPE = "type";
        public static final String POKEMON_RARITY = "rarity";
        public static final String POKEMON_ICON = "icon";
        public static final String POKEMON_DATE_CREATED = "date_created";

        public static final String SQL_INITIATE_ENTRIES =
                CREATE_TABLE + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + COMMA_SEP +
                        POKEMON_NAME + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                        POKEMON_TYPE + TEXT_TYPE + COMMA_SEP +
                        POKEMON_RARITY + TEXT_TYPE + COMMA_SEP +
                        POKEMON_ICON + BLOB_TYPE + COMMA_SEP +
                        POKEMON_DATE_CREATED + INTEGER_TYPE + ")";

        public static final String SQL_DELETE_ENTRIES =
                DROP_TABLE + TABLE_NAME;

        public static final String[] DEFAULT_PROJECTION = {
                POKEMON_NAME,
                POKEMON_TYPE,
                POKEMON_RARITY,
                POKEMON_ICON,
                POKEMON_DATE_CREATED
        };

    }

}
