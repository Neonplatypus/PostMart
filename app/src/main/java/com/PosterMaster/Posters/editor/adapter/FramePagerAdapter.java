package com.PosterMaster.Posters.editor.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.PosterMaster.Posters.editor.Fragment.FrameFragment;
import com.PosterMaster.Posters.editor.Fragment.PersonalFrameFragment;
import com.PosterMaster.Posters.model.FrameCategoryModel;
import com.PosterMaster.Posters.model.FrameModel;

import java.util.ArrayList;
import java.util.List;

public class FramePagerAdapter extends FragmentPagerAdapter {

    List<FrameCategoryModel> list = new ArrayList<>();
    OnFrameSelect listner;
    public interface OnFrameSelect{
        void onSelect(FrameModel frameModel);
        void onPersonalSelect(String framepath);
    }

    public FramePagerAdapter(@NonNull FragmentManager fm, List<FrameCategoryModel> list, OnFrameSelect listener) {
        super(fm);
        this.list = list;
        this.listner = listener;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new PersonalFrameFragment(new OnFrameSelect() {
                @Override
                public void onSelect(FrameModel frameModel) {
                    listner.onSelect(frameModel);
                }
                @Override
                public void onPersonalSelect(String frameModel) {
                    listner.onPersonalSelect(frameModel);
                }
            });
        }else{
            return new FrameFragment(list.get(position).getFrames(), new OnFrameSelect() {
                @Override
                public void onSelect(FrameModel frameModel) {
                    listner.onSelect(frameModel);
                }

                @Override
                public void onPersonalSelect(String frameModel) {
                    listner.onPersonalSelect(frameModel);
                }
            });
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
