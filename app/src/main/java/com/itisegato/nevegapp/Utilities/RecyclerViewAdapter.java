package com.itisegato.nevegapp.Utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.itisegato.nevegapp.Activities.ImageActivity;
import com.itisegato.nevegapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final Context mContext ;
    protected List<Image> mData ;

    public RecyclerViewAdapter(Context mContext, List<Image> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_images,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_image_title.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getThumbnail()).into(holder.image_thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(mContext, ImageActivity.class);
                        intent.putParcelableArrayListExtra("ListImages", (ArrayList<? extends Parcelable>) mData);
                        intent.putExtra("pos", position);
                        mContext.startActivity(intent);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                v.startAnimation(anim);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_image_title;
        ImageView image_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_image_title = (TextView) itemView.findViewById(R.id.image_title_id) ;
            image_thumbnail = (ImageView) itemView.findViewById(R.id.image_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}