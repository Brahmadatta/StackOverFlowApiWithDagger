package example.com.stackoverflowapiproject.screens.questionList.questionDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.questions.QuestionDetails;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressDispatcher;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressedListener;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BaseFragment;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.DrawerItems;
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;
import example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper.ToastsHelper;


public class QuestionDetailsFragment extends BaseFragment  implements FetchQuestionDetailsUseCase.Listener,QuestionsDetailViewMvc.Listener{

    private static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private ToastsHelper mToastsHelper;

    private QuestionsDetailViewMvc mViewMvc;

    private ScreensNavigator mScreensNavigator;


    public static QuestionDetailsFragment newInstance(String questionId){

        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID,questionId);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionsDetailUseCase();

        mToastsHelper = getCompositionRoot().getToastsHelper();

        mScreensNavigator = getCompositionRoot().getScreenNavigator();


        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);

        return mViewMvc.getRootView();

    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.showProgressBarIndication();
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId());
        mViewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
        mViewMvc.unregisterListener(this);
    }

    private void bindQuestionDetails(QuestionDetails questionDetails) {

        mViewMvc.hideProgressBarIndication();
        mViewMvc.bindQuestion(questionDetails);
    }


    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFailed() {
        mViewMvc.hideProgressBarIndication();
        mToastsHelper.showUseCaseError();
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.naviagetUp();
    }





}
