package com.example.lab6_1_trivia_quiz.adapters;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab6_1_trivia_quiz.models.QuizData;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    private List<QuizData> quizData;
    private ItemClickListener itemClickListener;
    private final String fileNameInternal = "running_quiz.json";

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private final TextView tvPhoto;
        //private final TextView tvPhotoID;
        //private final ImageView ivThumbnail;

        public MyViewHolder(View view) {
            super(view);
            //tvPhoto = view.findViewById(R.id.tvPhoto);
            //tvPhotoID = view.findViewById(R.id.tvPhotoID);
            //ivThumbnail = view.findViewById(R.id.ivTumbnail);
            //tvPhoto.setOnClickListener(this);
            //ivThumbnail.setOnClickListener(this);
            //TODO her kommer verdiene som skal sendes med viewholder
        }

        //public TextView getTvPhoto() {
        //return tvPhoto;
        //}

        //public TextView getTvPhotoID() {
        //return tvPhotoID;
        //}

        @Override
        public void onClick(View view) {
            if (QuizAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public QuizAdapter(List<QuizData> dataSet) {
        quizData = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //View view = LayoutInflater.from(viewGroup.getContext()).
        //inflate(R.layout.results_row_item, viewGroup, false);
        //return new MyViewHolder(view);
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        /*
        //TODO her skal vi binde verdiene og sende med

        String photoUrl = resultsDataSet.get(position).getUrl();
        GlideUrl url = new GlideUrl(photoUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build());
        Glide.with(myViewHolder.itemView.getContext())
                .load(url)
                .into(myViewHolder.ivThumbnail);

         */

    }

    @Override
    public int getItemCount() {
        return quizData.size();
    }

    public void setClickListener(QuizAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public QuizData getItem(int id) {
        return quizData.get(id);
    }

    public void setLocalDataSet(List<QuizData> quizData) {
        this.quizData = quizData;
    }
}