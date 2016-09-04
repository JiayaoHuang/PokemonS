package ca.cary.pokemons.helpers;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jiayaohuang on 2016-09-03.
 */
public class SyncHelper {

    private static SyncHelper mSyncHelper;

    private ConcurrentHashMap<String, Boolean> processing;

    protected SyncHelper() {
        processing = new ConcurrentHashMap<>();
    }

    public static SyncHelper getInstance() {
        if (mSyncHelper == null) {
            mSyncHelper = new SyncHelper();
        }

        return mSyncHelper;
    }

    public void clear() {
        processing.clear();
    }

    public boolean isProcessing(String tag) {
        if (processing.containsKey(tag)) {
            Boolean value = processing.get(tag);
            return value == null? false : value;
        } else {
            processing.put(tag, false);
            return false;
        }
    }

    public void setProcessing(String tag, boolean process) {
        processing.put(tag, process);
    }

}
