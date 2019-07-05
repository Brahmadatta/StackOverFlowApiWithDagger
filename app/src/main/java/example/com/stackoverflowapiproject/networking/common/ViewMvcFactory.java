package example.com.stackoverflowapiproject.networking.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import example.com.stackoverflowapiproject.screens.questionList.QuestionListItemViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.QuestionsListItemViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.QuestionsListViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.QuestionsRecyclerviewMvcImpl;

public class ViewMvcFactory {


    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public QuestionsListViewMvc getQuestionListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsRecyclerviewMvcImpl(mLayoutInflater,parent,this);
    }

    public QuestionsListItemViewMvc getQuestionListViewItemMvc(@Nullable ViewGroup parent){
        return new QuestionListItemViewMvcImpl(mLayoutInflater,parent);
    }
}
