package example.com.stackoverflowapiproject.common.dependencyInjection;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.screens.questionList.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.screens.questionList.questionList.MessagesDisplayer;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionListController;
import example.com.stackoverflowapiproject.screens.questionList.questionList.ScreensNavigator;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private Activity mActivity;

    public ControllerCompositionRoot(CompositionRoot mCompositionRoot, Activity activity) {
        this.mCompositionRoot = mCompositionRoot;
        this.mActivity = activity;
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
        ,getMessageDisplayer());
    }

    public Context getContext(){
        return mActivity;
    }

    public MessagesDisplayer getMessageDisplayer(){
        return new MessagesDisplayer(getContext());
    }

    public ScreensNavigator getScreenNavigator(){
        return new ScreensNavigator(getContext());
    }
}
