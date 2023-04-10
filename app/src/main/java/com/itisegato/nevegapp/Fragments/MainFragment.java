package com.itisegato.nevegapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.itisegato.nevegapp.R;

import java.util.ArrayList;

/**
 * A fragment that helps the transition between the three
 * sections: Map, Point and Topics
 */
public class MainFragment extends Fragment {

    private ArrayList<Fragment> fragments;
    private ViewPager2 mViewPager;
    private final BottomNavigationView bottomNavigationView;

    public MainFragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
        initializeFrags();
    }

    private void addNavigationListener(){
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_mappa:
                                mViewPager.setCurrentItem(0);
                                return true;
                            case R.id.navigation_punti:
                                mViewPager.setCurrentItem(1);
                                return true;
                            case R.id.navigation_tematiche:
                                mViewPager.setCurrentItem(2);
                                return true;
                        }
                        return false;
                    }});
    }

    private void initializeFrags(){
        fragments = new ArrayList<>();
        fragments.add(new MapFragment());
        fragments.add(new PointFragment());
        fragments.add(new TematicheFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mViewPager = v.findViewById(R.id.mainFragmentsPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setNestedScrollingEnabled(true);
        mViewPager.setAdapter(new FragmentStateAdapter(this){
            @Override
            public int getItemCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

        });
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_mappa);
                        mViewPager.setUserInputEnabled(false);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_punti);
                        mViewPager.setUserInputEnabled(false);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_tematiche);
                        mViewPager.setUserInputEnabled(true);
                        break;
                }
            }
        });
        mViewPager.setCurrentItem(0);
        addNavigationListener();
        return v;
    }
}