package example.com.stackoverflowapiproject.screens.questionList;

import android.view.View;

import example.com.stackoverflowapiproject.networking.common.ObservableViewMvc;
import example.com.stackoverflowapiproject.networking.common.ViewMvc;
import example.com.stackoverflowapiproject.screens.Question;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    void bindQuestion(Question question);
}
