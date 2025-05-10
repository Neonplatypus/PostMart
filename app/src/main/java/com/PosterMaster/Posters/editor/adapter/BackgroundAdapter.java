package com.PosterMaster.Posters.editor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PosterMaster.Posters.binding.BindingAdaptet;
import com.PosterMaster.Posters.classes.Functions;
import com.PosterMaster.Posters.databinding.ItemBackgroundBinding;
import com.PosterMaster.Posters.interfaces.AdapterClickListener;
import com.PosterMaster.Posters.model.BackgroundModel;

import java.util.ArrayList;
import java.util.List;

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.ViewHolder> {

    Context context;
    List<BackgroundModel> list = new ArrayList<>();
    AdapterClickListener clickListener;

    public BackgroundAdapter(Context context, List<BackgroundModel> list, AdapterClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBackgroundBinding binding = ItemBackgroundBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            BindingAdaptet.setImageUrl(holder.binding.imgView, Functions.getItemBaseUrl(list.get(position).item_url));
            holder.binding.imgView.setOnClickListener(view -> {
                clickListener.onItemClick(view,position,list.get(position));
            });
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemBackgroundBinding binding;

        public ViewHolder(@NonNull ItemBackgroundBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
