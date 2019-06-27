package com.zhihu.matisse.internal.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by Joshua on 4/5/15.
 */
public final class TypefaceProvider {
    public static final String TYPEFACE_FOLDER = "fonts";
    public static final String TYPEFACE_EXTENSION = ".ttf";

    public enum Style {
        BLACK("Black"),
        BOLD("Bold"),
        BOLD_ITALIC("BoldItalic"),
        HAIRLINE("Hairline"),
        ITALIC("Italic"),
        LIGHT("Light"),
        REGULAR("Regular"),
        CONDENSED_BOLD("CondensedBold");

        private String name;

        Style(String name) {
            this.name = name;
        }

        public static Style valueOf(int style) {
            switch (style) {
                case 0:
                    return BLACK;
                case 1:
                    return BOLD;
                case 2:
                    return BOLD_ITALIC;
                case 3:
                    return HAIRLINE;
                case 4:
                    return ITALIC;
                case 5:
                    return LIGHT;
                case 6:
                    return REGULAR;
                case 7:
                    return CONDENSED_BOLD;
                default:
                    return REGULAR;
            }
        }

        public String getName() {
            return name;
        }

        public String getFullName() {
            return "Lato-" + name;
        }
    }

    private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<String, Typeface>();

    private TypefaceProvider() {
    }

    public static Typeface getTypeFace(Context context, Style style) {
        String fileName = "Lato-" + style.getName();
        Typeface tempTypeface = sTypeFaces.get(fileName);

        try {
            if (tempTypeface == null) {
                String fontPath = TYPEFACE_FOLDER + '/' + fileName + TYPEFACE_EXTENSION;
                tempTypeface = Typeface.createFromAsset(context.getAssets(), fontPath);
                sTypeFaces.put(fileName, tempTypeface);
            }

            return tempTypeface;
        } catch (Exception e) {
            return Typeface.createFromAsset(context.getAssets(), TYPEFACE_FOLDER + "/Lato-Regular" + TYPEFACE_EXTENSION);
        }
    }
}
