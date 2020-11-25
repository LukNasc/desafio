package com.lucasnascimento.myapplication.presenter.view;

import com.lucasnascimento.myapplication.interfaces.PRView;
import com.lucasnascimento.myapplication.models.service.Services;
import com.lucasnascimento.myapplication.models.skeleton.PullRequests;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PRPresenter implements com.lucasnascimento.myapplication.interfaces.PRPresenter {
    private PRView view;

    public PRPresenter(PRView view) {
        this.view = view;
    }

    @Override
    public void callService(String repository, PRCallback callback) {
        Services.getPullRequestsRepositories(repository, view).enqueue(new Callback<List<PullRequests>>() {
            @Override
            public void onResponse(Call<List<PullRequests>> call, Response<List<PullRequests>> response) {
                if(response.isSuccessful()){
                    callback.run(response.body());
                }else{
                    view.showMessage("Não foi possível encontrar pull requests para esse repositório");
                }
            }

            @Override
            public void onFailure(Call<List<PullRequests>> call, Throwable t) {
                t.printStackTrace();
                view.showMessage(t.getMessage());
            }
        });
    }


    public interface PRCallback {
        void run(List<PullRequests>  lstPullRequests);
    }
}
