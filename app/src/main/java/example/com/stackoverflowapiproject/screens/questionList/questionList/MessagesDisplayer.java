package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.content.Context;
import android.widget.Toast;

import example.com.stackoverflowapiproject.R;

public class MessagesDisplayer {

    private final Context mContext;

    public MessagesDisplayer(Context mContext) {
        this.mContext = mContext;
    }

    public void showUseCaseError() {

        Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
