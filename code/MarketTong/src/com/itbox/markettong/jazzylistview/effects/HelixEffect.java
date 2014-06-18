package com.itbox.markettong.jazzylistview.effects;

import android.view.View;

import com.itbox.markettong.jazzylistview.JazzyEffect;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class HelixEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 180;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setRotationY(item, INITIAL_ROTATION_ANGLE);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationYBy(INITIAL_ROTATION_ANGLE * scrollDirection);
    }

}
