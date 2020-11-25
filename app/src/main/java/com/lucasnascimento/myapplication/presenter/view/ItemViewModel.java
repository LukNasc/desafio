package com.lucasnascimento.myapplication.presenter.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.models.skeleton.GithubService;
import com.lucasnascimento.myapplication.presenter.pagination.ItemDataSourceFactory;

public class ItemViewModel extends ViewModel {
    public LiveData<PagedList<GithubService.Repositories>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, GithubService.Repositories>> liveDataSource;
    public  MainView view;
    //constructor
    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(view);

        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MainPresenter.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
}
