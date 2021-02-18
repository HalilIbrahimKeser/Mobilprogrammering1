package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<User> userDataSet;
    private UserAdapter.ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvUser;

        public MyViewHolder(View view) {
            super(view);
            tvUser = view.findViewById(R.id.tvUser);
            tvUser.setOnClickListener(this);
        }

        public TextView getTextView() {
            return tvUser;
        }

        @Override
        public void onClick(View view) {
            if (UserAdapter.this.itemClickListener != null) {
                int pos = getAdapterPosition();
                itemClickListener.onItemClick(view, pos);
            }
        }
    }

    public UserAdapter(List<User> dataSet) {
        userDataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row_item, viewGroup, false);
        return new UserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.MyViewHolder myViewHolder, final int position) {
        myViewHolder.getTextView().setText(userDataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userDataSet.size();
    }

    public void setClickListener(UserAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public User getItem(int id) {
        return userDataSet.get(id);
    }

    public void setLocalDataSet(List<User> userDataSet) {
        this.userDataSet = userDataSet;
    }
}
