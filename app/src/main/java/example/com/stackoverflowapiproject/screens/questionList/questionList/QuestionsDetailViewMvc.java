package example.com.stackoverflowapiproject.screens.questionList.questionList;

import example.com.stackoverflowapiproject.screens.questionList.common.ViewMvc;
import example.com.stackoverflowapiproject.questions.QuestionDetails;

public interface QuestionsDetailViewMvc extends ViewMvc {

    void bindQuestion(QuestionDetails question);

    void showProgressBarIndication();

    void hideProgressBarIndication();
}
