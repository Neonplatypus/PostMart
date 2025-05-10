package com.PosterMaster.Posters.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.PosterMaster.Posters.model.SettingModel;
import com.PosterMaster.Posters.respository.SettingRespository;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    private SettingRespository respository;

    public SettingsViewModel(){
        respository = new SettingRespository();
    }

    public LiveData<List<SettingModel>> getAllSetting(){
        return respository.getData();
    }

}
