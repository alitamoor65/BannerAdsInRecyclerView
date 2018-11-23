package com.example.alitamoor.adinrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.ads.AdRequest;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String TAG = "Adapter";
    private List<Object> mRecyclerViewItems;
    Context context;
    private int lastPosition;

    public RecyclerAdapter(Context context,List<Object> mRecyclerViewItems) {
        this.context = context;
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
        View view;
        if(getItemViewType(i) == 2){
            ((AdsViewHolder)viewHolder).adView.loadAd(((AdsDM) mRecyclerViewItems.get(i)).getAdRequest());
            view = ((AdsViewHolder) viewHolder).adView;
        }else {
            ((TextViewHolder)viewHolder).adView.setText(((TextDM) mRecyclerViewItems.get(i)).getString());
            view = ((TextViewHolder) viewHolder).adView;
        }
        if (lastPosition < viewHolder.getAdapterPosition()) {
            startAnimation(view, viewHolder.getAdapterPosition());
            lastPosition = viewHolder.getAdapterPosition();
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

    private void startAnimation(View view, int position) {
        if (position > 2) {
            Log.d(TAG, "animation start");
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
            animation.setInterpolator(new LinearOutSlowInInterpolator());
            animation.setStartTime(500);
            view.startAnimation(animation);
        }
    }

     @Override
    public void onViewDetachedFromWindow(final RecyclerView.ViewHolder viewHolder) {
        View view;
        super.onViewDetachedFromWindow(viewHolder);
         if(getItemViewType(viewHolder.getAdapterPosition()) == 2){
             view = ((AdsViewHolder) viewHolder).adView;
         }else {
             view = ((TextViewHolder) viewHolder).adView;
         }
        ViewCompat.animate(view).cancel();
        Log.d(TAG, "animation cancel");
    }
}
