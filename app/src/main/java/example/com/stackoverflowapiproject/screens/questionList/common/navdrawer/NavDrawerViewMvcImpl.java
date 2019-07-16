package example.com.stackoverflowapiproject.screens.questionList.common.navdrawer;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import example.com.stackoverflowapiproject.R;
import example.com.stackoverflowapiproject.screens.questionList.common.views.BaseObservableViewMvc;

public class NavDrawerViewMvcImpl extends BaseObservableViewMvc<NavDrawerViewMvc.Listener>
        implements NavDrawerViewMvc{

    private final DrawerLayout mDrawerLayout;
    private final FrameLayout mFrameLayout;
    private final NavigationView mNavigationView;

    public NavDrawerViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        //super.setRootView(inflater.inflate(R.layout.layout_drawer,parent,false));
        setRootView(inflater.inflate(R.layout.layout_drawer,parent,false));
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mFrameLayout = findViewById(R.id.frame_content);
        mNavigationView = findViewById(R.id.nav_view);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.drawer_menu_latest_questions){

                    for (Listener listener : getListeners()){
                        listener.onQuestionsListClicked();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void openDrawer() {

        mDrawerLayout.openDrawer(Gravity.START);

    }


    @Override
    public FrameLayout getFragmentFrame() {
        return mFrameLayout;
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    public FrameLayout getFrameLayout() {
        return mFrameLayout;
    }

    /*protected abstract void onDrawerItemClicked(DrawerItems items);*/

    /*@Override
    protected void setRootView(View view) {
        mFrameLayout.addView(view);
    }*/
}
