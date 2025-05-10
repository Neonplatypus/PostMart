package com.PosterMaster.Posters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.PosterMaster.Posters.R;
import com.PosterMaster.Posters.classes.Functions;
import com.PosterMaster.Posters.databinding.ItemUserListBinding;
import com.PosterMaster.Posters.databinding.ItemWithdrawHistriBinding;
import com.PosterMaster.Posters.interfaces.AdapterClickListener;
import com.PosterMaster.Posters.model.WithdrawModel;

import java.util.List;


public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.ViewHolder> {

    private List<WithdrawModel> list;
    private AdapterClickListener listener;
    Context context;

    public WithdrawAdapter(Context context, List<WithdrawModel> list, AdapterClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWithdrawHistriBinding binding = ItemWithdrawHistriBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WithdrawModel model = list.get(position);
        holder.binding.idTv.setText(""+position+1);
        holder.binding.amountTv.setText(context.getString(R.string.currency)+" "+model.getAmount());
        holder.binding.dateTv.setText(Functions.getFormatedDate(model.getCreated_at()));
        if (model.getStatus().equals("pending")){
            holder.binding.statusTv.setText(context.getString(R.string.pending));
            holder.binding.statusTv.getBackground().setTint(context.getColor(R.color.warning));
        }else if (model.getStatus().equals("cancel")){
            holder.binding.statusTv.setText(context.getString(R.string.cancel_));
            holder.binding.statusTv.getBackground().setTint(context.getColor(R.color.error));
        }else if (model.getStatus().equals("success")){
            holder.binding.statusTv.setText(context.getString(R.string.success));
            holder.binding.statusTv.getBackground().setTint(context.getColor(R.color.success));
        }

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

        ItemWithdrawHistriBinding binding;

        ViewHolder(@NonNull ItemWithdrawHistriBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }

}