package com.itbox.markettong.jazzylistview.effects;

import android.view.View;

import com.itbox.markettong.jazzylistview.JazzyEffect;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class WaveEffect implements JazzyEffect {

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setTranslationX(item, -item.getWidth());
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.translationX(0);
    }

}
