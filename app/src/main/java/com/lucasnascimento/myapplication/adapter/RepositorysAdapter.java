package com.lucasnascimento.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.lucasnascimento.myapplication.models.skeleton.GithubService;

import java.util.List;

public class RepositorysAdapter extends PagedListAdapter<GithubService.Repositories,RepositorysAdapter.RepositorysHolder> {

    private List<GithubService.Repositories> lstRepositories;
    private Context context;

    public RepositorysAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
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
        GithubService.Repositories repo = getItem(position);
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

    private static DiffUtil.ItemCallback<GithubService.Repositories> DIFF_CALLBACK = new DiffUtil.ItemCallback<GithubService.Repositories>() {
        @Override
        public boolean areItemsTheSame(@NonNull GithubService.Repositories oldItem, @NonNull GithubService.Repositories newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull GithubService.Repositories oldItem, @NonNull GithubService.Repositories newItem) {
            return oldItem.equals(newItem);
        }
    };

    class RepositorysHolder extends RecyclerView.ViewHolder{
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
        }
    }
}
