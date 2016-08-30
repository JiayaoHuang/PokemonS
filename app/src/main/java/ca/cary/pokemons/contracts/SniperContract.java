package ca.cary.pokemons.contracts;

import android.provider.BaseColumns;

/**
 * Created by jiayaohuang on 2016-08-28.
 */
public class SniperContract implements BaseContract {

    private SniperContract() {}

    public static abstract class Sniper implements BaseColumns {

        public static final String TABLE_NAME = "sniper";
        public static final String SNIPER_ID = "sniper_id";
        public static final String SNIPER_POKEMON_NAME = "pokemon_name";
        public static final String SNIPER_LATITUDE = "latitude";
        public static final String SNIPER_LONGITUDE = "longitude";
        public static final String SNIPER_DATE_DISAPPEAR = "sniper_date_disappear";
        public static final String SNIPER_DATE_CREATED = "date_created";

        public static final String SQL_INITIATE_ENTRIES =
                CREATE_TABLE + TABLE_NAME + " (" +
                        _ID + INTEGER_TYPE + COMMA_SEP +
                        SNIPER_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                        SNIPER_POKEMON_NAME + TEXT_TYPE + COMMA_SEP +
                        SNIPER_LATITUDE + REAL_TYPE + COMMA_SEP +
                        SNIPER_LONGITUDE + REAL_TYPE + COMMA_SEP +
                        SNIPER_DATE_DISAPPEAR + INTEGER_TYPE + COMMA_SEP +
                        SNIPER_DATE_CREATED + INTEGER_TYPE + ")";

        public static final String SQL_DELETE_ENTRIES =
                DROP_TABLE + TABLE_NAME;

        public static final String[] DEFAULT_PROJECTION = {
                SNIPER_ID,
                SNIPER_POKEMON_NAME,
                SNIPER_LATITUDE,
                SNIPER_LONGITUDE,
                SNIPER_DATE_DISAPPEAR,
                SNIPER_DATE_CREATED
        };

    }

}
