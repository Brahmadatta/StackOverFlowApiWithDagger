package example.com.stackoverflowapiproject.screens.questionList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question> {

    private final OnQuestionClickListener mOnQuestionClickListener;

    public QuestionsListAdapter(Context context, OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        mOnQuestionClickListener = onQuestionClickListener;
    }

    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    public static class ViewHolder{
        private TextView mTxtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quetion_list_item,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mTxtTitle = convertView.findViewById(R.id.txt_title);
            convertView.setTag(viewHolder);
        }
        final Question question = getItem(position);

        //bond the data to views
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mTxtTitle.setText(question.getmTitle());

        //set listener
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onQuestionClicked(question);
            }
        });

        return convertView;
    }

    private void onQuestionClicked(Question question){
        mOnQuestionClickListener.onQuestionClicked(question);
    }
}
