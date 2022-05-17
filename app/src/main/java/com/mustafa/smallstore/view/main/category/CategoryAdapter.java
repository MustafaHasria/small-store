package com.mustafa.smallstore.view.main.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.model.entity.CategoryEntity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    //region Variables
    List<CategoryEntity> categoryEntityList;
    CategoryOnClickListener categoryOnClickListener;
    //endregion

    //region Constructor
    public CategoryAdapter(List<CategoryEntity> categoryEntityList, CategoryOnClickListener categoryOnClickListener) {
        this.categoryEntityList = categoryEntityList;
        this.categoryOnClickListener = categoryOnClickListener;
    }
    //endregion

    //region Adapter
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view_category, viewGroup, false);
        return new CategoryViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryEntity categoryEntity = categoryEntityList.get(position);
        holder.itemRecyclerViewCategoryTextViewName.setText(categoryEntity.getName());
    }

    @Override
    public int getItemCount() {
        return categoryEntityList.size();
    }

    public void refreshList(List<CategoryEntity> accountEntityList) {
        this.categoryEntityList = accountEntityList;
        notifyDataSetChanged();

    }

    public CategoryEntity getCategoryPosition(int position) {
        return categoryEntityList.get(position);
    }

    //endregion

    //region Interface
    public interface CategoryOnClickListener {
        void onItemRecyclerViewCategoryCardViewMainContainerClickListener(CategoryEntity categoryEntity);
    }
    //endregion

    //region View holder
    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout itemRecyclerViewCategoryCardViewMainContainer;
        TextView itemRecyclerViewCategoryTextViewName;
        //View itemToRecyclerViewView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerViewCategoryCardViewMainContainer = itemView.findViewById(R.id.item_recycler_view_Linear_layout_view_main_container);
            itemRecyclerViewCategoryTextViewName = itemView.findViewById(R.id.item_recycler_view_category_text_view_name);

        }

        @Override
        public void onClick(View view) {
            categoryOnClickListener.onItemRecyclerViewCategoryCardViewMainContainerClickListener(categoryEntityList.get(getAdapterPosition()));
        }
    }
    //endregion


}
