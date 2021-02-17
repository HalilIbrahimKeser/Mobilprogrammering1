package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;

import java.util.List;

public class SinglePhotoAdapter extends RecyclerView.Adapter<SinglePhotoAdapter.MyViewHolder> {

    private List<Photo> photoDataSet;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView ivImage;

        public MyViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.ivImage);
            ivImage.setOnClickListener(this);  //fjernes
        }


        @Override
        public void onClick(View view) {
            if (SinglePhotoAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public SinglePhotoAdapter(List<Photo> dataSet) {
        photoDataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_photo_row_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        String photoUrl = photoDataSet.get(position).getUrl();
        GlideUrl url = new GlideUrl(photoUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build());
        Glide.with(myViewHolder.itemView.getContext())
                .load(url)
                .into(myViewHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        return photoDataSet.size();
    }

    public void setClickListener(SinglePhotoAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Photo getItem(int id) {
        return photoDataSet.get(id);
    }

    public void setLocalDataSet(List<Photo> photoDataSet) {
        this.photoDataSet = photoDataSet;
    }
}