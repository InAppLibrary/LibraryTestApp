package inova.lk.com.librarytestapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import inova.lk.com.inapplibrary.ads.AdRequest;
import inova.lk.com.inapplibrary.util.Utility;
import inova.lk.com.librarytestapp.R;
import inova.lk.com.librarytestapp.utils.Constants;

public class SecondaryActivity extends AppCompatActivity {

    WebView webView;
    String htmlText = " %s ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadData(String.format(htmlText, getString(R.string.page_desc)), "text/html", "utf-8");

        int addType = getIntent().getExtras().getInt(Constants.ADD_TYPE);

        switch (addType){

            case R.id.fullScreenBanner:
                fullScreenBanner();
                break;

            case R.id.topBanner:
                topBanner();
                break;

            case R.id.bottomBanner:
                bottomBanner();
                break;

            case R.id.videoBanner:
                videoBanner();
                break;

            case R.id.allBanners:
                allBanners();
                break;
        }

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_layout, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Utility.dpToPx(300), Utility.dpToPx(50));//  Utils.toDIP(activity, BANNER_HEIGHT));
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        layoutParams.setMargins(0, Utility.getActionBarSize(this), 0, 0);
        getWindow().addContentView(layout, layoutParams);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);

        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
        wmlp.x = 0;   //x position
        int actionBarHeight = Utility.getActionBarSize(this);
        if (actionBarHeight != -1) {
            wmlp.y = actionBarHeight;   //y position
        }
    }

    private void allBanners() {

    }

    private void videoBanner() {

        AdRequest adRequest = new AdRequest(this, getString(R.string.inapp_token));
        adRequest.enableVideoBanner(true);
    }

    private void bottomBanner() {

        AdRequest adRequest = new AdRequest(this, getString(R.string.inapp_token));
        adRequest.enableBottomBanner(true);
    }

    private void topBanner() {

        AdRequest adRequest = new AdRequest(this, getString(R.string.inapp_token));
        adRequest.enableTopBanner(true);
    }

    private void fullScreenBanner() {

        AdRequest adRequest = new AdRequest(this, getString(R.string.inapp_token));
        adRequest.enableInterstitialBanner(true);
    }
}
