package example.com.stackoverflowapiproject.screens.questionList;

import android.view.View;

import example.com.stackoverflowapiproject.screens.Question;

public interface QuestionsListItemViewMvc {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    View getRootView();

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestion(Question question);
}
