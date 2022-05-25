package com.mustafa.smallstore.view.main.product;

import android.content.Context;
import android.text.format.DateFormat;
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
import java.util.Date;
import java.util.List;

public class ProductOfferAdapter extends RecyclerView.Adapter<ProductOfferAdapter.ProductOfferViewHolder> {

    public String specificDate;
    //region Variables
    List<ProductEntity> productEntityList;
    List<byte[]> picturesList;
    PictureAdapter pictureAdapter;
    Context context;
    ProductEntity productEntity;
    ProductOfferOnClickListener productOfferOnClickListener;
    //endregion

    //region Constructor
    public ProductOfferAdapter(List<ProductEntity> productEntityList, ProductOfferOnClickListener productOfferOnClickListener) {
        this.productEntityList = productEntityList;
        picturesList = new ArrayList<>();
        this.productOfferOnClickListener = productOfferOnClickListener;
    }
    //endregion

    @NonNull
    @Override
    public ProductOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_product_offer, parent, false);
        context = parent.getContext();
        return new ProductOfferAdapter.ProductOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOfferViewHolder holder, int position) {
        //for date offer
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM D , YYYY", date.getDate());
        specificDate = sequence.toString();

        if (productEntityList.get(position).getImage1() != null)
            picturesList.add(productEntityList.get(position).getImage1());
        if (productEntityList.get(position).getImage2() != null)
            picturesList.add(productEntityList.get(position).getImage2());
        if (productEntityList.get(position).getImage3() != null)
            picturesList.add(productEntityList.get(position).getImage3());

        pictureAdapter = new PictureAdapter(picturesList);

        if (productEntityList.get(position).isOffered() == true && productEntityList.get(position).getExpireDateOffer() != specificDate) {
            holder.itemRecyclerViewProductOfferTextViewOffer.setText("Offer");
        }
        if (productEntityList.get(position).isOffered() == true && productEntityList.get(position).getExpireDateOffer() == specificDate) {
            holder.itemRecyclerViewProductOfferTextViewOffer.setText("End Offer");
        }

        holder.itemRecyclerViewProductOfferTextViewName.setText(productEntityList.get(position).getName());
        holder.itemRecyclerViewProductOfferTextViewOffer_price_value.setText(String.valueOf(productEntityList.get(position).getOfferCost()));
        holder.itemRecyclerViewProductOfferTextViewQuantityValue.setText(String.valueOf(productEntityList.get(position).getQuantity()));
        holder.itemRecyclerViewProductOfferTextViewMadeInValue.setText(productEntityList.get(position).getMadeIn());
        holder.itemRecyclerViewProductOfferTextViewCategoryValue.setText(productEntityList.get(position).getCategoryName());
        holder.itemRecyclerViewProductOfferTextViewExpireDateOfferValue.setText(productEntityList.get(position).getExpireDateOffer());
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }

    //region Methods
    public void refreshList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
        notifyDataSetChanged();
    }
    //endregion

    //region Interface
    public interface ProductOfferOnClickListener {
        void onProductOfferItemLinearParentClickListener(ProductEntity productEntity);
    }
    //endregion

    //region View Holder
    public class ProductOfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //region Components
        RecyclerView itemRecyclerViewProductOfferRecyclerViewImage;
        TextView itemRecyclerViewProductOfferTextViewName;
        TextView itemRecyclerViewProductOfferTextViewOffer;
        TextView itemRecyclerViewProductOfferTextViewOfferPrice;
        TextView itemRecyclerViewProductOfferTextViewOffer_price_value;
        TextView itemRecyclerViewProductOfferTextViewQuantity;
        TextView itemRecyclerViewProductOfferTextViewQuantityValue;
        TextView itemRecyclerViewProductOfferTextViewMadeIn;
        TextView itemRecyclerViewProductOfferTextViewMadeInValue;
        TextView itemRecyclerViewProductOfferTextViewCategory;
        TextView itemRecyclerViewProductOfferTextViewCategoryValue;
        TextView itemRecyclerViewProductOfferTextViewExpireDateOffer;
        TextView itemRecyclerViewProductOfferTextViewExpireDateOfferValue;
        LinearLayout itemRecyclerViewProductOfferLinearlayoutParent;
        //endregion

        //region Constructor
        public ProductOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerViewProductOfferRecyclerViewImage = itemView.findViewById(R.id.item_to_recycler_view_product_recycler_view_image);
            itemRecyclerViewProductOfferTextViewOffer = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_new_or_old);
            itemRecyclerViewProductOfferTextViewName = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_name);
            itemRecyclerViewProductOfferTextViewOfferPrice = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_price);
            itemRecyclerViewProductOfferTextViewOffer_price_value = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_price_value);
            itemRecyclerViewProductOfferTextViewQuantity = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_quantity);
            itemRecyclerViewProductOfferTextViewQuantityValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_quantity_value);
            itemRecyclerViewProductOfferTextViewMadeIn = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_made_in);
            itemRecyclerViewProductOfferTextViewMadeInValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_made_in_value);
            itemRecyclerViewProductOfferTextViewCategory = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_category);
            itemRecyclerViewProductOfferTextViewCategoryValue = itemView.findViewById(R.id.item_to_recycler_view_product_text_view_category_value);
            itemRecyclerViewProductOfferTextViewExpireDateOffer = itemView.findViewById(R.id.item_to_recycler_view_product_offer_text_view_expire_date);
            itemRecyclerViewProductOfferTextViewExpireDateOfferValue = itemView.findViewById(R.id.item_to_recycler_view_product_offer_text_view_expire_date_value);
            itemRecyclerViewProductOfferLinearlayoutParent = itemView.findViewById(R.id.item_to_recycler_view_product_offer_linear_layout_parent);
            itemRecyclerViewProductOfferLinearlayoutParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            productOfferOnClickListener.onProductOfferItemLinearParentClickListener(productEntityList.get(getAdapterPosition()));
        }
        //endregion
    }
    //endregion
}
