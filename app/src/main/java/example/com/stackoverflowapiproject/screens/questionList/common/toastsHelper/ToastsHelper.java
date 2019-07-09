package example.com.stackoverflowapiproject.screens.questionList.common.toastsHelper;

import android.content.Context;
import android.widget.Toast;

import example.com.stackoverflowapiproject.R;

public class ToastsHelper {

    private final Context mContext;

    public ToastsHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void showUseCaseError() {

        Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
