package example.com.stackoverflowapiproject.networking.common;


import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc implements ViewMvc {

    public View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    protected void setRootView(View rootView){
        mRootView = rootView;
    }


    protected  <T extends View> T findViewById(int id) {

        return getRootView().findViewById(id);
    }

    protected Context getContext() {
        return getRootView().getContext();
    }
}
