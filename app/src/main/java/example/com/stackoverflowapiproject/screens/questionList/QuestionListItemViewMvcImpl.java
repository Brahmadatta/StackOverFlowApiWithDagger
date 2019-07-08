package example.com.stackoverflowapiproject.screens.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.networking.common.BaseObservableViewMvc;
import example.com.stackoverflowapiproject.questions.Question;

public class QuestionListItemViewMvcImpl extends BaseObservableViewMvc<QuestionsListItemViewMvc.Listener> implements QuestionsListItemViewMvc {

    private Question mQuestion;
    private TextView txtTitle;

    public QuestionListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent){
         setRootView(inflater.inflate(R.layout.layout_quetion_list_item,parent,false));
        txtTitle = findViewById(R.id.txt_title);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : getListeners()){
                    listener.onQuestionClicked(mQuestion);
                }
            }
        });
    }


    @Override
    public void bindQuestion(Question question) {
        mQuestion = question;
        txtTitle.setText(question.getmTitle());
    }
}
