package example.com.stackoverflowapiproject.screens.questionList.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import example.com.stackoverflowapiproject.common.CustomApplication;
import example.com.stackoverflowapiproject.common.dependencyInjection.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot(){
        if (mControllerCompositionRoot == null){
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication)getApplication()).getCompositionRoot(),this
            );
        }
        return mControllerCompositionRoot;
    }

    /*protected CompositionRoot getCompositionRoot() {
        return ((CustomApplication) getApplication()).getCompositionRoot();
    }*/
}
