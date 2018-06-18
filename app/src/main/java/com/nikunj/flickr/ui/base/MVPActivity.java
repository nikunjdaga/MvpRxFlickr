package com.nikunj.flickr.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by nikunj on 6/6/18.
 */
public abstract class MVPActivity<PRESENTER extends BasePresenter, VIEW extends View> extends BaseActivity {

    PRESENTER presenter;

    protected abstract VIEW getView();
    protected abstract PRESENTER getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = getPresenter();
        if (presenter != null) {
            presenter.setView(getView());
        }
        presenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
