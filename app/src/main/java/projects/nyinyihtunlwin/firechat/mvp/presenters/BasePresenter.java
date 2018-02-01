package projects.nyinyihtunlwin.firechat.mvp.presenters;

/**
 * Created by Dell on 2/1/2018.
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public void onCreate(T view) {
        mView = view;
    }

    public abstract void onStart();

    public void onResume() {

    }

    public void onPause() {

    }

    public abstract void onStop();

    public void onDestroy() {

    }

}
