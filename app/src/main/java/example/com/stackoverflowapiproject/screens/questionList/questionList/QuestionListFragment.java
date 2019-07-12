package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BaseFragment;

public class QuestionListFragment extends BaseFragment {

    private QuestionListController mQuestionListController;

    public static Fragment newInstance() {
        return new QuestionListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        QuestionsListViewMvc mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionListViewMvc(container);

        mQuestionListController = getCompositionRoot().getQuestionsListController();

        mQuestionListController.bindView(mViewMvc);

        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionListController.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        mQuestionListController.onStop();
    }

}
