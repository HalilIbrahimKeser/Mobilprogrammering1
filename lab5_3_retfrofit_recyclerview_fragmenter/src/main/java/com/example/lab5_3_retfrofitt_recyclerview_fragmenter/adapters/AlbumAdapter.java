package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private List<Album> albumDataSet;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvAlbum;

        public MyViewHolder(View view) {
            super(view);
            tvAlbum = (TextView) view.findViewById(R.id.tvAlbum);
            tvAlbum.setOnClickListener(this);
        }

        public TextView getTextView() {
            return tvAlbum;
        }

        @Override
        public void onClick(View view) {
            if (AlbumAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public AlbumAdapter(List<Album> dataSet) {
        albumDataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_row_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        myViewHolder.getTextView().setText(albumDataSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return albumDataSet.size();
    }

    public void setClickListener(AlbumAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public Album getItem(int id) {
        return albumDataSet.get(id);
    }

    public void setLocalDataSet(List<Album> localDataSet) {
        this.albumDataSet = localDataSet;
    }
}