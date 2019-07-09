package example.com.stackoverflowapiproject.screens.questionList.questionDetails;

import example.com.stackoverflowapiproject.screens.questionList.common.views.ViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public interface QuestionsDetailViewMvc extends ViewMvc {

    void bindQuestion(QuestionDetails question);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
