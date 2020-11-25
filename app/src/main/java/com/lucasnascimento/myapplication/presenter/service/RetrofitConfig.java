package com.lucasnascimento.myapplication.presenter.service;

import android.util.Log;

import com.lucasnascimento.myapplication.interfaces.MainView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final String TAG = "retrofitConfigError";
    Retrofit retrofit;

    public RetrofitConfig(MainView view){
        try{
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
            view.showMessage(e.getMessage());
        }
    }

    public ApiService getClient(){
        return this.retrofit.create(ApiService.class);
    }

}
