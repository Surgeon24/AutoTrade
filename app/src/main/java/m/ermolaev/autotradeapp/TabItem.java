package m.ermolaev.autotradeapp;


import com.google.android.material.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

//TODO(b/76413401): make class final after the widget migration
public class TabItem extends View {
    //TODO(b/76413401): make package private after the widget migration
    public final CharSequence text;
    //TODO(b/76413401): make package private after the widget migration
    public final Drawable icon;
    //TODO(b/76413401): make package private after the widget migration
    public final int customLayout;

    public TabItem(Context context) {
        this(context, null);
    }

    @SuppressLint("RestrictedApi")
    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TintTypedArray a =
                TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TabItem);
        text = a.getText(R.styleable.TabItem_android_text);
        icon = a.getDrawable(R.styleable.TabItem_android_icon);
        customLayout = a.getResourceId(R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }
}