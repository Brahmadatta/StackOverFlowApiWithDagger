package example.com.stackoverflowapiproject.screens.questionList.questionDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.ToolbarViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.screens.questionList.common.views.BaseObservableViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public class QuestionDetailsViewMvcImpl extends BaseObservableViewMvc<QuestionsDetailViewMvc.Listener> implements QuestionsDetailViewMvc{

    private final TextView mQuestionTitle;

    private final TextView mQuestionBody;

    private final ProgressBar mProgressBar;

    private ToolbarViewMvc mToolbarViewMvc;

    private Toolbar mToolbar;


    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.activity_questions_detail_list,parent));
        mQuestionTitle = findViewById(R.id.question_title);
        mQuestionBody = findViewById(R.id.question_body);
        mProgressBar = findViewById(R.id.progress);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        initToolbar();

    }

    private void initToolbar() {



        mToolbar.addView(mToolbarViewMvc.getRootView());

        mToolbarViewMvc.setTitle(getString(R.string.detail_questions_title));

        mToolbarViewMvc.enableUpButtonAndListen(new ToolbarViewMvc.NavigateUpcClickListener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener : getListeners()){
                    listener.onNavigateUpClicked();
                }
            }
        });
        
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
