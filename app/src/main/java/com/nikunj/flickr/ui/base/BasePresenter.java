package com.nikunj.flickr.ui.base;

import com.nikunj.flickr.data.RepositoryManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by nikunj on 6/6/18.
 */
public class BasePresenter<V extends View> {

    private V view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RepositoryManager repositoryManager;

    @Inject
    public BasePresenter(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    protected void onCreate() {
    }

    protected void onStart() {
    }

    protected void onStop() {
    }

    protected void onDestroy() {
        compositeDisposable.clear();
    }

    protected void withDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
}
