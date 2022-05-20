package com.mustafa.smallstore.view.main.product;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.smallstore.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    //region Variables
    List<byte[]> picturesList;
    //endregion

    //region Constructor
    public PictureAdapter(List<byte[]> picturesList) {
        this.picturesList = picturesList;
    }
    //endregion

    //region Adapter

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_account, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {

        holder.itemRecyclerViewPictureImageView.setImageBitmap(
                BitmapFactory
                        .decodeByteArray(picturesList.get(position), 0, picturesList.get(position).length)

        );
    }

    @Override
    public int getItemCount() {
        return picturesList.size();
    }
    //endregion

    //region Methods
    public void refreshList(List<byte[]> picturesList) {
        this.picturesList = picturesList;
        notifyDataSetChanged();
    }
    //endregion

    //region View holder
    public class PictureViewHolder extends RecyclerView.ViewHolder {

        //region Components
        ImageView itemRecyclerViewPictureImageView;
        //endregion

        //region Constructor
        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerViewPictureImageView = itemView.findViewById(R.id.item_recycler_view_picture_image_view);
        }
        //endregion
    }
    //endregion
}
