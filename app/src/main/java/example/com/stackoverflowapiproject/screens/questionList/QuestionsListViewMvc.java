package example.com.stackoverflowapiproject.screens.questionList;

import android.view.View;

import java.util.List;

import example.com.stackoverflowapiproject.screens.Question;

interface QuestionsListViewMvc {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    View getRootView();

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestions(List<Question> questions);


}
