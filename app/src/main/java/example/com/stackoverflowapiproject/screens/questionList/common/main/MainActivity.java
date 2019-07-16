package example.com.stackoverflowapiproject.screens.questionList.common.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;

import java.util.HashSet;
import java.util.Set;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressDispatcher;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressedListener;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BaseActivity;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.FragmentFrameWrapper;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerHelper;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;

public class MainActivity extends BaseActivity implements BackPressDispatcher , FragmentFrameWrapper, NavDrawerViewMvc.Listener, NavDrawerHelper {


    /*public static void startClearTop(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }*/

    private Set<BackPressedListener> mBackPressedListeners = new HashSet<>();

    private ScreensNavigator mScreensNavigator;

    private NavDrawerViewMvc mViewMvc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mScreensNavigator = getCompositionRoot().getScreenNavigator();

        mViewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null);

        mScreensNavigator.toQuestionsList();

        setContentView(mViewMvc.getRootView());
        //setContentView(R.layout.layout_content_frame);

        /*QuestionListFragment fragment;
        if (savedInstanceState == null){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = new QuestionListFragment();
            ft.add(R.id.frame_content,fragment).commit();
        }*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onBackPressed() {

        boolean isBackPressConsumedByAnyListener = false;

        for (BackPressedListener listener : mBackPressedListeners){
            if (listener.onBackPressed()){
                isBackPressConsumedByAnyListener = true;
            }
        }

        if (!isBackPressConsumedByAnyListener){
            super.onBackPressed();
        }
    }

    @Override
    public void registerListener(BackPressedListener listener) {
        mBackPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressedListener listener) {
        mBackPressedListeners.remove(listener);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        //return findViewById(R.id.frame_content);
        return mViewMvc.getFragmentFrame();
    }

    @Override
    public void onQuestionsListClicked() {
        mScreensNavigator.toQuestionsList();

    }

    @Override
    public void openDrawer() {
        mViewMvc.openDrawer();
    }

    @Override
    public void closeDrawer() {
        mViewMvc.closeDrawer();
    }

    @Override
    public boolean isDrawerOpened() {
        return mViewMvc.isDrawerOpen();
    }
}
