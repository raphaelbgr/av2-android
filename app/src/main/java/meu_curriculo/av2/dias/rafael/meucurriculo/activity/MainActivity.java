package meu_curriculo.av2.dias.rafael.meucurriculo.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import meu_curriculo.av2.dias.rafael.meucurriculo.R;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.CoursesFragment;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.DegreeFragment;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.PersonalInfoFragment;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.ProfessionalExperienceFragment;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.PublicationsFragment;
import meu_curriculo.av2.dias.rafael.meucurriculo.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            displaySelectedScreen(R.id.action_settings);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id){
            case R.id.nav_personal_info:
                fragment = new PersonalInfoFragment();
                break;
            case R.id.nav_degree:
                fragment = new DegreeFragment();
                break;
            case R.id.nav_professional_experience:
                fragment = new ProfessionalExperienceFragment();
                break;
            case R.id.nav_courses:
                fragment = new CoursesFragment();
                break;
            case R.id.nav_publications:
                fragment = new PublicationsFragment();
                break;
            case R.id.action_settings:
                fragment = new SettingsFragment();
                break;
        }
        if(fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}

