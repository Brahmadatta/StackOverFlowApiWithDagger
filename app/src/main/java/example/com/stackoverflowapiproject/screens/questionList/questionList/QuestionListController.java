package example.com.stackoverflowapiproject.screens.questionList.questionList;

import java.util.List;

import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.questions.Question;
import example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper.ToastsHelper;
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;

public class QuestionListController implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private final FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase;

    private QuestionsListViewMvc mViewMvc;

    private final ScreensNavigator mScreensNavigator;

    private final ToastsHelper mToastsHelper;

    public QuestionListController(FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase, ScreensNavigator mScreensNavigator, ToastsHelper mToastsHelper) {
        this.mFetchLastActiveQuestionsUseCase = mFetchLastActiveQuestionsUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mToastsHelper = mToastsHelper;
    }

    public void onStart(){
        mViewMvc.registerListener(this);
        mViewMvc.showProgressBarIndication();
        mFetchLastActiveQuestionsUseCase.registerListener(this);
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestions();
    }

    public void bindView(QuestionsListViewMvc viewMvc){
        mViewMvc = viewMvc;
    }

    public void onStop(){
        mViewMvc.unregisterListener(this);
        mFetchLastActiveQuestionsUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {

        mScreensNavigator.toDialogDetails(question.getmId());
    }

    @Override
    public void onQuestionsListClicked() {
        //this is questions list screen - no - op
    }

    @Override
    public void onQuestionsFetchFailure() {
        mViewMvc.hideProgressBarIndication();
        mToastsHelper.showUseCaseError();
    }

    @Override
    public void onLastactiveQuestionsFetched(List<Question> questions) {
        mViewMvc.hideProgressBarIndication();
        mViewMvc.bindQuestions(questions);
    }
}
