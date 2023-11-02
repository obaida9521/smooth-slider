package com.developerobaida.smoothslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{
    List<SlideItem> slideItems;
    ViewPager2 viewPager2;
    Context context;


    public SliderAdapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
        this.context = viewPager2.getContext();
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(slideItems.get(position));
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
        void setImage(SlideItem slideItem){

            /*=====if you showing image from drawable you should remove Picasso and use==
            ==imageView.setImageResource(Integer.parseInt(slideItem.getImage()));==
             */
            Picasso.get().load(slideItem.getImage()).into(imageView);

            imageView.setOnClickListener(v -> Toast.makeText(context, "click event working", Toast.LENGTH_SHORT).show());
        }
    }
}
