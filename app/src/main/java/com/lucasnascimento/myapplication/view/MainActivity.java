package com.lucasnascimento.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lucasnascimento.myapplication.R;
import com.lucasnascimento.myapplication.adapter.RepositoriesAdapter;
import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.presenter.view.ItemViewModel;
import com.lucasnascimento.myapplication.presenter.view.MainPresenter;

public class MainActivity extends AppCompatActivity implements  MainView {
   private MainPresenter presenter;
   private RepositoriesAdapter repositoriesAdapter;
   private RecyclerView rvRepositories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias
        rvRepositories = findViewById(R.id.rvRepositorys);
        rvRepositories.setLayoutManager(new LinearLayoutManager(this));
        rvRepositories.setHasFixedSize(true);

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        repositoriesAdapter = new RepositoriesAdapter(this);

        itemViewModel.view = this;
        itemViewModel.itemPagedList.observe(this, repositories -> repositoriesAdapter.submitList(repositories));

        rvRepositories.setAdapter(repositoriesAdapter);

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToPRActivity(String repository) {
        Intent intent = new Intent(this, PRActivity.class);
        intent.putExtra("repository", repository);
        startActivity(intent);
    }
}