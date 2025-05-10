package com.PosterMaster.Posters.editor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PosterMaster.Posters.R;
import com.PosterMaster.Posters.binding.BindingAdaptet;
import com.PosterMaster.Posters.classes.Functions;
import com.PosterMaster.Posters.model.StickerModel;


import java.util.List;

public class StickersAdapter extends RecyclerView.Adapter<StickersAdapter.ViewHolder> {

    private List<StickerModel> list;
    private StickerPagerAdapter.OnStickerSelect listener;
    Context context;

    public StickersAdapter(Context context, List<StickerModel> list, StickerPagerAdapter.OnStickerSelect listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StickerModel model = list.get(position);
        try {
            BindingAdaptet.setImageUrl(holder.imgSticker,model.item_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.showLoader(context);
                listener.sticker(model.item_url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSticker;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSticker = itemView.findViewById(R.id.imgSticker);

        }
    }

}