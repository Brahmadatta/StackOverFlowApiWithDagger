package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.questions.FetchQuestionDetailsUseCase;
import example.com.stackoverflowapiproject.questions.QuestionDetails;
import example.com.stackoverflowapiproject.screens.questionList.common.BaseActivity;
import example.com.stackoverflowapiproject.networking.questions.QuestionsListResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import example.com.stackoverflowapiproject.questions.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsListActivity extends BaseActivity {

    private QuestionListController mQuestionListController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        QuestionsListViewMvc mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionListViewMvc(null);

        mQuestionListController = getCompositionRoot().getQuestionsListController();

        mQuestionListController.bindView(mViewMvc);

        setContentView(mViewMvc.getRootView());


    }


    @Override
    protected void onStart() {
        super.onStart();
        mQuestionListController.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionListController.onStop();
    }


}
