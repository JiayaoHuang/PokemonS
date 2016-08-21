package ca.cary.pokemons.helpers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import ca.cary.pokemons.receivers.SniperReceiver;

/**
 * Created by jiayaohuang on 2016-08-19.
 */
public class SniperAlarmHelper {

    public static final String SNIPER_SERVICE_CALL_DEFAULT_DELAY_TIME = "30000";

    private static SniperAlarmHelper sniperAlarmHelper;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private SniperAlarmHelper() {}

    public static SniperAlarmHelper getInstance() {
        if (sniperAlarmHelper == null) {
            sniperAlarmHelper = new SniperAlarmHelper();
        }

        return sniperAlarmHelper;
    }

    public synchronized void setAlarm(Context context, long delayTime) {
        endAlarm();

        Intent intent = new Intent(context, SniperReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delayTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delayTime, pendingIntent);
        }
    }

    public synchronized void endAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            pendingIntent = null;
        }
    }

}
