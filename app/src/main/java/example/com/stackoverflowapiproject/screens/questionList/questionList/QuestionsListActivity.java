package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.os.Bundle;

import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BaseActivity;

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

    @Override
    public void onBackPressed() {

        if (!mQuestionListController.onBackPressed()){
            super.onBackPressed();
        }
    }
}
