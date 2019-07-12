package example.com.stackoverflowapiproject.screens.questionList.common.controllers;

import androidx.fragment.app.Fragment;

import example.com.stackoverflowapiproject.common.CustomApplication;
import example.com.stackoverflowapiproject.common.dependencyInjection.ControllerCompositionRoot;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot(){
        if (mControllerCompositionRoot == null){
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication)requireActivity().getApplication()).getCompositionRoot(),requireActivity()
            );
        }
        return mControllerCompositionRoot;
    }
}
