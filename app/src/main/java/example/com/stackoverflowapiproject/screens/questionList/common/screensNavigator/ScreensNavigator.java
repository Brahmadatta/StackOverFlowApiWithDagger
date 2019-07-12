package example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import example.com.stackoverflowapiproject.screens.questionList.common.controllers.FragmentFrameWrapper;
import example.com.stackoverflowapiproject.screens.questionList.common.fragmentframehelper.FragmentFrameHelper;
import example.com.stackoverflowapiproject.screens.questionList.questionDetails.QuestionDetailsFragment;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionListFragment;

public class ScreensNavigator {

    /*private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toDialogDetails(String questionId) {
        QuestionsDetailActivity.start(mContext,questionId);
    }

    public void toQuestionsListClearTop() {

        MainActivity.startClearTop(mContext);
    }*/

    /*private final FragmentManager mFragmentManager;

    private final FragmentFrameWrapper mFragmentFrameWrapper;


    public ScreensNavigator(FragmentManager fragmentManager, FragmentFrameWrapper fragmentFrameWrapper) {
        mFragmentManager = fragmentManager;
        mFragmentFrameWrapper = fragmentFrameWrapper;
    }*/

    private FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toQuestionDetails(String questionId){

        mFragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(questionId));

        /*FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), QuestionDetailsFragment.newInstance(questionId)).commit();
*/
    }

    public void toQuestionsList(){

        mFragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionListFragment.newInstance());

        /*mFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), QuestionListFragment.newInstance());*/
    }

    public void naviagetUp() {

        mFragmentFrameHelper.navigateUp();
    }
}
