package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class EditItemActivity extends ActionBarActivity {
    private EditText etEditItem;
    private int editItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // Set Title for Activity
        getSupportActionBar().setTitle("Edit Item");

        String editItemText = getIntent().getStringExtra("MainItemText");
        editItemIndex = getIntent().getIntExtra("itemIndex", -1);
        etEditItem = (EditText) findViewById(R.id.etEditItem);
        setupReturnKeyListenerForEditText();
        populateEditText(editItemText);
    }

    /*
        Setup listener to call onSave when return key/done button is pressed
        on the keyboard
     */
    private void setupReturnKeyListenerForEditText() {
        etEditItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    onSave(v);
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void populateEditText(String editItemText) {
        etEditItem.setText(editItemText);
        etEditItem.setSelection(editItemText.length());
        etEditItem.requestFocus();

        // Set keyboard to appear after etEditItem is in focus
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void onSave(View v) {
        Intent editedTextData = new Intent();
        editedTextData.putExtra("editedItemText", etEditItem.getText().toString());
        editedTextData.putExtra("editedItemIndex", editItemIndex);
        setResult(RESULT_OK, editedTextData);
        this.finish();
    }
}