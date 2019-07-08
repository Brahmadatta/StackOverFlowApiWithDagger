package example.com.stackoverflowapiproject.screens.questionList.questionList;

import example.com.stackoverflowapiproject.screens.questionList.common.ObservableViewMvc;
import example.com.stackoverflowapiproject.questions.Question;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    void bindQuestion(Question question);
}
