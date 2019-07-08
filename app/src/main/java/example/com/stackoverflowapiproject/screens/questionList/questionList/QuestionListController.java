package example.com.stackoverflowapiproject.screens.questionList.questionList;

import java.util.List;

import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.questions.Question;

public class QuestionListController implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private final FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase;

    private QuestionsListViewMvc mViewMvc;

    private final ScreensNavigator mScreensNavigator;

    private final MessagesDisplayer mMessagesDisplayer;

    public QuestionListController(FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase, ScreensNavigator mScreensNavigator, MessagesDisplayer mMessagesDisplayer) {
        this.mFetchLastActiveQuestionsUseCase = mFetchLastActiveQuestionsUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mMessagesDisplayer = mMessagesDisplayer;
    }

    public void onStart(){
        mViewMvc.showProgressBarIndication();
        mFetchLastActiveQuestionsUseCase.registerListener(this);
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestions();
    }

    public void bindView(QuestionsListViewMvc viewMvc){
        mViewMvc = viewMvc;
        mViewMvc.registerListener(this);
    }

    public void onStop(){
        mFetchLastActiveQuestionsUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {

        mScreensNavigator.toDialogDetails(question.getmId());
    }

    @Override
    public void onQuestionsFetchFailure() {
        mViewMvc.hideProgressBarIndication();
        mMessagesDisplayer.showUseCaseError();
    }

    @Override
    public void onLastactiveQuestionsFetched(List<Question> questions) {
        mViewMvc.hideProgressBarIndication();
        mViewMvc.bindQuestions(questions);
    }
}
