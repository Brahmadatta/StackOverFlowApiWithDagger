package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionListItemViewMvcImpl implements QuestionsListItemViewMvc {

    private final View mRootView;
    private final List<Listener> mListeners = new ArrayList<>(1);
    private Question mQuestion;
    private TextView txtTitle;

    public QuestionListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent){
        mRootView = inflater.inflate(R.layout.layout_quetion_list_item,parent,false);
        txtTitle = findViewById(R.id.txt_title);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : mListeners){
                    listener.onQuestionClicked(mQuestion);
                }
            }
        });
    }

    private <T extends View> T findViewById(int id) {

        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void bindQuestion(Question question) {
        mQuestion = question;
        txtTitle.setText(question.getmTitle());
    }
}
