package example.com.stackoverflowapiproject.screens.questionList.questionDetails;

import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.DrawerItems;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.views.ObservableViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.views.ViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public interface QuestionsDetailViewMvc extends ObservableViewMvc<QuestionsDetailViewMvc.Listener> {

    public interface Listener{
        void onNavigateUpClicked();
    }

    void bindQuestion(QuestionDetails question);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
