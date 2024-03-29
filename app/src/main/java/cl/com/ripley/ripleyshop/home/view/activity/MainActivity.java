package cl.com.ripley.ripleyshop.home.view.activity;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cl.com.ripley.ripleyshop.R;
import cl.com.ripley.ripleyshop.cart.view.fragment.CartFragment;
import cl.com.ripley.ripleyshop.general.model.GridSpaceDecoration;
import cl.com.ripley.ripleyshop.general.model.UtilHelper;
import cl.com.ripley.ripleyshop.general.view.fragment.ManagementFragment;
import cl.com.ripley.ripleyshop.home.model.HomeProduct;
import cl.com.ripley.ripleyshop.home.presenter.Home;
import cl.com.ripley.ripleyshop.home.presenter.HomePresenter;
import cl.com.ripley.ripleyshop.home.view.adapter.PublicationAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , Home.View{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cardview_home)
    RecyclerView myRecyclerView;
    @BindView(R.id.imgview_letter_ripley)
    ImageView ripleyIcon;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgview_icon_shop)
    ImageView imgViewCart;
    @BindView(R.id.txtview_title)
    TextView titleMenu;
    private PublicationAdapter publicationAdapter;

    private HomePresenter homePresenter;
    private ActionBarDrawerToggle toggle;
    public static final String TAG = MainActivity.class.toString();
    private static int sCountJumps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        creatingRecyclerView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        titleMenu.setText(getResources().getString(R.string.title_cart));
        sCountJumps = 1;
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        enableViews(false,true,false);
        navigationView.setNavigationItemSelectedListener(this);
        homePresenter = new HomePresenter(getApplicationContext(),this);
        homePresenter.getItems();
    }

    /**
     * Método que crea el Recyclerview en forma de dos columnas
     */
    private void creatingRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        myRecyclerView.setLayoutManager(mLayoutManager);
        publicationAdapter = new PublicationAdapter(this, getSupportFragmentManager());
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.addItemDecoration(new GridSpaceDecoration(2, UtilHelper.dpToPx(10,getResources()), true));
        myRecyclerView.setAdapter(publicationAdapter);
    }

    /**
     * Metodo que cambia el menú superior cambiando la opcion lateral por un back
     * @param enable true: coloca la opcion de regresar en el menu superior, false: coloca la opción del menú lateral
     */
    public void enableViews(boolean enable,boolean isbackAction, boolean isCartSection) {

        if(isCartSection){
            imgViewCart.setVisibility(View.GONE);
        }
        else {
            imgViewCart.setVisibility(View.VISIBLE);
        }

        if(enable) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toggle.setToolbarNavigationClickListener(v -> {
                    if(!isbackAction) {
                        ManagementFragment.getInstance().backToInit(getSupportFragmentManager());
                        sCountJumps = 1;
                        enableViews(false,false,false);
                    }
                    else
                        onBackPressed();
                });
                ripleyIcon.setVisibility(View.INVISIBLE);
                if(isbackAction) {
                    sCountJumps ++;
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
                }
                else{
                    sCountJumps ++;
                    titleMenu.setVisibility(View.VISIBLE);
                    toolbar.setNavigationIcon(R.drawable.ic_expand_close);
                }
        } else {
            sCountJumps--;
            titleMenu.setVisibility(View.GONE);
            if(sCountJumps == 0) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toggle.setDrawerIndicatorEnabled(true);
                toggle.setToolbarNavigationClickListener(null);
                ripleyIcon.setVisibility(View.VISIBLE);
                toolbar.setNavigationIcon(R.drawable.ic_options_menu);
            }
            else{
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            }
        }
    }

    private void filterData(){

    }

    @OnClick(R.id.conslay_icon_shop)
    public void onClickCart(){
            ManagementFragment.getInstance().replaceFragment(new CartFragment(this),TAG,getSupportFragmentManager());
            enableViews(true,false,true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            enableViews(false,false,false);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        int[] idFilters = {R.id.nav_techn,
                R.id.nav_electronic,
                R.id.nav_furniture_home,
                R.id.nav_sport_adventure,
                R.id.nav_woman_fashion};
        for(int id : idFilters) {
            ((CheckBox) menu.findItem(id).getActionView())
                    .setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    filterData();
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_techn) {
            // Handle the camera action
        } else if (id == R.id.nav_electronic) {

        } else if (id == R.id.nav_furniture_home) {

        } else if (id == R.id.nav_woman_fashion) {

        } else if (id == R.id.nav_sport_adventure) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void addProducts(List<HomeProduct> products) {
        progressBar.setVisibility(View.GONE);
        publicationAdapter.addProducts(products);
        publicationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorConsultingSKU() {
        Toast.makeText(getApplicationContext(),R.string.error_ep_sku,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorConsultingPublications() {
        Toast.makeText(getApplicationContext(),R.string.error_ep_products,Toast.LENGTH_SHORT).show();
    }
}
