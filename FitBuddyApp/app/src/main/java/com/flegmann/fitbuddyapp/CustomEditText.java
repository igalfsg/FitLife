package com.flegmann.fitbuddyapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by songnob on 19/08/2015.
 */
public class CustomEditText extends EditText
{


    public interface IOnTextChanged
    {
        void onTextChanged(View v, String newText);
    }
    private IOnTextChanged iOnTextChanged;


    public void setiOnTextChanged(IOnTextChanged iOnTextChanged)
    {
        this.iOnTextChanged = iOnTextChanged;
    }

    private TextWatcher watcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            String str = s.toString();
            if(iOnTextChanged != null)
            {
                iOnTextChanged.onTextChanged(CustomEditText.this, str);
            }
        }
    };

    public CustomEditText(Context context)
    {
        super(context);
        init();
    }
    public void removeTextWatcher()
    {
        this.removeTextChangedListener(watcher);
    }
    public void addTextWatcher()
    {
        this.addTextChangedListener(watcher);
    }
    public CustomEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        Log.i("su", "Add watcher for " + this.hashCode());
        //this.addTextChangedListener(watcher);
    }
}
