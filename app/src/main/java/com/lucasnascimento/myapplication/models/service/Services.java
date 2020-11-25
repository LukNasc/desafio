package com.lucasnascimento.myapplication.models.service;

import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.models.skeleton.GithubService;
import com.lucasnascimento.myapplication.presenter.service.ApiService;
import com.lucasnascimento.myapplication.presenter.service.RetrofitConfig;

import retrofit2.Call;

public class Services {
    public static Call<GithubService> getGithubRepository(int page,int pageSize, MainView view){
        ApiService apiService = new RetrofitConfig(view).getClient();
        return  apiService.call(page, pageSize);
    }
}
