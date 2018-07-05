package com.outsoucre.leruyn.baseproject.common.widget;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class FontCache {
    private static HashMap<String, Typeface> sFontCache = new HashMap<>();

    private FontCache() {
    }

    public static Typeface getTypeFace(String fontName, Context context) {
        Typeface typeface = sFontCache.get(fontName);
        if (typeface == null) {
            try {
                if (!fontName.endsWith(".ttf")) {
                    fontName = fontName + ".ttf";
                }
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            } catch (Exception e) {
                return null;
            }
            sFontCache.put(fontName.replace(".ttf", ""), typeface);
        }
        return typeface;
    }
}
