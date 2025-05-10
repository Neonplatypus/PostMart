package com.PosterMaster.Posters.fragments;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.PosterMaster.Posters.adapter.LogosPagerAdapter;
import com.PosterMaster.Posters.databinding.FragmentLogosBinding;
import com.PosterMaster.Posters.responses.FrameResponse;
import com.PosterMaster.Posters.viewmodel.FrameViewModel;


public class LogosFragment extends BottomSheetDialogFragment {

    LogosPagerAdapter.OnLogoSelect listner;
    String name;

    public LogosFragment(String name,LogosPagerAdapter.OnLogoSelect listner) {
        this.listner = listner;
        this.name = name;
    }

    FragmentLogosBinding binding;
    FrameViewModel frameViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLogosBinding.inflate(getLayoutInflater());

        frameViewModel = new ViewModelProvider(this).get(FrameViewModel.class);
        frameViewModel.getLogos().observe(getViewLifecycleOwner(), new Observer<FrameResponse>() {
            @Override
            public void onChanged(FrameResponse frameResponse) {
                if (frameResponse != null){
                    if (frameResponse.logoscategory != null && frameResponse.logoscategory.size() > 0){
                        binding.stickerViewpager.setAdapter(new LogosPagerAdapter(getChildFragmentManager(), frameResponse.logoscategory, new LogosPagerAdapter.OnLogoSelect() {
                            @Override
                            public void sticker(String path) {
                                listner.sticker(path);
                                dismiss();
                            }
                        },name));
                        binding.stickerTabLayout.setupWithViewPager(binding.stickerViewpager);
                    }else {
                        binding.noDataLayout.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}