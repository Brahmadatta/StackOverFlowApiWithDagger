package example.com.stackoverflowapiproject.screens.questionList.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {


    private final TextView mToolbarTitle;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent){

        setRootView(inflater.inflate(R.layout.toolbar_layout,parent,false));
        mToolbarTitle = findViewById(R.id.tool_bar);
    }

    public void setTitle(String title){
        mToolbarTitle.setText(title);
    }

}
