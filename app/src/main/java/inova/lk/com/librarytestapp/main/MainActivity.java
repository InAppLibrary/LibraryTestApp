package inova.lk.com.librarytestapp.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import inova.lk.com.inapplibrary.dialog.FullScreenBannerDialogFragment;
import inova.lk.com.inapplibrary.util.Utility;
import inova.lk.com.librarytestapp.R;
import inova.lk.com.librarytestapp.utils.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    Button fullScreenBanner;
    Button topBanner;
    Button bottomBanner;
    Button videoBanner;
    Button nativeBanner;
    Button allBanners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullScreenBanner = (Button) findViewById(R.id.fullScreenBanner);
        topBanner = (Button) findViewById(R.id.topBanner);
        bottomBanner = (Button) findViewById(R.id.bottomBanner);
        nativeBanner = (Button) findViewById(R.id.nativeBanner);
        videoBanner = (Button) findViewById(R.id.videoBanner);
        allBanners = (Button) findViewById(R.id.allBanners);

        fullScreenBanner.setOnClickListener(this);
        topBanner.setOnClickListener(this);
        bottomBanner.setOnClickListener(this);
        nativeBanner.setOnClickListener(this);
        videoBanner.setOnClickListener(this);
        allBanners.setOnClickListener(this);

//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.dialog_layout, null);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Utility.dpToPx(300), Utility.dpToPx(50));//  Utils.toDIP(activity, BANNER_HEIGHT));
//        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
//        layoutParams.setMargins(0, Utility.getActionBarSize(this), 0, 0);
//        getWindow().addContentView(layout, layoutParams);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
//
//        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
//        wmlp.x = 0;   //x position
//        int actionBarHeight = Utility.getActionBarSize(this);
//        if (actionBarHeight != -1) {
//            wmlp.y = actionBarHeight;   //y position
//        }
//
//        View layout1 = inflater.inflate(R.layout.dialog_layout, null);
//        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(Utility.dpToPx(300), Utility.dpToPx(50));//  Utils.toDIP(activity, BANNER_HEIGHT));
//        layoutParams1.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
//        layoutParams1.setMargins(0, Utility.getActionBarSize(this), 0, 0);
//        getWindow().addContentView(layout1, layoutParams1);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
//
//        WindowManager.LayoutParams wmlp1 = getWindow().getAttributes();
//        wmlp1.x = 0;   //x position
//        int actionBarHeight1 = Utility.getActionBarSize(this);
//        if (actionBarHeight1 != -1) {
//            wmlp1.y = actionBarHeight;   //y position
//        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.fullScreenBanner:
                fullScreenBanner();
                break;

            case R.id.topBanner:
                topBanner();
                break;

            case R.id.bottomBanner:
                bottomBanner();
                break;

            case R.id.nativeBanner:
                nativeBanner();
                break;

            case R.id.videoBanner:
                videoBanner();
                break;

            case R.id.allBanners:
//                allBanners();
                break;
        }
    }

    private void allBanners() {

        openActivity(SecondaryActivity.class, R.id.allBanners);
    }

    private void nativeBanner() {

        openActivity(NativeBannerActivity.class, R.id.nativeBanner);
    }

    private void videoBanner() {

        openActivity(SecondaryActivity.class, R.id.videoBanner);
    }

    private void bottomBanner() {

        openActivity(SecondaryActivity.class, R.id.bottomBanner);
    }

    private void topBanner() {

        openActivity(SecondaryActivity.class, R.id.topBanner);
    }

    private void fullScreenBanner() {

        openActivity(SecondaryActivity.class, R.id.fullScreenBanner);
    }

    public void openActivity(Class activity, int addType){

        Intent intent = new Intent(MainActivity.this, activity);
        intent.putExtra(Constants.ADD_TYPE, addType);
        startActivity(intent);
    }

//    class NetworkExecution extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
////            ConfigApi configApi = new ConfigApi();
////            try {
////                Configuration configuration = (Configuration) configApi.commerceCatalogProductCatalogActorGetAgentConfigurationsGet();
////                Log.d(TAG, "MallCategoryId : " + configuration.getMallCategoryId());
////                Log.d(TAG, "DealCategoryId : " + configuration.getDealCategoryId());
////                Log.d(TAG, "ProductVersion : " + configuration.getProductVersion());
////                Log.d(TAG, "Commission : " + configuration.getCommision());
////
////            } catch (TimeoutException e) {
////                e.printStackTrace();
////            } catch (ExecutionException e) {
////                e.printStackTrace();
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            } catch (ApiException e) {
////                e.printStackTrace();
////            }
//
//            AdvertisementsApi advertisementsApi = new AdvertisementsApi();
//            try {
//                AdvertisementRequest requestBody = new AdvertisementRequest();
//                requestBody.setAddTypeId("6");
//                AdvertisementResponse advertisement = (AdvertisementResponse)
//                        advertisementsApi.fetchaddsPost(
//                                "bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXRBZCIsImFwcElkIjoiNjgifQ.l4x-U43NxzbByNO69M9TtEzTqQ2iHYKJaVf3-4YfWq4", requestBody);
//                Log.d(TAG, advertisement.getMsg());
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ApiException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}
