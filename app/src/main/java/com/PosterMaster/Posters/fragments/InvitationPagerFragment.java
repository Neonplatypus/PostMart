package com.PosterMaster.Posters.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.PosterMaster.Posters.R;
import com.PosterMaster.Posters.adapter.InvitationAdapter;
import com.PosterMaster.Posters.adapter.InvitationPagerAdapter;
import com.PosterMaster.Posters.databinding.FragmentInvitationPagerBinding;
import com.PosterMaster.Posters.model.InvitationModel;

import java.util.ArrayList;
import java.util.List;

public class InvitationPagerFragment extends Fragment {


    List<InvitationModel> invitationModelList = new ArrayList<>();
    InvitationPagerAdapter.OnCardSelect onCardSelect;
    FragmentInvitationPagerBinding binding;

    public InvitationPagerFragment(List<InvitationModel> invitationcards, InvitationPagerAdapter.OnCardSelect onCardSelect) {
        this.invitationModelList = invitationcards;
        this.onCardSelect = onCardSelect;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInvitationPagerBinding.inflate(getLayoutInflater());

        binding.recyclerView.setAdapter(new InvitationAdapter(getContext(), invitationModelList, new InvitationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, InvitationModel postsModels, int pos) {
                onCardSelect.onSelect(postsModels);
            }
        },2, getResources().getDimension(R.dimen._2sdp)));

        if (!(invitationModelList.size() > 0)){
            binding.noDataLayout.setVisibility(View.VISIBLE);
        }
        return binding.getRoot();
    }
}