package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.networking.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.QuestionRecylerViewHolder> implements QuestionsListItemViewMvc.Listener {





    public interface Listener{
        void onQuestionClicked(Question question);
    }


    private ViewMvcFactory viewMvcFactory;
    private final Listener mListener;
    private List<Question> mQuestion = new ArrayList<>();

    public QuestionsRecyclerAdapter(Listener listener, ViewMvcFactory viewMvcFactory){

        mListener = listener;
        this.viewMvcFactory = viewMvcFactory;
    }


    @NonNull
    @Override
    public QuestionRecylerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsListItemViewMvc viewMvc = viewMvcFactory.getQuestionListViewItemMvc(parent);
        viewMvc.registerListener(this);
        return new QuestionRecylerViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRecylerViewHolder holder, int position) {
        holder.mViewMvc.bindQuestion(mQuestion.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestion.size();
    }

    public void bindQuestions(List<Question> question){
        mQuestion = new ArrayList<>(question);
        notifyDataSetChanged();

    }

    static class QuestionRecylerViewHolder extends RecyclerView.ViewHolder{

        private final QuestionsListItemViewMvc mViewMvc;

        public QuestionRecylerViewHolder(QuestionsListItemViewMvc viewMvc){
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
    }

    @Override
    public void onQuestionClicked(Question question) {
        mListener.onQuestionClicked(question);
    }
}
