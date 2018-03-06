package com.oldfriends.app.util;

/**
 * lh on 2016/2/23.
 */
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.oldfriends.app.R;

public class KeyboardUtil
{
    private EditText ed;
    private Keyboard k;
    private KeyboardView keyboardView;
    private KeyboardView.OnKeyboardActionListener listener;

    public KeyboardUtil(Activity paramActivity, Context paramContext, EditText paramEditText)
    {
        KeyboardView.OnKeyboardActionListener local1 = new KeyboardView.OnKeyboardActionListener()
        {
            public void onKey(int paramAnonymousInt, int[] paramAnonymousArrayOfInt)
            {
                Editable localEditable = KeyboardUtil.this.ed.getText();
                int i = KeyboardUtil.this.ed.getSelectionStart();
                if (paramAnonymousInt == -5)
                {
                    if ((localEditable != null) && (localEditable.length() > 0) && (i > 0))
                        localEditable.delete(i - 1, i);
                    return;
                }
                if (paramAnonymousInt == 4896)
                {
                    Log.d("KeyboardUtil", " string=" + Character.toString((char)paramAnonymousInt));
                    localEditable.insert(i, ".");
                    return;
                }
                Log.d("KeyboardUtil", " string=" + Character.toString((char)paramAnonymousInt) + ",primaryCode=" + paramAnonymousInt);
                localEditable.insert(i, Character.toString((char)paramAnonymousInt));
            }

            public void onPress(int paramAnonymousInt)
            {
            }

            public void onRelease(int paramAnonymousInt)
            {
            }

            public void onText(CharSequence paramAnonymousCharSequence)
            {
            }

            public void swipeDown()
            {
            }

            public void swipeLeft()
            {
            }

            public void swipeRight()
            {
            }

            public void swipeUp()
            {
            }
        };
        this.listener = local1;
        this.ed = paramEditText;
        Keyboard localKeyboard = new Keyboard(paramContext, R.xml.symbols);
        this.k = localKeyboard;
        this.keyboardView = ((KeyboardView)paramActivity.findViewById(R.id.keyboard_view));
        this.keyboardView.setKeyboard(this.k);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setVisibility(View.VISIBLE);
        this.keyboardView.setOnKeyboardActionListener(this.listener);
    }

    public void showKeyboard()
    {
        int i = this.keyboardView.getVisibility();
        if ((i == 8) || (i == 4))
            this.keyboardView.setVisibility(View.VISIBLE);
    }
}
