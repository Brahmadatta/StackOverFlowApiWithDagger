package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.networking.common.BaseObservableViewMvc;
import example.com.stackoverflowapiproject.networking.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.questions.Question;

public class QuestionsRecyclerviewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener{

    private RecyclerView mRecyclerViewQuestions;
    private QuestionsRecyclerAdapter mAdapter;



    public QuestionsRecyclerviewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory){

         setRootView(inflater.inflate(R.layout.activity_main,parent,false));

        mRecyclerViewQuestions = findViewById(R.id.recyclerView_questions);
        mRecyclerViewQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter(this,viewMvcFactory);
        mRecyclerViewQuestions.setAdapter(mAdapter);
    }




    @Override
    public void bindQuestions(List<Question> questions) {

        mAdapter.bindQuestions(questions);

    }


    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : getListeners()){
            listener.onQuestionClicked(question);
        }
    }
}
