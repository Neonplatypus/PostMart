package com.PosterMaster.Posters.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.PosterMaster.Posters.responses.FrameResponse;
import com.PosterMaster.Posters.respository.FrameRespository;

public class FrameViewModel extends ViewModel {

    private FrameRespository respository;

    public FrameViewModel(){
        respository = new FrameRespository();
    }

    public LiveData<FrameResponse> getFrames(String ratio){
        return respository.getFrames(ratio);
    }

    public LiveData<FrameResponse> getFramesByType(String type,String ratio,boolean featured,String uid){
        return respository.getFramesByType(type,ratio,featured,uid);
    }

    public LiveData<FrameResponse> getStickers(String search){
        return respository.getStickers(search);
    }

    public LiveData<FrameResponse> getLogos(){
        return respository.getLogos();
    }

    public LiveData<FrameResponse> getMusic(){
        return respository.getMusicByCategory();
    }

    public LiveData<FrameResponse> getBackgrounds(){
        return respository.getBackgrounds();
    }
}
