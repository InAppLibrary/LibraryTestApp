package inova.lk.com.inapplibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import inova.lk.com.inapplibrary.R;
import inova.lk.com.inapplibrary.ui.CustomWebView;
import io.swagger.client.model.AdvertisementResponse;

public class FullScreenBannerDialogFragment extends DialogFragment {

    public static final String TAG = FullScreenBannerDialogFragment.class.getSimpleName();
    CustomWebView customWebView;
    AdvertisementResponse advertisementResponse;
    Activity activity;

    public FullScreenBannerDialogFragment() {
    }

    public static FullScreenBannerDialogFragment newInstance(Activity activity, AdvertisementResponse advertisementResponse) {

        FullScreenBannerDialogFragment fullScreenBannerDialogFragment = new FullScreenBannerDialogFragment();
        fullScreenBannerDialogFragment.setup(activity, advertisementResponse);
        return fullScreenBannerDialogFragment;
    }

    public void setup(Activity activity, AdvertisementResponse advertisementResponse) {

        this.advertisementResponse = advertisementResponse;
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);

        customWebView = (CustomWebView) dialogView.findViewById(R.id.testWebView);
//        customWebView.loadData("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><link href='http://dev.inovaitsys.com:18085/InAppInWeb/resources/custom/css/video-responsive.css' rel='stylesheet' type='text/css' /></head><body><div class='fullscreen-bg'><a target='_blank' href='https://www.google.lk/'><video class='fullscreen-bg__video' autoplay loop><source src='http://signage.dialog.lk/default-videos/contents/Survivor%20Worlds%20Apart%2020-sec%20TV%20ad2016-09-06-17-29-46_ORG000003_2017-01-27-09-49-13.mp4' /></video></a></div></body></html>");

        customWebView.setAddType("2");
        customWebView.setUrl(advertisementResponse);
        ImageView closeView = (ImageView) dialogView.findViewById(R.id.testImageView);
        closeView.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if(customWebView != null) {

                            customWebView.removeAllViews();
                            WebView webView = customWebView.getWebView();

                            if(null != webView) {

//                                webView.clearHistory();
//                                webView.clearCache(true);
                                webView.loadUrl("about:blank");
//                                webView.freeMemory();
//                                webView.pauseTimers();
                                webView = null;
                            }
                        }

//                        dismiss();
                        dismissAllowingStateLoss();
                    }
                });

        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);

            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        activity.onBackPressed();
                    }
                    return true;
                }
            });

            Window window = dialog.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);

            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wmlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
    }
}
