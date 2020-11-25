package com.lucasnascimento.myapplication.presenter.service;

import com.lucasnascimento.myapplication.models.skeleton.GithubRepositories;
import com.lucasnascimento.myapplication.models.skeleton.PullRequests;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/repositories?q=language:Java&sort=stars")
    Call<GithubRepositories> callRepositories(
            @Query("page") int page,
            @Query("per_page") int pageSize
    );

    @GET("repos/{user}/{repo}/pulls")
    Call<List<PullRequests>> callPullRequests(
            @Path("user") String user,
            @Path("repo") String repo
    );
}
