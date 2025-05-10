package com.PosterMaster.Posters.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.PosterMaster.Posters.classes.Constants;
import com.PosterMaster.Posters.model.LanguageModel;
import com.PosterMaster.Posters.network.ApiClient;
import com.PosterMaster.Posters.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguageRespository {


    private ApiService apiService;
    public LanguageRespository(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<LanguageModel>> getData(){
        MutableLiveData<List<LanguageModel>> data = new MutableLiveData<>();
        apiService.getLanguages(Constants.API_KEY).enqueue(new Callback<List<LanguageModel>>() {
            @Override
            public void onResponse(Call<List<LanguageModel>> call, Response<List<LanguageModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<LanguageModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
