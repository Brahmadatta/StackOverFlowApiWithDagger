package example.com.stackoverflowapiproject.screens.questionList.questionList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.ToolbarViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.BaseNavDrawerViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.navdrawer.DrawerItems;
import example.com.stackoverflowapiproject.screens.questionList.common.views.BaseObservableViewMvc;
import example.com.stackoverflowapiproject.screens.questionList.common.ViewMvcFactory;
import example.com.stackoverflowapiproject.questions.Question;
import example.com.stackoverflowapiproject.screens.questionList.questionDetails.QuestionsDetailViewMvc;

public class QuestionsRecyclerviewMvcImpl extends BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener{

    private RecyclerView mRecyclerViewQuestions;
    private QuestionsRecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private ToolbarViewMvc mToolbarViewMvc;
    private Toolbar mToolbar;


    public QuestionsRecyclerviewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory){

        super(inflater, parent);
         setRootView(inflater.inflate(R.layout.activity_main,parent,false));

        mRecyclerViewQuestions = findViewById(R.id.recyclerView_questions);
        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerViewQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter(this,viewMvcFactory);
        mRecyclerViewQuestions.setAdapter(mAdapter);

        mToolbar = findViewById(R.id.tool_bar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        initToolbar();
    }

    private void initToolbar() {


        mToolbarViewMvc.setTitle(getString(R.string.latest_questions_title));
        mToolbar.addView(mToolbarViewMvc.getRootView());

        mToolbarViewMvc.enableHamburgerButtonAndListen(new ToolbarViewMvc.HamburgerClickListener() {
            @Override
            public void onHamburgerClicked() {

                openDrawer();

            }
        });

    }


    @Override
    public void bindQuestions(List<Question> questions) {

        mAdapter.bindQuestions(questions);

    }



    @Override
    public void showProgressBarIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBarIndication() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : getListeners()){
            listener.onQuestionClicked(question);
        }
    }

    @Override
    protected void onDrawerItemClicked(DrawerItems items) {
        for (Listener listener : getListeners()){
            switch (items){
                case QUESTIONS_LIST:
                    listener.onQuestionsListClicked();
            }
        }
    }
}
