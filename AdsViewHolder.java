package com.example.alitamoor.adinrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdView;

public class AdsViewHolder extends RecyclerView.ViewHolder {
    AdView adView;

    public AdView getAdView() {
        return adView;
    }

    public AdsViewHolder(@NonNull View itemView) {
        super(itemView);
        adView = itemView.findViewById(R.id.adViewItemID);
    }
}
