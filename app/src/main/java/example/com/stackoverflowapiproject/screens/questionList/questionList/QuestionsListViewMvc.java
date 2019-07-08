package example.com.stackoverflowapiproject.screens.questionList.questionList;

import java.util.List;

import example.com.stackoverflowapiproject.screens.questionList.common.ObservableViewMvc;
import example.com.stackoverflowapiproject.questions.Question;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
