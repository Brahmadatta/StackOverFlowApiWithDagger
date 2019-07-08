package example.com.stackoverflowapiproject.screens.questionList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.networking.common.BaseActivity;
import example.com.stackoverflowapiproject.questions.QuestionDetails;
import example.com.stackoverflowapiproject.networking.questions.QuestionDetailsResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsDetailActivity extends BaseActivity {


    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private StackOverflowApi mStackOverflowApi;

    private QuestionsDetailViewMvc mViewMvc;

    public static void start(Context context,String question_id){
        Intent intent = new Intent(context,QuestionsDetailActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID,question_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStackOverflowApi = getCompositionRoot().getStackOverflowApi();
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.showProgressBarIndication();
        fetchQuestionDetails();
    }

    private void fetchQuestionDetails() {
        mStackOverflowApi.fetchQuestionDetails(getQuestionId()).enqueue(new Callback<QuestionDetailsResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {

                if (response.isSuccessful()){

                    bindQuestionDetails(response.body().getmQuestions());

                }else {
                    networkCallFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                    networkCallFailed();
            }
        });
    }

    private void bindQuestionDetails(QuestionsSchema getmQuestions) {

        mViewMvc.hideProgressBarIndication();
        mViewMvc.bindQuestion(new QuestionDetails(getmQuestions.getmId(),getmQuestions.getmTitle()
        ,getmQuestions.getmBody()));
    }

    private void networkCallFailed() {
        mViewMvc.hideProgressBarIndication();
        Toast.makeText(this, "Network Call Failed.", Toast.LENGTH_SHORT).show();
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }
}
