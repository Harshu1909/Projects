package com.example.harshupatel.inviko.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Dharmesh on 8/27/2016.
 */
public class API {

    public static int dpToPx(double dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round((int)dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(double px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private static final String TAG = "API";

    public static void toast(Context context, String message) {
        Log.e(TAG, "toast: ");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

   /* public static void startSplashCounter(final Context context, final Class<?> gotoclass) {
        Log.e(TAG, "startSplashCounter() returned: ");
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                context.startActivity(new Intent(context, LocationListActivity.class));
                ((Activity) context).finish();
            }
        }.start();
    }*/

    public static void createAppShortCut(Context context, Class<?> classname, String appname, int logoId) {
        Log.e(TAG, "createAppShortCut() returned: ");

        SharedPreferences sharedPreferences = context.getSharedPreferences("SHORTCUT_PREF", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("isappinstalled", false)) {
            Intent shortcutIntent = new Intent(context,
                    classname);
            shortcutIntent.setAction(Intent.ACTION_MAIN);

            Intent addIntent = new Intent();
            addIntent
                    .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, appname);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(context,
                            logoId));
            addIntent
                    .setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
            context.sendBroadcast(addIntent);

            addIntent
                    .setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            context.sendBroadcast(addIntent);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isappinstalled", true);
            editor.apply();
            editor.commit();
        }
    }

    public static Bitmap getLocalStorageImage(String filepath) {
        Log.e(TAG, "getLocalStorageImage() called with: " + "filepath = [" + filepath + "]");
        if (filepath != null && !filepath.equals("")) {
            if (new File(filepath).exists()) {
                return BitmapFactory.decodeFile(filepath);
            }
        }
        return null;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method to get screen size.
     *
     * @param context
     * @return window manager instance which contains width and height of the
     * screen
     */
    public static DisplayMetrics getScreenSize(Context context) {
        // TODO Auto-generated method stub
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

   /* public static boolean checkPlayServices(Context context) {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }*/
}
