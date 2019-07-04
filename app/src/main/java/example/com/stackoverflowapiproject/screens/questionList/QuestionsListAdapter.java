package example.com.stackoverflowapiproject.screens.questionList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question> implements QuestionsListItemViewMvc.Listener {

    private final OnQuestionClickListener mOnQuestionClickListener;

    public QuestionsListAdapter(Context context, OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        mOnQuestionClickListener = onQuestionClickListener;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            QuestionsListItemViewMvc viewMvc = new QuestionListItemViewMvcImpl(LayoutInflater.from(getContext()),parent);

            viewMvc.registerListener(this);

            convertView = viewMvc.getRootView();
            convertView.setTag(viewMvc);


        }
        final Question question = getItem(position);


        /* TextView txtTitle = convertView.findViewById(R.id.txt_title);
        txtTitle.setText(question.getmTitle());*/

        //bind the data to views

        QuestionsListItemViewMvc viewMvc = (QuestionsListItemViewMvc) convertView.getTag();
        viewMvc.bindQuestion(question);



        return convertView;
    }


    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    @Override
    public void onQuestionClicked(Question question){
        mOnQuestionClickListener.onQuestionClicked(question);
    }
}
