package com.oldfriends.app.view;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * lh on 2015/10/13.
 */
public class CustomListEditText extends EditText{
    private List<TextWatcher> watchers = new ArrayList<TextWatcher>();

    public CustomListEditText(Context context) {
        super(context);
    }

    public CustomListEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        watchers.add(watcher);
        super.addTextChangedListener(watcher);
    }

    public void removeTextChangedListener() {
        for (int i = 0; i < watchers.size(); i++) {
            removeTextChangedListener(watchers.get(i));
        }
    }
}
