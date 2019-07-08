package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.content.Context;

public class ScreensNavigator {

    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toDialogDetails(String questionId) {
        QuestionsDetailActivity.start(mContext,questionId);
    }
}
