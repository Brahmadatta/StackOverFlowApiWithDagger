package example.com.stackoverflowapiproject.screens.questionList.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerHelper;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.NavDrawerViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.questionDetails.QuestionDetailsViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.questionList.questionslisitem.QuestionListItemViewMvcImpl;
import example.com.stackoverflowapiproject.screens.questionList.questionDetails.QuestionsDetailViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.questionslisitem.QuestionsListItemViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsListViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.questionList.QuestionsRecyclerviewMvcImpl;

public class ViewMvcFactory {


    private final LayoutInflater mLayoutInflater;

    private final NavDrawerHelper mNavDrawerHelper;

    public ViewMvcFactory(LayoutInflater mLayoutInflater, NavDrawerHelper navDrawerHelper) {
        this.mLayoutInflater = mLayoutInflater;
        mNavDrawerHelper = navDrawerHelper;
    }

    public QuestionsListViewMvc getQuestionListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsRecyclerviewMvcImpl(mLayoutInflater,parent,this, mNavDrawerHelper);
    }

    public QuestionsListItemViewMvc getQuestionListViewItemMvc(@Nullable ViewGroup parent){
        return new QuestionListItemViewMvcImpl(mLayoutInflater,parent);
    }

    public QuestionsDetailViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent){
        return new QuestionDetailsViewMvcImpl(mLayoutInflater,parent,this);
    }

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent){
        return new ToolbarViewMvc(mLayoutInflater,parent);
    }

    public NavDrawerViewMvc getNavDrawerViewMvc(@Nullable ViewGroup parent) {
        return new NavDrawerViewMvcImpl(mLayoutInflater,parent);
    }

}
