package com.example.alitamoor.adinrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mRecyclerViewItems;

    public RecyclerAdapter(List<Object> mRecyclerViewItems) {
        this.mRecyclerViewItems = mRecyclerViewItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 2){
            View adsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_item,viewGroup,false);
            return new AdsViewHolder(adsView);
        }

        View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_item,viewGroup,false);
        return new TextViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(getItemViewType(i) == 2){
            ((AdsViewHolder)viewHolder).adView.loadAd(((AdsDM) mRecyclerViewItems.get(i)).getAdRequest());
        }else {
            ((TextViewHolder)viewHolder).adView.setText(((TextDM) mRecyclerViewItems.get(i)).getString());
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mRecyclerViewItems.get(position) instanceof AdsDM){
            return 2;
        }
        return 1;
    }
}
