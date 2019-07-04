package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionsRecyclerviewMvcImpl implements QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener{

    private RecyclerView mRecyclerViewQuestions;
    private QuestionsRecyclerAdapter mAdapter;

    private final View mRootView;

    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsRecyclerviewMvcImpl(LayoutInflater inflater, ViewGroup parent){

        mRootView = inflater.inflate(R.layout.activity_main,parent,false);

        mRecyclerViewQuestions = findViewById(R.id.recyclerView_questions);
        mRecyclerViewQuestions.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        mAdapter = new QuestionsRecyclerAdapter(inflater,this);
        mRecyclerViewQuestions.setAdapter(mAdapter);
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
    public void bindQuestions(List<Question> questions) {

        mAdapter.bindQuestions(questions);

    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : mListeners){
            listener.onQuestionClicked(question);
        }
    }
}
