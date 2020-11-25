package com.lucasnascimento.myapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lucasnascimento.myapplication.R;
import com.lucasnascimento.myapplication.interfaces.MainView;
import com.lucasnascimento.myapplication.models.skeleton.GithubRepositories;

public class RepositoriesAdapter extends PagedListAdapter<GithubRepositories.Repositories, RepositoriesAdapter.RepositorysHolder> {

    private MainView view;

    public RepositoriesAdapter(MainView view) {
        super(DIFF_CALLBACK);
        this.view = view;
    }

    @NonNull
    @Override
    public RepositorysHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_repositorys, parent, false);
        return new RepositorysHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositorysHolder holder, int position) {
        GithubRepositories.Repositories repo = getItem(position);
        holder.txtRepoTitle.setText(repo.getName());
        holder.txtRepoDesc.setText(repo.getDescription());
        holder.txtStars.setText(repo.getStargazers_count()+" Stars");
        holder.txtForks.setText(repo.getForks_count()+" Forks");
        holder.txtUser.setText(repo.getOwner().getLogin());
        if(repo.getOwner().getAvatar_url() != null){
            Glide.with(holder.itemView)
                    .load(repo.getOwner().getAvatar_url())
                    .into(holder.ivProfile);
        }
    }

    private static DiffUtil.ItemCallback<GithubRepositories.Repositories> DIFF_CALLBACK = new DiffUtil.ItemCallback<GithubRepositories.Repositories>() {
        @Override
        public boolean areItemsTheSame(@NonNull GithubRepositories.Repositories oldItem, @NonNull GithubRepositories.Repositories newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull GithubRepositories.Repositories oldItem, @NonNull GithubRepositories.Repositories newItem) {
            return oldItem.equals(newItem);
        }
    };


    class RepositorysHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtRepoTitle, txtRepoDesc, txtStars, txtForks, txtUser;
        public ImageView ivProfile;
        public RepositorysHolder(@NonNull View itemView) {
            super(itemView);
            txtRepoTitle = itemView.findViewById(R.id.txtRepoTitle);
            txtRepoDesc = itemView.findViewById(R.id.txtDescription);
            txtStars = itemView.findViewById(R.id.txtStars);
            txtForks = itemView.findViewById(R.id.txtForks);
            txtUser = itemView.findViewById(R.id.txtUser);
            ivProfile = itemView.findViewById(R.id.ivProfile);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String repository = txtUser.getText().toString()+"/"+txtRepoTitle.getText().toString();
            view.goToPRActivity(repository);
        }
    }
}
