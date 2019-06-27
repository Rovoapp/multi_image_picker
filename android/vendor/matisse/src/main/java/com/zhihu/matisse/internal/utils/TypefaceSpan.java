package com.zhihu.matisse.internal.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * Created by Joshua on 3/9/15.
 */
public class TypefaceSpan extends MetricAffectingSpan {
    private static final int CACHE_SIZE = 12;
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(CACHE_SIZE);
    private Typeface mTypeface;

    public TypefaceSpan(Context context, String typefaceName) {
        mTypeface = sTypefaceCache.get(typefaceName);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s.ttf", typefaceName));
            sTypefaceCache.put(typefaceName, mTypeface);
        }
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
