package com.PosterMaster.Posters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PosterMaster.Posters.databinding.ItemLanguageBinding;
import com.PosterMaster.Posters.interfaces.AdapterClickListener;
import com.PosterMaster.Posters.model.LanguageModel;

import java.util.ArrayList;


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private ArrayList<LanguageModel> list;
    private AdapterClickListener listener;
    Context context;

    public LanguageAdapter(Context context,ArrayList<LanguageModel> list, AdapterClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLanguageBinding binding = ItemLanguageBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setLanguage(list.get(position));
        if (list.get(position).isSelected()){
            holder.binding.switchBtn.setChecked(true);
        }else {
            holder.binding.switchBtn.setChecked(false);
        }
        holder.binding.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    list.get(position).setSelected(true);
                }else{
                    list.get(position).setSelected(false);
                }
            }
        });
        holder.bind(position, list.get(position) , listener);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        ItemLanguageBinding binding;

        ViewHolder(@NonNull ItemLanguageBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(final int pos, final LanguageModel model, final AdapterClickListener listener) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,pos,model);
                }
            });
        }

    }

}