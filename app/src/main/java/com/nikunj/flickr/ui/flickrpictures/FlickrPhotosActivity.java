package com.nikunj.flickr.ui.flickrpictures;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nikunj.flickr.R;
import com.nikunj.flickr.data.model.Photo;
import com.nikunj.flickr.ui.base.MVPActivity;
import com.nikunj.flickr.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikunj on 6/6/18.
 */
@SuppressWarnings("ButterKnifeInjectNotCalled")
public class FlickrPhotosActivity extends MVPActivity<FlickrPhotosPresenter, FlickrPhotosView>
        implements FlickrPhotosView, PhotoGridAdapter.Callback {

    @BindView(R.id.photos_loading_progress_bar)
    ProgressBar photosLoadingProgressBar;

    @BindView(R.id.recyclerView)
    RecyclerView photoGrid;

    @Inject
    FlickrPhotosPresenter presenter;

    private PhotoGridAdapter photoGridAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected FlickrPhotosView getView() {
        return this;
    }

    @Override
    protected FlickrPhotosPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getContentView() {
        return R.layout.flickr_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoGridAdapter = new PhotoGridAdapter(this, new ArrayList<>());
        mGridLayoutManager = new GridLayoutManager(this,2);

        photoGrid.setHasFixedSize(true);
        photoGrid.setLayoutManager(mGridLayoutManager);
        photoGridAdapter.setCallback(this);
        photoGrid.setAdapter(photoGridAdapter);
    }

    @Override
    public void updatePhotos(List<Photo> photos) {
        hideLoading();
        photoGridAdapter.updatePhotos(photos);
    }

    @Override
    public void showErrorMessage(int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhotoItemClick(int position) {
        if(position==photoGridAdapter.getSelectedPos()) {
            photoGridAdapter.setSelectedPos(-1);
            mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });

        } else {
            photoGridAdapter.setSelectedPos(position);
            final int infoPos = Utils.getInfoPosition(position);
            mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    if(position==infoPos) return 2;
                    else return 1;
                }
            });
        }
        photoGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadPhotoItems() {
        showLoading();
        presenter.loadPhotos();
    }

    private void showLoading() {
        hideLoading();
        photosLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        photosLoadingProgressBar.setVisibility(View.GONE);
    }
}
