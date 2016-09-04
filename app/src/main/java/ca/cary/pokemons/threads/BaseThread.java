package ca.cary.pokemons.threads;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public abstract class BaseThread {

    public static String getTag(String action, String id) {
        return action + "." + id;
    }

}
