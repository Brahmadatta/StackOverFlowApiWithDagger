package example.com.stackoverflowapiproject.screens.questionList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionsListViewMvcImpl implements QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {


    private ListView mListQuestions;
    private QuestionsListAdapter mQuestionsListAdapter;
    private final View mRootView;

    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent){
         mRootView = inflater.inflate(R.layout.activity_main,parent,false);

        mListQuestions = findViewById(R.id.lst_questions);
        mQuestionsListAdapter = new QuestionsListAdapter(getContext(),this);
        mListQuestions.setAdapter(mQuestionsListAdapter);
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View>T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void registerListener(Listener listener){
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener){
        mListeners.remove(listener);
    }

    @Override
    public void onQuestionClicked(Question question) {

        for (Listener listener : mListeners){
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {

        mQuestionsListAdapter.clear();
        mQuestionsListAdapter.addAll(questions);
        mQuestionsListAdapter.notifyDataSetChanged();
    }
}
