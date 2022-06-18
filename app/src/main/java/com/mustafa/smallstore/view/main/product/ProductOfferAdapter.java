package com.mustafa.smallstore.view.main.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.model.entity.ProductEntity;

import java.util.List;

public class ProductOfferAdapter extends RecyclerView.Adapter<ProductOfferAdapter.ProductOfferViewHolder> {


    public String specificDate;
    //region Variables
    List<ProductEntity> productEntityList;
    Context context;
    ProductEntity productEntity;
    ProductOfferOnClickListener productOfferOnClickListener;
    // List<byte[]> picturesList;
    // PictureAdapter pictureAdapter;
    //endregion

    //region Constructor
    public ProductOfferAdapter(List<ProductEntity> productEntityList, ProductOfferOnClickListener productOfferOnClickListener) {
        this.productEntityList = productEntityList;
        //picturesList = new ArrayList<>();
        this.productOfferOnClickListener = productOfferOnClickListener;
    }
    //endregion

    //region Adapter
    @NonNull
    @Override
    public ProductOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_product_offer, parent, false);
        context = parent.getContext();
        return new ProductOfferAdapter.ProductOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOfferViewHolder holder, int position) {
        ProductEntity productEntity;
        productEntity = productEntityList.get(position);
        holder.itemRecyclerViewProductOfferTextViewName.setText(productEntity.getName());
        holder.itemRecyclerViewProductOfferTextViewOffer_price_value.setText(String.valueOf(productEntity.getOfferCost()));
        holder.itemRecyclerViewProductOfferTextViewQuantityValue.setText(String.valueOf(productEntity.getQuantity()));
        holder.itemRecyclerViewProductOfferTextViewMadeInValue.setText(productEntity.getMadeIn());
        holder.itemRecyclerViewProductOfferTextViewCategoryValue.setText(productEntity.getCategoryName());
        holder.itemRecyclerViewProductOfferTextViewExpireDateOfferValue.setText(productEntity.getExpireDateOffer());

    }

//        if (productEntity.getImage1() != null)
//            picturesList.add(productEntity.getImage1());
//        if (productEntity.getImage2() != null)
//            picturesList.add(productEntity.getImage2());
//        if (productEntity.getImage3() != null)
//            picturesList.add(productEntity.getImage3());
//
//        pictureAdapter = new PictureAdapter(picturesList);
//        int month;
//        if (!productEntity.getExpireDateOffer().equals(""))
//            month = Integer.parseInt(productEntity.getExpireDateOffer().substring(3, 2));
//        if (productEntity.isOffered() && productEntity.getExpireDateOffer() > specificDate) {
//            holder.itemRecyclerViewProductOfferTextViewOffer.setText("Offer");
//        }
//        if (productEntity.isOffered() && productEntity.getExpireDateOffer() == specificDate) {
//            holder.itemRecyclerViewProductOfferTextViewOffer.setText("End Offer");
//        }


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

    //region Delete
    public ProductEntity getProductPosition(int position) {
        return productEntityList.get(position);
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
        CardView itemRecyclerViewCardViewMainContainer;
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
            itemRecyclerViewCardViewMainContainer = itemView.findViewById(R.id.item_recycler_view_card_view_main_container);
            itemRecyclerViewProductOfferRecyclerViewImage = itemView.findViewById(R.id.item_recycler_view_product_offer_recycler_view_image);
            itemRecyclerViewProductOfferTextViewOffer = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_new_or_old);
            itemRecyclerViewProductOfferTextViewName = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_name);
            itemRecyclerViewProductOfferTextViewOfferPrice = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_price);
            itemRecyclerViewProductOfferTextViewOffer_price_value = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_price_value);
            itemRecyclerViewProductOfferTextViewQuantity = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_quantity);
            itemRecyclerViewProductOfferTextViewQuantityValue = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_quantity_value);
            itemRecyclerViewProductOfferTextViewMadeIn = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_made_in);
            itemRecyclerViewProductOfferTextViewMadeInValue = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_made_in_value);
            itemRecyclerViewProductOfferTextViewCategory = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_category);
            itemRecyclerViewProductOfferTextViewCategoryValue = itemView.findViewById(R.id.item_recycler_view_product_offer_text_view_category_value);
            itemRecyclerViewProductOfferTextViewExpireDateOffer = itemView.findViewById(R.id.item_recycler_view_product_offer_offer_text_view_expire_date);
            itemRecyclerViewProductOfferTextViewExpireDateOfferValue = itemView.findViewById(R.id.item_recycler_view_product_offer_offer_text_view_expire_date_value);
            itemRecyclerViewProductOfferLinearlayoutParent = itemView.findViewById(R.id.item_recycler_view_product_offer_offer_linear_layout_parent);
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

