package com.mustafa.smallstore.view.main.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    //endregion

    //region Constructor
    public ProductAdapter(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
        picturesList = new ArrayList<>();
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
        holder.itemRecyclerViewProductTextViewName.setText(productEntityList.get(position).getName());
        holder.itemRecyclerViewProductTextViewPrice.setText(String.valueOf(productEntityList.get(position).getPrice()));
        holder.itemRecyclerViewProductTextViewMadeIn.setText(productEntityList.get(position).getMadeIn());
//        holder.itemRecyclerViewProductRecyclerViewImage.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        holder.itemRecyclerViewProductRecyclerViewImage.setAdapter(pictureAdapter);
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }
    //endregion

    //region Methods
    public void refreshList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
        notifyDataSetChanged();
    }

    //endregion

    //region View holder
    public class ProductViewHolder extends RecyclerView.ViewHolder {

        //region Components
        RecyclerView itemRecyclerViewProductRecyclerViewImage;
        TextView itemRecyclerViewProductTextViewName;
        TextView itemRecyclerViewProductTextViewPrice;
        TextView itemRecyclerViewProductTextViewMadeIn;
        //endregion

        //region Constructor
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerViewProductRecyclerViewImage = itemView.findViewById(R.id.item_recycler_view_product_recycler_view_image);
            itemRecyclerViewProductTextViewName = itemView.findViewById(R.id.item_recycler_view_product_text_view_name);
            itemRecyclerViewProductTextViewPrice = itemView.findViewById(R.id.item_recycler_view_product_text_view_price);
            itemRecyclerViewProductTextViewMadeIn = itemView.findViewById(R.id.item_recycler_view_product_text_view_made_in);
        }
        //endregion
    }
    //endregion
}
