package example.com.stackoverflowapiproject.screens.questionList.questionDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BaseActivity;
import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.questions.QuestionDetails;
import example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper.ToastsHelper;

public class QuestionsDetailActivity extends BaseActivity implements FetchQuestionDetailsUseCase.Listener,QuestionsDetailViewMvc.Listener{


    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private ToastsHelper mToastsHelper;

    private QuestionsDetailViewMvc mViewMvc;


    public static void start(Context context,String question_id){
        Intent intent = new Intent(context,QuestionsDetailActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID,question_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionsDetailUseCase();

        mToastsHelper = getCompositionRoot().getMessageDisplayer();

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.showProgressBarIndication();
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId());
        mViewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
        mViewMvc.unregisterListener(this);
    }

    private void bindQuestionDetails(QuestionDetails questionDetails) {

        mViewMvc.hideProgressBarIndication();
        mViewMvc.bindQuestion(questionDetails);
    }


    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
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
        onBackPressed();
    }
}
