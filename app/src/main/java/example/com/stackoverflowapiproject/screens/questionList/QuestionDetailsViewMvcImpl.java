package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.networking.common.BaseViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public class QuestionDetailsViewMvcImpl extends BaseViewMvc implements QuestionsDetailViewMvc{

    private final TextView mQuestionTitle;

    private final TextView mQuestionBody;

    private final ProgressBar mProgressBar;


    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.activity_questions_detail_list,parent));
        mQuestionTitle = findViewById(R.id.question_title);
        mQuestionBody = findViewById(R.id.question_body);
        mProgressBar = findViewById(R.id.progress);
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionTitle = question.getmTitle();
        String questionBody = question.getmBody();

        mQuestionTitle.setText(questionTitle);
        mQuestionBody.setText(questionBody);
    }

    @Override
    public void showProgressBarIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarIndication() {
        mProgressBar.setVisibility(View.GONE);
    }
}
