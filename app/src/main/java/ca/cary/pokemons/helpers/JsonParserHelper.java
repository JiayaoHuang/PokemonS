package ca.cary.pokemons.helpers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.cary.pokemons.dtos.SniperDto;
import ca.cary.pokemons.objects.ChargeMove;
import ca.cary.pokemons.objects.FastMove;
import ca.cary.pokemons.objects.Pokemon;

/**
 * Created by jiayaohuang on 2016-08-29.
 */
public class JsonParserHelper {

    public static final String TAG = JsonParserHelper.class.getName();

    public static List<SniperDto> sniperJsonToSniperDtos(JSONObject sniperJson) throws JSONException, NumberFormatException, ParseException {
        if (sniperJson == null) {
            Log.e(TAG, "(sniperJson == null)");
            return null;
        }

        List<SniperDto> sniperDtos = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(sniperJson.getString("results"));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SniperDto sniperDto = new SniperDto();

            sniperDto.setSniperId(jsonObject.getInt("id"));

            String name = jsonObject.getString("name");
            String rarity = jsonObject.getString("rarity");
            int iv = jsonObject.getInt("iv");
            sniperDto.setPokemon(new Pokemon(name, rarity, iv));

            JSONArray attacks = jsonObject.getJSONArray("attacks");
            if (attacks.length() == 2) {
                FastMove fastMove = new FastMove(attacks.getString(0));
                ChargeMove chargeMove = new ChargeMove(attacks.getString(1));
                sniperDto.setPokemon(new Pokemon(name, fastMove, chargeMove, iv));
            }

            sniperDto.setLatitude(0.0);
            sniperDto.setLongitude(0.0);
            String coordinate = jsonObject.getString("coords");
            if (coordinate != null) {
                String[] coords = coordinate.split(",");

                if (coords.length == 2) {
                    sniperDto.setLatitude(Double.valueOf(coords[0]));
                    sniperDto.setLongitude(Double.valueOf(coords[1]));
                }
            }

            String until = jsonObject.getString("until");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Date disappearDate = formatter.parse(until.replace(".000Z", "+0000"));
            sniperDto.setDisappearDate(disappearDate);

            sniperDto.setIcon(jsonObject.getString("icon"));

            sniperDtos.add(sniperDto);
        }

        return sniperDtos;
    }

}
