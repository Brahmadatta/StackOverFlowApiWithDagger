package example.com.stackoverflowapiproject.screens.questionList.questionList;

import java.util.List;

import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.views.ObservableViewMvc;
import example.com.stackoverflowapiproject.questions.Question;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> , NavDrawerViewMvc {

    public interface Listener{
        void onQuestionClicked(Question question);

        void onQuestionsListClicked();

    }

    void bindQuestions(List<Question> questions);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
