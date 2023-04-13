package itisegato.com.nevegapptemp.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    private List<Image> images;
    private boolean premuto;
    private Context mContext;

    public SwipeAdapter(FragmentManager fm, List<Image> images, boolean premuto, Context mContext) {
        super(fm);
        this.images = images;
        this.premuto = premuto;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int pos) {
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", images.get(pos).getTitle());
        bundle.putInt("image", images.get(pos).getThumbnail());
        bundle.putBoolean("premuto", premuto);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
