package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<Photo> photoDataSet;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvPhoto;

        public MyViewHolder(View view) {
            super(view);
            tvPhoto = (TextView) view.findViewById(R.id.tvPhoto);
            tvPhoto.setOnClickListener(this);
        }

        public TextView getTvPhoto() {
            return tvPhoto;
        }

        @Override
        public void onClick(View view) {
            if (PhotoAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public PhotoAdapter(List<Photo> dataSet) {
        photoDataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_row_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        myViewHolder.getTvPhoto().setText(photoDataSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return photoDataSet.size();
    }

    public void setClickListener(PhotoAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public Photo getItem(int id) {
        return photoDataSet.get(id);
    }

    public void setLocalDataSet(List<Photo> photoDataSet) {
        this.photoDataSet = photoDataSet;
    }
}