package com.lucasnascimento.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.lucasnascimento.myapplication.R;
import com.lucasnascimento.myapplication.adapter.PullRequestsAdapter;
import com.lucasnascimento.myapplication.interfaces.PRView;
import com.lucasnascimento.myapplication.models.skeleton.PullRequests;
import com.lucasnascimento.myapplication.presenter.view.PRPresenter;

import java.util.List;

public class PRActivity extends AppCompatActivity implements PRView, PRPresenter.PRCallback {

    private PRPresenter presenter;
    private RecyclerView rvPullRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_r);
        Bundle bundle = getIntent().getExtras();
        String repository = bundle.getString("repository");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("PR | "+repository);

        rvPullRequests = findViewById(R.id.rvPullRequests);

        presenter = new PRPresenter(this);
        presenter.callService(repository, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openBrowser(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    @Override
    public void run(List<PullRequests> lstPullRequests) {
        PullRequestsAdapter adapter = new PullRequestsAdapter(lstPullRequests, this, this);
        rvPullRequests.setLayoutManager(new LinearLayoutManager(this));
        rvPullRequests.setHasFixedSize(true);
        rvPullRequests.setAdapter(adapter);
    }
}