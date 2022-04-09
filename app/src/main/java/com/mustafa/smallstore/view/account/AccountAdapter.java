package com.mustafa.smallstore.view.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

    //region Adapter methods
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_account, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.recyclerViewAccountItemTextViewName.setText(accountEntityList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return accountEntityList.size();
    }

    public void updateList(List<AccountEntity> accountEntityList) {
        this.accountEntityList = accountEntityList;
        //مشان اعمل رفرش للداتا
        notifyDataSetChanged();
    }

    //endregion

    //region View holder
    class AccountViewHolder extends RecyclerView.ViewHolder {

        //region Components
        CardView recyclerViewAccountItemCardViewMainContainer;
        TextView recyclerViewAccountItemTextViewName;
        //endregion

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewAccountItemCardViewMainContainer = itemView.findViewById(R.id.recycler_view_account_item_card_view_main_container);
            recyclerViewAccountItemTextViewName = itemView.findViewById(R.id.recycler_view_account_item_text_view_name);
        }
    }
    //endregion
}
