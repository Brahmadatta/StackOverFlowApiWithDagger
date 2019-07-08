package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import example.com.stackoverflowapiproject.screens.questionList.common.BaseActivity;
import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public class QuestionsDetailActivity extends BaseActivity implements FetchQuestionDetailsUseCase.Listener {


    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private MessagesDisplayer mMessagesDisplayer;

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

        mMessagesDisplayer = getCompositionRoot().getMessageDisplayer();

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.showProgressBarIndication();
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
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
        mMessagesDisplayer.showUseCaseError();
    }
}
