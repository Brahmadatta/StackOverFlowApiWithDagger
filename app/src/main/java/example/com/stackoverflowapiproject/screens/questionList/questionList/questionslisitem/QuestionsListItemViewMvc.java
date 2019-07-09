package example.com.stackoverflowapiproject.screens.questionList.questionList.questionslisitem;

import example.com.stackoverflowapiproject.screens.questionList.common.views.ObservableViewMvc;
import example.com.stackoverflowapiproject.questions.Question;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    void bindQuestion(Question question);
}
