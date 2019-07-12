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
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;

public class MainActivity extends BaseActivity implements BackPressDispatcher , FragmentFrameWrapper {


    public static void startClearTop(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private Set<BackPressedListener> mBackPressedListeners = new HashSet<>();

    private ScreensNavigator mScreensNavigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);

        mScreensNavigator = getCompositionRoot().getScreenNavigator();
        mScreensNavigator.toQuestionsList();


        /*QuestionListFragment fragment;
        if (savedInstanceState == null){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = new QuestionListFragment();
            ft.add(R.id.frame_content,fragment).commit();
        }*/

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
        return findViewById(R.id.frame_content);
    }
}
