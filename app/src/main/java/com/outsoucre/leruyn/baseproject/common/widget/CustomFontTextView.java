package com.outsoucre.leruyn.baseproject.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.outsoucre.leruyn.baseproject.R;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
        String fontName = styledAttributes.getString(R.styleable.CustomFontTextView_font);
        Typeface customFont = FontCache.getTypeFace(fontName, context);
        setTypeface(customFont);
        styledAttributes.recycle();
    }
}