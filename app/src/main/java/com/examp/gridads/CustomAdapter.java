package com.examp.gridads;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;

public class CustomAdapter extends ListAdapter<DataModel, RecyclerView.ViewHolder> {

    public static final int viewTypeItem = 0;
    public static final int viewTypeAd = 2;

    Activity conn;

    public CustomAdapter(Activity context) {
        super(new DiffUtilCallback());
        conn = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case viewTypeItem:
                return new ViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
            case viewTypeAd:
                return new AdsHolder(inflater.inflate(R.layout.ads_layout, parent, false));
            default:
                throw new IllegalArgumentException("You must supply a valid type for this adapter");
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == viewTypeItem)
        {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.images.setImageResource(getItem(position).getImages());
        }
        else {
            AdsHolder adsholder = (AdsHolder) holder;

            AdLoader.Builder builder = new AdLoader.Builder(conn, "ca-app-pub-3940256099942544/2247696110");
            builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    new Inflate_NativeAds(conn).inflat_admobnative(nativeAd, adsholder.frameads);
                }
            });

            AdLoader adLoader = builder.withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {

                    AdLoader.Builder builder = new AdLoader.Builder(conn, "ca-app-pub-3940256099942544/2247696110");

                    builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {

                            new Inflate_NativeAds(conn).inflat_admobnative(nativeAd, adsholder.frameads);
                        }
                    });

                    AdLoader adLoader = builder.withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {

                        }
                    }).build();
                    adLoader.loadAd(new AdRequest.Builder().build());

                }
            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().ordinal();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView images;

        ViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.item_text);
        }

    }

    class AdsHolder extends RecyclerView.ViewHolder {

        FrameLayout frameads;
        AdsHolder(View itemView) {
            super(itemView);
            frameads = itemView.findViewById(R.id.frameads);
        }

    }

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<DataModel> {
        @Override
        public boolean areItemsTheSame(DataModel oldItem, DataModel newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(DataModel oldItem, DataModel newItem) {
            return false;
        }
    }

}

