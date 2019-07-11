package example.com.stackoverflowapiproject.screens.questionList.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {


    public interface NavigateUpcClickListener {
        void onNavigateUpClicked();
    }

    public interface HamburgerClickListener{
        void onHamburgerClicked();
    }

    private final TextView mToolbarTitle;
    private final ImageButton btnBack;
    private final ImageButton btnHamburger;



    private NavigateUpcClickListener mNavigateUpcClickListener;

    private HamburgerClickListener mHamburgerClickListener;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent){

        setRootView(inflater.inflate(R.layout.layout_toolbar_text,parent,false));
        mToolbarTitle = findViewById(R.id.txt_toolbar_title);
        btnBack = findViewById(R.id.back_button);
        btnHamburger = findViewById(R.id.hambuger_button);

        btnHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHamburgerClickListener.onHamburgerClicked();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigateUpcClickListener.onNavigateUpClicked();
            }
        });
    }

    public void setTitle(String title){
        mToolbarTitle.setText(title);
    }

    public void enableHamburgerButtonAndListen(HamburgerClickListener hamburgerClickListener){

        if (mNavigateUpcClickListener != null){
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }else {
            mHamburgerClickListener = hamburgerClickListener;
            btnHamburger.setVisibility(View.VISIBLE);
        }
    }

    public void enableUpButtonAndListen(NavigateUpcClickListener navigateUpcClickListener){
        if (mHamburgerClickListener != null){
            throw new RuntimeException("Up and hamburger shouldn't be shown together");
        }else {
            mNavigateUpcClickListener = navigateUpcClickListener;
            btnBack.setVisibility(View.VISIBLE);
        }
    }

}
