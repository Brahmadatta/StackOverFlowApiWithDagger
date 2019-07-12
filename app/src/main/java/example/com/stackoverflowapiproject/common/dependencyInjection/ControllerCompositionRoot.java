package example.com.stackoverflowapiproject.common.dependencyInjection;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.screens.questionList.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressDispatcher;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.FragmentFrameWrapper;
import example.com.stackoverflowapiproject.screens.questionList.common.fragmentframehelper.FragmentFrameHelper;
import example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper.ToastsHelper;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionListController;
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private FragmentActivity mActivity;

    public ControllerCompositionRoot(CompositionRoot mCompositionRoot, FragmentActivity activity) {
        this.mCompositionRoot = mCompositionRoot;
        this.mActivity = activity;
    }

    private FragmentActivity getActivity(){
        return mActivity;
    }

    private FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }

    public StackOverflowApi getStackOverflowApi() {
        return mCompositionRoot.getStackOverflowApi();
    }

    private LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(mActivity);
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionsDetailUseCase() {
        return new FetchQuestionDetailsUseCase(getStackOverflowApi());
    }

    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase(){
        return new FetchLastActiveQuestionsUseCase(getStackOverflowApi());
    }

    public QuestionListController getQuestionsListController() {
        return new QuestionListController(getFetchLastActiveQuestionsUseCase(),getScreenNavigator()
        ,getToastsHelper(),getBackPressDispatcher());
    }

    public Context getContext(){
        return mActivity;
    }

    public ToastsHelper getToastsHelper(){
        return new ToastsHelper(getContext());
    }

    public ScreensNavigator getScreenNavigator(){
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(),getFragmentFrameWrapper(),getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {

        return (FragmentFrameWrapper) getActivity();
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (BackPressDispatcher) getActivity();
    }
}
