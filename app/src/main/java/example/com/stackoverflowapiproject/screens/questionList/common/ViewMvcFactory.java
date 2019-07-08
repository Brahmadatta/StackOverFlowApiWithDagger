package example.com.stackoverflowapiproject.screens.questionList.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionDetailsViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionListItemViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsDetailViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsListItemViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsListViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsRecyclerviewMvcImpl;

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

    public QuestionsDetailViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent){
        return new QuestionDetailsViewMvcImpl(mLayoutInflater,parent);
    }
}
