package com.PosterMaster.Posters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PosterMaster.Posters.R;
import com.PosterMaster.Posters.databinding.ItemSliderLayoutBinding;
import com.PosterMaster.Posters.databinding.ItemWithdrawHistriBinding;
import com.PosterMaster.Posters.interfaces.AdapterClickListener;
import com.PosterMaster.Posters.model.SliderModel;

import java.util.List;


public class SliderAdapterRecycler extends RecyclerView.Adapter<SliderAdapterRecycler.ViewHolder> {

    private List<SliderModel> list;
    private AdapterClickListener listener;
    Context context;

    public SliderAdapterRecycler(Context context, List<SliderModel> list, AdapterClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSliderLayoutBinding binding = ItemSliderLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SliderModel model = list.get(position);
        holder.binding.setSlider(model);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,position,list.get(position));
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        ItemSliderLayoutBinding binding;

        ViewHolder(@NonNull ItemSliderLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }

}