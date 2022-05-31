package com.mustafa.smallstore.view.main.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    //region Variables
    List<ProductEntity> productEntityList;
    List<byte[]> picturesList;
    PictureAdapter pictureAdapter;
    Context context;
    ProductOnClickListener productOnClickListener;
    //endregion

    //region Constructor
    public ProductAdapter(List<ProductEntity> productEntityList, ProductOnClickListener productOnClickListener) {
        this.productEntityList = productEntityList;
        picturesList = new ArrayList<>();
        this.productOnClickListener = productOnClickListener;
    }
    //endregion

    //region Adapter

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_product, parent, false);
        context = parent.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if (productEntityList.get(position).getImage1() != null)
            picturesList.add(productEntityList.get(position).getImage1());
        if (productEntityList.get(position).getImage2() != null)
            picturesList.add(productEntityList.get(position).getImage2());
        if (productEntityList.get(position).getImage3() != null)
            picturesList.add(productEntityList.get(position).getImage3());

        pictureAdapter = new PictureAdapter(picturesList);
        if (productEntityList.get(position).isNew() == true) {
            holder.itemRecyclerViewProductTextViewNew.setText("New");

        } else {
            holder.itemRecyclerViewProductTextViewNew.setText("Old");
        }

        holder.itemRecyclerViewProductTextViewName.setText(productEntityList.get(position).getName());
        holder.itemRecyclerViewProductTextViewPriceValue.setText(String.valueOf(productEntityList.get(position).getPrice()));
        holder.itemRecyclerViewProductTextViewQuantityValue.setText(String.valueOf(productEntityList.get(position).getQuantity()));
        holder.itemRecyclerViewProductTextViewMadeInValue.setText(productEntityList.get(position).getMadeIn());
        holder.itemRecyclerViewProductTextViewCategoryValue.setText(productEntityList.get(position).getCategoryName());
//        holder.itemRecyclerViewProductRecyclerViewImage.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        holder.itemRecyclerViewProductRecyclerViewImage.setAdapter(pictureAdapter);
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }
    //endregion

    //for Delete Swipe Position
    public ProductEntity getProductPosition(int position) {
        return productEntityList.get(position);
    }

    //Region Interface
    public interface ProductOnClickListener {
        void onProductItemLinearParentClickListener(ProductEntity productEntity);
    }
    //endregion

    //region Methods
    public void refreshList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
        notifyDataSetChanged();
    }
    //endregion


    //region View holder
    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //region Components
        RecyclerView itemRecyclerViewProductRecyclerViewImage;
        TextView itemRecyclerViewProductTextViewName;
        TextView itemRecyclerViewProductTextViewNew;
        TextView itemRecyclerViewProductTextViewPrice;
        TextView itemRecyclerViewProductTextViewPriceValue;
        TextView itemRecyclerViewProductTextViewQuantity;
        TextView itemRecyclerViewProductTextViewQuantityValue;
        TextView itemRecyclerViewProductTextViewMadeIn;
        TextView itemRecyclerViewProductTextViewMadeInValue;
        TextView itemRecyclerViewProductTextViewCategory;
        TextView itemRecyclerViewProductTextViewCategoryValue;
        LinearLayout itemToRecyclerViewProductLinearLayoutParent;
        //endregion

        //region Constructor
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerViewProductRecyclerViewImage = itemView.findViewById(R.id.item_to_recycler_view_product_recycler_view_image);
            itemRecyclerViewProductTextViewNew = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_new_or_old);
            itemRecyclerViewProductTextViewName = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_name);
            itemRecyclerViewProductTextViewPrice = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_price);
            itemRecyclerViewProductTextViewPriceValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_price_value);
            itemRecyclerViewProductTextViewQuantity = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_quantity);
            itemRecyclerViewProductTextViewQuantityValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_quantity_value);
            itemRecyclerViewProductTextViewMadeIn = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_made_in);
            itemRecyclerViewProductTextViewMadeInValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_made_in_value);
            itemRecyclerViewProductTextViewCategory = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_category);
            itemRecyclerViewProductTextViewCategoryValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_category_value);
            itemToRecyclerViewProductLinearLayoutParent = itemView.findViewById(R.id.item_to_recycler_view_product_linear_layout_parent);
            itemToRecyclerViewProductLinearLayoutParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            productOnClickListener.onProductItemLinearParentClickListener(productEntityList.get(getAdapterPosition()));
        }
        //endregion

    }
    //endregion
}
