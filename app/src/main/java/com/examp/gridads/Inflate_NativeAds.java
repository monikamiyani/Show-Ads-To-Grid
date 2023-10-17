package com.examp.gridads;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

public class Inflate_NativeAds {
    Activity context;

    public Inflate_NativeAds(Activity context) {
        this.context = context;
    }

    public void inflat_admobnative(NativeAd unifiedNativeAd, ViewGroup viewGroup) {

        NativeAdView adView = (NativeAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.native_ads_layout, null);

        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));

        TextView button;
        button = adView.findViewById(R.id.ad_call_to_action);
        adView.setCallToActionView(button);

        ((TextView) adView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());

        if (unifiedNativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(unifiedNativeAd.getBody());
        }

        if (unifiedNativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
            button.setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(unifiedNativeAd);
        viewGroup.removeAllViews();
        viewGroup.addView(adView);

    }

}
