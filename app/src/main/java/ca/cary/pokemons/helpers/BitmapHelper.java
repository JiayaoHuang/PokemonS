package ca.cary.pokemons.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ca.cary.pokemons.customs.FlushedInputStream;

/**
 * Created by jiayaohuang on 2016-09-04.
 */
public class BitmapHelper {

    public static final String TAG = BitmapHelper.class.getName();

    public static Bitmap resizeImages(FlushedInputStream inputStream, int width, int height) {
        byte[] bytes = readBytes(inputStream);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (options == null) {
            Log.e(TAG, "(options == null)");
            return 1;
        }

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        while ((height / inSampleSize) > reqHeight || (width / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }

        return inSampleSize;
    }

    private static byte[] readBytes(FlushedInputStream inputStream) {
        if (inputStream == null) {
            Log.e(TAG, "(inputStream == null)");
            return null;
        }

        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException! " + e.getMessage());
        }

        return output.toByteArray();
    }

}
