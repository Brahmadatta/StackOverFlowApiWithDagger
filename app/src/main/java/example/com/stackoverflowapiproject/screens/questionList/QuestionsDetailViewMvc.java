package example.com.stackoverflowapiproject.screens.questionList;

import example.com.stackoverflowapiproject.networking.common.ViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public interface QuestionsDetailViewMvc extends ViewMvc {

    void bindQuestion(QuestionDetails question);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
