package cl.com.ripley.ripleyshop.general.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cl.com.ripley.ripleyshop.R;

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

    public void replaceFragment(Fragment mFragment, String mTag, FragmentActivity ctx) {
        FragmentManager manager=ctx.getSupportFragmentManager();//create an instance of fragment manager
        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction
        transaction.addToBackStack(mTag);
        transaction.replace(FRAME_ID, mFragment, mTag);
        transaction.commitAllowingStateLoss();
    }
}
