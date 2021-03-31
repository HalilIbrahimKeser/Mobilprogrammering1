package com.example.lab8_oblig_4_room.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.lab8_oblig_4_room.model.User;

public class ListAdapter extends androidx.recyclerview.widget.ListAdapter<User, ViewHolder> {

    public ListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User current = getItem(position);
        holder.bind(current.getmName());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getmName().equals(newItem.getmName());
        }
    }
}