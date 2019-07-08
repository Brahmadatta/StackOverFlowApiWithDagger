package example.com.stackoverflowapiproject.screens.questionList;

import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.networking.common.BaseActivity;
import example.com.stackoverflowapiproject.networking.questions.QuestionsListResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import example.com.stackoverflowapiproject.questions.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsListActivity extends BaseActivity implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc.Listener {

    private StackOverflowApi mStackOverflowApi;

    private QuestionsListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mStackOverflowApi = getCompositionRoot().getStackOverflowApi();

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionListViewMvc(null);

        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());


    }


    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestions();
    }

    private void fetchQuestions() {

        mStackOverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if (response.isSuccessful()){
                            bindQuestions(response.body().getQuestions());
                        }else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                        networkCallFailed();
                    }
                });

    }

    private void networkCallFailed() {

        Toast.makeText(this, "Network call Failed", Toast.LENGTH_SHORT).show();
    }

    private void bindQuestions(List<QuestionsSchema> getmQuestions) {

        List<Question> questions = new ArrayList<>(getmQuestions.size());
        for (QuestionsSchema questionsSchema : getmQuestions){
            questions.add(new Question(questionsSchema.getmId(),questionsSchema.getmTitle()));
        }
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onQuestionClicked(Question question) {

        QuestionsDetailActivity.start(this,question.getmId());
    }
}
