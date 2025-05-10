package com.PosterMaster.Posters.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.PosterMaster.Posters.model.LanguageModel;
import com.PosterMaster.Posters.respository.LanguageRespository;

import java.util.List;

public class LanguageViewModel extends ViewModel {

    private LanguageRespository respository;

    public LanguageViewModel(){
        respository = new LanguageRespository();
    }

    public LiveData<List<LanguageModel>> getData(){
        return respository.getData();
    }

}
