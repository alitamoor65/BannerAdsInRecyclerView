package com.example.alitamoor.adinrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class TextViewHolder extends RecyclerView.ViewHolder {
    TextView adView;

    public TextView getAdView() {
        return adView;
    }

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        adView = itemView.findViewById(R.id.textViewItemID);
    }
}
