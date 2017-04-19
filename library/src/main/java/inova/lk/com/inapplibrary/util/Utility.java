package inova.lk.com.inapplibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import inova.lk.com.inapplibrary.R;
import inova.lk.com.inapplibrary.dialog.FullScreenBannerDialogFragment;
import inova.lk.com.inapplibrary.ui.CustomWebView;
import io.swagger.client.model.AdvertisementResponse;

/**
 * Created by Milan on 2/23/17.
 */

public class Utility {

    final static String TAG = Utility.class.getCanonicalName();

    public static void fullScreenBanner(final Activity activity, AdvertisementResponse advertisementResponse) {

        FullScreenBannerDialogFragment fullScreenBannerDialogFragment = FullScreenBannerDialogFragment.newInstance(activity, advertisementResponse);
        fullScreenBannerDialogFragment.show(((AppCompatActivity) activity).getSupportFragmentManager(), TAG);
    }

    public static void bannerTopBottom(final Activity activity, boolean isTopBanner, AdvertisementResponse advertisementResponse) {

        LayoutInflater inflater = activity.getLayoutInflater();
        final View layout = inflater.inflate(R.layout.dialog_layout, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Utility.dpToPx(300), Utility.dpToPx(50));//  Utils.toDIP(activity, BANNER_HEIGHT));
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);

        if (isTopBanner) {

            layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
            layoutParams.setMargins(0, Utility.getActionBarSize(activity), 0, 0);

        } else {

            layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        }

        CustomWebView customWebView = (CustomWebView) layout.findViewById(R.id.testWebView);
        ImageView closeView = (ImageView) layout.findViewById(R.id.testImageView);

        customWebView.setUrl(advertisementResponse);
        customWebView.getWebView().setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {


                }
            }
        });

        closeView.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        ((ViewGroup) layout.getParent()).removeView(layout);
                    }
                });

        activity.getWindow().addContentView(layout, layoutParams);
    }

    public static int getActionBarSize(Activity activity) {

        TypedValue tv = new TypedValue();

        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
            return actionBarHeight;
        }

        return -1;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
