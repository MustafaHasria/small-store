package com.mustafa.smallstore.view.account;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.model.entity.AccountEntity;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {


    //region Variables
    List<AccountEntity> accountEntityList;
    AccountOnClickListener accountOnClickListener;
    //endregion

    //region Constructor
    public AccountAdapter(List<AccountEntity> accountEntityList, AccountOnClickListener accountOnClickListener) {
        this.accountEntityList = accountEntityList;
        this.accountOnClickListener = accountOnClickListener;
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
        AccountEntity accountEntity = accountEntityList.get(position);
        holder.itemToRecyclerViewTextViewTitle.setText(accountEntity.getName());
        holder.itemToRecyclerViewTextViewContent.setText("Role: " + (accountEntity.getRole() == 1 ? " User" : "Admin ") + "  " + "Password: " + accountEntityList.get(position).getPassword());
        if (accountEntity.getImage() != null)
            holder.itemToRecyclerViewImageViewUser.setImageBitmap(
                    BitmapFactory
                            .decodeByteArray(accountEntity.getImage(), 0, accountEntity.getImage().length)

            );
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

    //for Delete Swipe Position
    public AccountEntity getAccountPosition(int position) {
        return accountEntityList.get(position);
    }

    //Region Interface
    public interface AccountOnClickListener {
        void onAccountItemCardMainContainerClickListener(AccountEntity accountEntity);
    }
    //endregion

    //region View holder
    public class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemToRecyclerViewTextViewTitle;
        TextView itemToRecyclerViewTextViewContent;
        ImageView itemToRecyclerViewImageViewUser;
        RelativeLayout itemToRecyclerViewRelativeLayout;
        ConstraintLayout itemToRecyclerViewConstraintLayoutParent;
        //View itemToRecyclerViewView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            itemToRecyclerViewTextViewTitle = itemView.findViewById(R.id.item_to_recycler_view_text_view_name);
            itemToRecyclerViewTextViewContent = itemView.findViewById(R.id.item_to_recycler_view_text_view_password_and_role);
            itemToRecyclerViewImageViewUser = itemView.findViewById(R.id.item_to_recycler_view_image_user);
            itemToRecyclerViewRelativeLayout = itemView.findViewById(R.id.item_to_recycler_view_relative_layout);
            itemToRecyclerViewConstraintLayoutParent = itemView.findViewById(R.id.item_to_recycler_view_constraint_layout_parent);
            itemToRecyclerViewConstraintLayoutParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            accountOnClickListener.onAccountItemCardMainContainerClickListener(accountEntityList.get(getAdapterPosition()));
        }
    }
    //endregion


}
