package com.zhihu.matisse.internal.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.utils.TypefaceProvider;

/**
 * Created by Joshua on 9/10/13.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class RVTextView extends AppCompatTextView {

    private boolean caps;
    private int mDrawableWidth;
    private int mDrawableHeight;

    public RVTextView(Context context) {
        super(context);
    }

    public RVTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(attrs);
        }
    }

    private void init(AttributeSet attrs) {
        TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.rv);

        try {
            int style = attrsArray.getInteger(R.styleable.rv_textStyle, 6);
            caps = attrsArray.getBoolean(R.styleable.rv_caps, false);
            boolean noSpacing = attrsArray.getBoolean(R.styleable.rv_noSpacing, false);
            mDrawableWidth = attrsArray.getDimensionPixelSize(R.styleable.rv_compoundDrawableWidth, -1);
            mDrawableHeight = attrsArray.getDimensionPixelSize(R.styleable.rv_compoundDrawableHeight, -1);

            if (mDrawableWidth > 0 || mDrawableHeight > 0) {
                initCompoundDrawableSize();
            }

            setTypeface(TypefaceProvider.getTypeFace(getContext(), TypefaceProvider.Style.valueOf(style)));
            setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        } finally {
            attrsArray.recycle();
        }
    }

    public void initCompoundDrawableSize() {
        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable == null) {
                continue;
            }

            Rect realBounds = drawable.getBounds();
            realBounds.right = realBounds.left + Math.round(mDrawableWidth);
            realBounds.bottom = realBounds.top + Math.round(mDrawableHeight);

            drawable.setBounds(realBounds);
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public void setText(String text) {
        if (text != null) {
            super.setText(text);
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null) {
            if (caps) {
                super.setText(text.toString().toUpperCase(), type);
            } else {
                super.setText(text, type);
            }
        }
    }
}
