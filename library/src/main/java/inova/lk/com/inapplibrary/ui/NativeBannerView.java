package inova.lk.com.inapplibrary.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import inova.lk.com.inapplibrary.R;
import io.swagger.client.model.AdvertisementResponse;

/**
 * Created by Milan on 2/16/17.
 */

public class NativeBannerView extends RelativeLayout implements View.OnTouchListener{

    private WebView myWebView;
    private TextView textViewHeader;
    String contentUrl;
    AdvertisementResponse advertisementResponse;
    Context context;

    public NativeBannerView(Context context) {

        super(context);
        initializeViews(context);
    }

    public NativeBannerView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initializeViews(context);
    }

    public NativeBannerView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {

        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.native_banner, this);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        textViewHeader = (TextView) this.findViewById(R.id.textViewHeader);
        myWebView = (WebView) this.findViewById(R.id.nativeBannerContent);
        assert myWebView != null;
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        myWebView.setOnTouchListener(this);
    }

    @Override
    protected void onAttachedToWindow() {

        super.onAttachedToWindow();
        textViewHeader.setVisibility(GONE);
        this.myWebView.setVisibility(VISIBLE);
    }

    public void loadData(AdvertisementResponse advertisementResponse){

        this.advertisementResponse = advertisementResponse;
        this.myWebView.loadData(advertisementResponse.getResponseObject().getContent(),"text/html", "UTF-8");
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
