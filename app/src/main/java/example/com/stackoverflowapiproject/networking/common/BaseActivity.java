package example.com.stackoverflowapiproject.networking.common;

import androidx.appcompat.app.AppCompatActivity;

import example.com.stackoverflowapiproject.CustomApplication;
import example.com.stackoverflowapiproject.common.dependencyInjection.CompositionRoot;
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
}
