package example.com.stackoverflowapiproject.screens.questionList.common.navdrawer;

import android.widget.FrameLayout;

import example.com.stackoverflowapiproject.screens.questionList.common.views.ObservableViewMvc;

public interface NavDrawerViewMvc extends ObservableViewMvc<NavDrawerViewMvc.Listener> {


    interface Listener{


        void onQuestionsListClicked();
    }

    FrameLayout getFragmentFrame();

    boolean isDrawerOpen();

    void  openDrawer();

    void closeDrawer();

}
