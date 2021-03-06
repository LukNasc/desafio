package com.lucasnascimento.myapplication.presenter.view;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.models.service.Services;
import com.lucasnascimento.myapplication.models.skeleton.GithubRepositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends PageKeyedDataSource<Integer, GithubRepositories.Repositories> implements com.lucasnascimento.myapplication.interfaces.MainPresenter {
    MainView view;
    GithubRepositories model;
    private static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 20;

    public MainPresenter(MainView view){
        this.model = new GithubRepositories();
        this.view = view;
    }

    @Override
    public void callService(int page, RepositoriesCallback callback) {
        Services.getGithubRepository(page, PAGE_SIZE, view).enqueue(new Callback<GithubRepositories>() {
            @Override
            public void onResponse(Call<GithubRepositories> call, Response<GithubRepositories> response) {
                callback.run(response.body());
            }

            @Override
            public void onFailure(Call<GithubRepositories> call, Throwable t) {
                t.printStackTrace();
                view.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, GithubRepositories.Repositories> callback) {
        callService(FIRST_PAGE, response -> {
            callback.onResult(response.getItems(), null, FIRST_PAGE + 1);
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GithubRepositories.Repositories> callback) {
        callService(params.key, response -> {
            Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
            callback.onResult(response.getItems(), adjacentKey);

        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GithubRepositories.Repositories> callback) {
        callService(params.key, response -> {
            Integer key = params.key * response.getItems().size() < response.getTotal_count() ? params.key + 1 : null;
            callback.onResult(response.getItems(), key);
        });
    }

    public interface RepositoriesCallback {
        void run(GithubRepositories response);
    }
}
