package br.tbm.github.api.shared.ui.components;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class CustomMontserratRegularTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomMontserratRegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomMontserratRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomMontserratRegularTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/MontserratRegular.ttf"));
    }
}
