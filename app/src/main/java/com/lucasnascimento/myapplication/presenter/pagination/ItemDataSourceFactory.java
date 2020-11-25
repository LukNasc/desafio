package com.lucasnascimento.myapplication.presenter.pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.models.skeleton.GithubRepositories;
import com.lucasnascimento.myapplication.presenter.view.MainPresenter;

public class ItemDataSourceFactory extends DataSource.Factory {
    private MainView view;
    private MutableLiveData<PageKeyedDataSource<Integer, GithubRepositories.Repositories>> itemLiveDataSource = new MutableLiveData<>();

    public ItemDataSourceFactory(MainView view) {
        this.view = view;
    }

    @NonNull
    @Override
    public DataSource<Integer, GithubRepositories.Repositories> create() {

        MainPresenter presenter = new MainPresenter(view);

        itemLiveDataSource.postValue(presenter);

        return presenter;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, GithubRepositories.Repositories>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
