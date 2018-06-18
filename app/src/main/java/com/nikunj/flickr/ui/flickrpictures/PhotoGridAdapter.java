package com.nikunj.flickr.ui.flickrpictures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nikunj.flickr.R;
import com.nikunj.flickr.data.model.Photo;
import com.nikunj.flickr.utils.Constant;
import com.nikunj.flickr.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikunj on 6/6/18.
 */
public class PhotoGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int selectedPos = -1;
    private final int TYPE_INFO = 0;
    private final int TYPE_DATA = 1;
    private final List<Photo> photoList;
    private Context context;
    private Callback callback;

    public PhotoGridAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public int getItemViewType(int position) {
        if (selectedPos != -1) {

            int infoPos = Utils.getInfoPosition(selectedPos);

            if (position == infoPos) {
                return TYPE_INFO;
            } else {
                return TYPE_DATA;
            }
        } else {
            return TYPE_DATA;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DATA) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
            return new DataViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_description, parent, false);
            return new InformationViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DataViewHolder) {
            DataViewHolder viewHolder = (DataViewHolder) holder;

            int listPosition = position;

            if(selectedPos!=-1) {
                int infoPos = Utils.getInfoPosition(selectedPos);

                if(infoPos<=position) {
                    listPosition = listPosition-1;
                }
                if(selectedPos == position) {
                    viewHolder.imageSelectionBorder.setVisibility(View.VISIBLE);
                    viewHolder.triangleView.setVisibility(View.VISIBLE);

                } else {
                    viewHolder.imageSelectionBorder.setVisibility(View.GONE);
                    viewHolder.triangleView.setVisibility(View.INVISIBLE);
                }
            } else {
                viewHolder.imageSelectionBorder.setVisibility(View.GONE);
                viewHolder.triangleView.setVisibility(View.INVISIBLE);
            }
            Photo photoItem = photoList.get(listPosition);

            String address = String.format(
                    Constant.PHOTO_ADDRESS_PATTERN,
                    photoItem.getFarm(),
                    photoItem.getServer(),
                    photoItem.getId(),
                    photoItem.getSecret()
            );

            Glide.with(context)
                    .load(address)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .downsample(DownsampleStrategy.AT_MOST)
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher_round))
                    .into(viewHolder.photoImageView);

            final int finalListPosition = listPosition;

            viewHolder.photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(callback != null) callback.onPhotoItemClick(finalListPosition);
                }
            });

        } else {
            InformationViewHolder viewHolder = (InformationViewHolder) holder;
            Photo photoItem = photoList.get(selectedPos);
            viewHolder.descriptionText.setText(photoItem.getTitle());
        }
        checkForLoadingNewPhotos(position);
    }

    @Override
    public int getItemCount() {
        if (selectedPos == -1) {
            return photoList.size();
        } else {
            return photoList.size() + 1;
        }
    }

    private void checkForLoadingNewPhotos(int position) {
        if (position == photoList.size() - 1) {
            if (callback != null) {
                callback.loadPhotoItems();
            }
        }
    }

    public void updatePhotos(List<Photo> photos) {
        this.photoList.addAll(photos);
        notifyDataSetChanged();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo_image_view)
        ImageView photoImageView;
        @BindView(R.id.image_selection_border)
        View imageSelectionBorder;
        @BindView(R.id.triangle_view)
        View triangleView;

        DataViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class InformationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.description_text)
        TextView descriptionText;

        InformationViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public void setCallback(PhotoGridAdapter.Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onPhotoItemClick(int position);
        void loadPhotoItems();
    }
}
