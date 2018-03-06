package com.oldfriends.app.util;

/**
 * Created by Administrator on 2016/2/23.
 */
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

public class AnimUtil
{
    private static Animation shakeAnimation(int paramInt)
    {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 10.0F, 0.0F, 0.0F);
        CycleInterpolator localCycleInterpolator = new CycleInterpolator(paramInt);
        localTranslateAnimation.setInterpolator(localCycleInterpolator);
        localTranslateAnimation.setDuration(1000L);
        return localTranslateAnimation;
    }
}