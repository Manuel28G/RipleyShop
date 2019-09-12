package cl.com.ripley.ripleyshop.general.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.home.view.activity.MainActivity;

public class ManagementFragment {

    public static final int FRAME_ID = R.id.body_fragment;
    private static ManagementFragment sManagementFragment;

    private ManagementFragment(){

    }

    public static ManagementFragment getInstance(){
        if(sManagementFragment == null)
            sManagementFragment = new ManagementFragment();
        return  sManagementFragment;
    }

    public void replaceFragment(Fragment mFragment, String mTag, FragmentManager manager) {
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.addToBackStack(mTag);
        transaction.replace(FRAME_ID, mFragment, mTag);
        transaction.commitAllowingStateLoss();
    }

    /**
     * Metodo que reinicia el backStack para que la proxima vez que se llame el onbackpress se dirija
     * al fragment principal
     * @param manager
     */
    public void backToInit(FragmentManager manager){
        manager.popBackStack(MainActivity.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
