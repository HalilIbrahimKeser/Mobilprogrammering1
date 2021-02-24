package com.example.lab6_1_trivia_quiz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.lab6_1_trivia_quiz.R;
import com.example.lab6_1_trivia_quiz.models.Results;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {

    private List<Results> resultsDataSet;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvPhoto;
        private final TextView tvPhotoID;
        private final ImageView ivThumbnail;

        public MyViewHolder(View view) {
            super(view);
            tvPhoto = view.findViewById(R.id.tvPhoto);
            tvPhotoID = view.findViewById(R.id.tvPhotoID);
            ivThumbnail = view.findViewById(R.id.ivTumbnail);
            tvPhoto.setOnClickListener(this);
            ivThumbnail.setOnClickListener(this);
        }

        public TextView getTvPhoto() {
            return tvPhoto;
        }

        public TextView getTvPhotoID() {
            return tvPhotoID;
        }

        @Override
        public void onClick(View view) {
            if (ResultsAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public ResultsAdapter(List<Results> dataSet) {
        resultsDataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.results_row_item, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        String str = "Bilde: " + resultsDataSet.get(position).getTitle();
        myViewHolder.getTvPhoto().setText(str);

        String str1 = "Id: " + resultsDataSet.get(position).getId();
        String str2 = "\nUrl: " + resultsDataSet.get(position).getUrl();
        myViewHolder.getTvPhotoID().setText(str1 + str2);

        String photoUrl = resultsDataSet.get(position).getUrl();
        GlideUrl url = new GlideUrl(photoUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build());
        Glide.with(myViewHolder.itemView.getContext())
                .load(url)
                .into(myViewHolder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        return resultsDataSet.size();
    }

    public void setClickListener(ResultsAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Results getItem(int id) {
        return resultsDataSet.get(id);
    }

    public void setLocalDataSet(List<Results> photoDataSet) {
        this.resultsDataSet = photoDataSet;
    }
}