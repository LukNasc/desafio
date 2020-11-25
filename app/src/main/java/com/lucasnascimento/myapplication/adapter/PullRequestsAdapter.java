package com.lucasnascimento.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lucasnascimento.myapplication.R;
import com.lucasnascimento.myapplication.interfaces.PRView;
import com.lucasnascimento.myapplication.models.skeleton.PullRequests;

import java.util.List;

public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.PRHolder> {

    private List<PullRequests> lstPullRequest;
    private Context context;
    private PRView view;

    public PullRequestsAdapter(List<PullRequests> lstPullRequest, Context context, PRView view) {
        this.lstPullRequest = lstPullRequest;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public PRHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_pullrequests, parent, false);
        return new PRHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PRHolder holder, int position) {
        PullRequests pr = lstPullRequest.get(position);

        holder.txtTitle.setText(pr.getTitle());
        holder.txtDescription.setText(pr.getBody());
        holder.txtUser.setText(pr.getUser().getLogin());
        holder.txtDate.setText(pr.getCreated_at());

        if(pr.getUser().getAvatar_url() != null){
            Glide.with(holder.itemView)
                    .load(pr.getUser().getAvatar_url())
                    .circleCrop()
                    .into(holder.ivProfile);
        }

        holder.itemView.setOnClickListener(view -> { this.view.openBrowser(pr.getHtml_url());});


    }

    @Override
    public int getItemCount() {
        return lstPullRequest.size();
    }

    class PRHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription, txtUser, txtDate;
        ImageView ivProfile;
        public PRHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtPRTitle);
            txtDescription = itemView.findViewById(R.id.txtPRDescription);
            txtUser = itemView.findViewById(R.id.txtPRUser);
            txtDate = itemView.findViewById(R.id.txtPRDate);
            ivProfile = itemView.findViewById(R.id.ivPRProfile);

        }

    }
}
