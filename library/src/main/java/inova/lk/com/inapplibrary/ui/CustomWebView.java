package inova.lk.com.inapplibrary.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import inova.lk.com.inapplibrary.R;
import inova.lk.com.inapplibrary.util.Utility;
import io.swagger.client.model.AdvertisementResponse;

/**
 * Created by Milan on 2/16/17.
 */

public class CustomWebView extends RelativeLayout implements View.OnTouchListener{

    private WebView myWebView;
    String addType;
    AdvertisementResponse advertisementResponse;
    Context context;

    public CustomWebView(Context context) {

        super(context);
        initializeViews(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray theAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomWebView);
        addType = theAttrs.getString(R.styleable.CustomWebView_addType);
        theAttrs.recycle();

        initializeViews(context, addType);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        TypedArray theAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomWebView);
        addType = theAttrs.getString(R.styleable.CustomWebView_addType);
        theAttrs.recycle();
        initializeViews(context, addType);
    }

    private void initializeViews(Context context) {

        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inapp_webview, this);
    }

    private void initializeViews(Context context, String temp) {

        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inapp_webview, this);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        myWebView = (WebView) this.findViewById(R.id.inAPpWebView);
        assert myWebView != null;
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        myWebView.setOnTouchListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

//        this.myWebView.loadData("<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'><link href='http://dev.inovaitsys.com:18085/InAppInWeb/resources/custom/css/video-responsive.css' rel='stylesheet' type='text/css' /></head><body><div class='fullscreen-bg'><a target='_blank' href='https://www.google.lk/'><video class='fullscreen-bg__video' autoplay loop><source src='http://dev.inovaitsys.com:18085/InAppInWeb/contents/ORG0001892017-03-17-11-27-08.mp4' /></video></a></div></body></html>", "text/html", "UTF-8");

        if (Integer.parseInt(addType) == 0 || Integer.parseInt(addType) == 1) {

            this.setLayoutParams(new LayoutParams(Utility.dpToPx(320), Utility.dpToPx(50)));
//            this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//            myWebView.getLayoutParams().width = LayoutParams.MATCH_PARENT;
            myWebView.getLayoutParams().width = Utility.dpToPx(320);
            myWebView.getLayoutParams().height = Utility.dpToPx(50);
        } else if (Integer.parseInt(addType) == 2 || Integer.parseInt(addType) == 3) {

            this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            myWebView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }else if (Integer.parseInt(addType) == 4) {

            this.setLayoutParams(this.getLayoutParams());
            myWebView.setLayoutParams(new LayoutParams(Utility.dpToPx(200), Utility.dpToPx(200)));
        }
    }

    public void setAddType(String addType) {

        this.addType = addType;
    }

    public String getAddType() {

        return addType;
    }

    public void setUrl(AdvertisementResponse advertisementResponse) {

        this.advertisementResponse = advertisementResponse;
        this.myWebView.loadData(advertisementResponse.getResponseObject().getContent(), "text/html", "UTF-8");
    }

    public void loadData(String data) {

        this.myWebView.loadData(data, "text/html", "UTF-8");
    }

    public String getUrl() {

        return addType;
    }

    public WebView getWebView() {

        return myWebView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && null != advertisementResponse && null != advertisementResponse.getResponseObject() && null != advertisementResponse.getResponseObject().getUrl()) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(advertisementResponse.getResponseObject().getUrl()));
            context.startActivity(browserIntent);
        }

        return false;
    }
}
