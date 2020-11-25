package com.lucasnascimento.myapplication.models.service;

import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.interfaces.PRView;
import com.lucasnascimento.myapplication.models.skeleton.GithubRepositories;
import com.lucasnascimento.myapplication.models.skeleton.PullRequests;
import com.lucasnascimento.myapplication.presenter.service.ApiService;
import com.lucasnascimento.myapplication.presenter.service.RetrofitConfig;

import java.util.List;

import retrofit2.Call;

public class Services {
    public static Call<GithubRepositories> getGithubRepository(int page, int pageSize, MainView view){
        ApiService apiService = new RetrofitConfig(view).getClient();
        return  apiService.callRepositories(page, pageSize);
    }

    public static Call<List<PullRequests>> getPullRequestsRepositories(String repository, PRView view){
        ApiService apiService = new RetrofitConfig(view).getClient();
        String repo = repository.split("/")[1];
        String user = repository.split("/")[0];
        return apiService.callPullRequests(user, repo);
    }
}
