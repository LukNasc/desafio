package com.lucasnascimento.myapplication.presenter.service;

import com.lucasnascimento.myapplication.models.skeleton.GithubService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("repositories?q=language:Java&sort=stars")
    Call<GithubService> call(
            @Query("page") int page,
            @Query("per_page") int pageSize
    );
}
