package com.itisegato.nevegapp.Utilities;

import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * Transformer page per la sezione 'Punti' (Main Activity)
 *  Fonte <<Guida ufficiale di android>>
 */
public class DepthPageTranformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
            view.setAlpha(0);

        } else if (position <= 0) {
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) {
            view.setAlpha(1 - position);
            view.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else {
            view.setAlpha(0);
        }
    }
}