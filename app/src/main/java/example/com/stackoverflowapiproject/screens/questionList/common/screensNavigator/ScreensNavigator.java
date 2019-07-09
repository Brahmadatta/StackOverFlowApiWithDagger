package example.com.stackoverflowapiproject.screens.questionList.common.screensNavigator;

import android.content.Context;

import example.com.stackoverflowapiproject.screens.questionList.questionDetails.QuestionsDetailActivity;

public class ScreensNavigator {

    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toDialogDetails(String questionId) {
        QuestionsDetailActivity.start(mContext,questionId);
    }
}
