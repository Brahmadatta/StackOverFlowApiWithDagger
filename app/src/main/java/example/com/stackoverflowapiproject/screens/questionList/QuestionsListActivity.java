package example.com.stackoverflowapiproject.screens.questionList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.networking.questions.QuestionsListResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import example.com.stackoverflowapiproject.screens.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements QuestionsListViewMvcImpl.Listener {

    private StackOverflowApi mStackOverflowApi;



    private QuestionsListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mViewMvc = new QuestionsListViewMvcImpl(LayoutInflater.from(this),null);




        mViewMvc.registerListener(this);

        mStackOverflowApi = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StackOverflowApi.class);

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
        Toast.makeText(this, question.getmTitle(), Toast.LENGTH_SHORT).show();
    }
}
