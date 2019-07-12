package example.com.stackoverflowapiproject.screens.questionList.questionList;

import java.util.List;

import example.com.stackoverflowapiproject.questions.FetchLastActiveQuestionsUseCase;
import example.com.stackoverflowapiproject.questions.Question;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressDispatcher;
import example.com.stackoverflowapiproject.screens.questionList.common.controllers.BackPressedListener;
import example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper.ToastsHelper;
import example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator.ScreensNavigator;

public class QuestionListController implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener, BackPressedListener {

    private final FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase;

    private QuestionsListViewMvc mViewMvc;

    private final ScreensNavigator mScreensNavigator;

    private final ToastsHelper mToastsHelper;

    private final BackPressDispatcher mBackPressDispatcher;

    public QuestionListController(FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase, ScreensNavigator mScreensNavigator, ToastsHelper mToastsHelper,
                                  BackPressDispatcher backPressDispatcher1) {
        this.mFetchLastActiveQuestionsUseCase = mFetchLastActiveQuestionsUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mToastsHelper = mToastsHelper;
        this.mBackPressDispatcher = backPressDispatcher1;
    }

    public void onStart(){
        mViewMvc.registerListener(this);
        mViewMvc.showProgressBarIndication();
        mFetchLastActiveQuestionsUseCase.registerListener(this);
        mBackPressDispatcher.registerListener(this);
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestions();
    }

    public void bindView(QuestionsListViewMvc viewMvc){
        mViewMvc = viewMvc;
    }

    public void onStop(){
        mViewMvc.unregisterListener(this);
        mBackPressDispatcher.unregisterListener(this);
        mFetchLastActiveQuestionsUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {

        mScreensNavigator.toQuestionDetails(question.getmId());
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

    public boolean onBackPressed() {
        if (mViewMvc.isDrawerOpen()){
            mViewMvc.closeDrawer();
            return true;
        }else {
            return false;
        }
    }
}
