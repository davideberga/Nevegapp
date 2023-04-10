package com.itisegato.nevegapp.Activities;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import com.itisegato.nevegapp.R;
import com.itisegato.nevegapp.Utilities.DepthPageTranformer;
import com.itisegato.nevegapp.Utilities.Image;
import com.itisegato.nevegapp.Utilities.SwipeAdapter;

/**
 *  Galleria
 */
public class ImageActivity extends FragmentActivity {

    private ViewPager viewPager;
    private List<Image> images;
    private int pos;
    private  boolean premuto;
    private SwipeAdapter swipeAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        riceviImmagini();

        inviaImmagini();

        mContext = this;
    }

    private void riceviImmagini(){
        Intent intent = getIntent();
        images = intent.getParcelableArrayListExtra("ListImages");
        pos = intent.getIntExtra("pos", -1);
        premuto = intent.getBooleanExtra("premuto", false);
    }

    private void inviaImmagini(){
        viewPager = (ViewPager) findViewById(R.id.view_pager_id);
        swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), images, premuto, mContext);
        viewPager.setPageTransformer(true, new DepthPageTranformer(), View.LAYER_TYPE_NONE);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(pos);
    }

}
