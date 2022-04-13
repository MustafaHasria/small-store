package com.mustafa.smallstore.view.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.model.entity.AccountEntity;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {


    //region Variables
    List<AccountEntity> accountEntityList;
    //endregion

    //region Constructor
    public AccountAdapter(List<AccountEntity> accountEntityList) {
        this.accountEntityList = accountEntityList;
    }
    //endregion

    //region Adapter
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view_account, viewGroup, false);
        return new AccountViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.itemToRecyclerViewTextViewTitle.setText(accountEntityList.get(position).getName());
        holder.itemToRecyclerViewTextViewContent.setText("Role: " + (accountEntityList.get(position).getRole() == 1 ? "Normal User" : "Admin User") + "     Password: " + accountEntityList.get(position).getPassword());
        //todo set image view

    }

    @Override
    public int getItemCount() {
        return accountEntityList.size();
    }

    public void refreshList(List<AccountEntity> accountEntityList) {
        this.accountEntityList = accountEntityList;
        notifyDataSetChanged();

    }
    //endregion

    //region View holder
    public class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView itemToRecyclerViewTextViewTitle;
        TextView itemToRecyclerViewTextViewContent;
        ImageView itemToRecyclerViewImageViewUser;
        RelativeLayout itemToRecyclerViewRelativeLayout;
        //View itemToRecyclerViewView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            itemToRecyclerViewTextViewTitle = itemView.findViewById(R.id.item_to_recycler_view_text_view_title);
            itemToRecyclerViewTextViewContent = itemView.findViewById(R.id.item_to_recycler_view_text_view_content);
            itemToRecyclerViewImageViewUser = itemView.findViewById(R.id.item_to_recycler_view_image_view_user);
            itemToRecyclerViewRelativeLayout = itemView.findViewById(R.id.item_to_recycler_view_relative_layout);
            // itemToRecyclerViewView = itemView.findViewById(R.id.item_to_recycler_view_view);
        }
    }
    //endregion

}
