package example.com.stackoverflowapiproject.screens.questionList;

import android.view.View;

import java.util.List;

import example.com.stackoverflowapiproject.networking.common.ObservableViewMvc;
import example.com.stackoverflowapiproject.networking.common.ViewMvc;
import example.com.stackoverflowapiproject.screens.Question;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    public interface Listener{
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);


}
