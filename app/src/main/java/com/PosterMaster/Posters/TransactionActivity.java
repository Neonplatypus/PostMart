package com.PosterMaster.Posters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.PosterMaster.Posters.adapter.TransactionAdapter;
import com.PosterMaster.Posters.classes.Functions;
import com.PosterMaster.Posters.databinding.ActivityTransactionBinding;
import com.PosterMaster.Posters.databinding.FragmentWithdrawBinding;
import com.PosterMaster.Posters.interfaces.AdapterClickListener;
import com.PosterMaster.Posters.responses.UserResponse;
import com.PosterMaster.Posters.viewmodel.UserViewModel;

public class TransactionActivity extends AppCompatActivity {


    ActivityTransactionBinding binding;
    Activity context;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        binding.backBtn.setOnClickListener(view -> {
            finish();
        });
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        getData();
    }

    private void getData() {
        viewModel.getTransactionRequest(Functions.getUID(context)).observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if (userResponse != null){
                    if (userResponse.getTransactionlist().size() > 0){
                        binding.userList.setAdapter(new TransactionAdapter(context, userResponse.getTransactionlist(), new AdapterClickListener() {
                            @Override
                            public void onItemClick(View view, int pos, Object object) {

                            }
                        }));
                    }else{
                        binding.noDataLayout.setVisibility(View.VISIBLE);
                    }
                }else {
                    binding.noDataLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}