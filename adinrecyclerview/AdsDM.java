package com.example.alitamoor.adinrecyclerview;

import com.google.android.gms.ads.AdRequest;

public class AdsDM {
    AdRequest adRequest;

    public AdsDM(AdRequest adRequest) {
        this.adRequest = adRequest;
    }

    public AdRequest getAdRequest() {
        return adRequest;
    }
}
