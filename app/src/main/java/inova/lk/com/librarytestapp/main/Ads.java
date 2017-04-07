package inova.lk.com.librarytestapp.main;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import inova.lk.com.inapplibrary.util.Utility;

/**
 * Created by Milan on 4/7/17.
 */

public class Ads {
    // replace it with your actual value
    final private static int BANNER_HEIGHT = 75;

    public static void showBottomBanner(Activity activity)  {
        // replace with your actual ad API code

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(inova.lk.com.inapplibrary.R.layout.dialog_layout, null);
        final View banner = layout;
//        final Banner banner = new Banner(activity);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                Utility.dpToPx(50));//  Utils.toDIP(activity, BANNER_HEIGHT));

        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        activity.getWindow().addContentView(banner, layoutParams);
    }

    public static void setupContentViewPadding(Activity activity, boolean top, int margin) {
        View view = ((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        if (top)
            view.setPadding(view.getPaddingLeft(), margin, view.getPaddingRight(), view.getPaddingBottom());
        else
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), margin);
    }
}