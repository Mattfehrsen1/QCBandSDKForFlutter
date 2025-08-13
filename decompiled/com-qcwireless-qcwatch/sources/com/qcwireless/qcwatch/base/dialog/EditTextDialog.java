package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class EditTextDialog extends Dialog implements View.OnClickListener {
    private TextView mBtnSave;
    private int mMaxLength;
    private EditText mNameEdt;
    private String mTextString;
    public Window mWindow;
    private OnTextConfirmListener onConfirmListener;
    private Handler uiHandler;

    public interface OnTextConfirmListener {
        void OnConfirm(String text);
    }

    public boolean getDimEnabled() {
        return true;
    }

    public int getGravity() {
        return 81;
    }

    public int getHeight() {
        return -2;
    }

    public int getWidth() {
        return -1;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.layout_dialog_editview);
        Window window = getWindow();
        this.mWindow = window;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        this.mWindow.setWindowAnimations(R.style.DialogAnimation);
        if (getDimEnabled()) {
            this.mWindow.addFlags(2);
        } else {
            this.mWindow.clearFlags(2);
        }
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        attributes.width = getWidth();
        attributes.height = getHeight();
        attributes.gravity = getGravity();
        onWindowAttributesChanged(attributes);
        initView();
    }

    private void initView() {
        this.mBtnSave = (TextView) findViewById(R.id.btn_save);
        this.mNameEdt = (EditText) findViewById(R.id.name_edt);
        this.mBtnSave.setOnClickListener(this);
        this.mNameEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mMaxLength)});
        this.mNameEdt.setText(this.mTextString);
        EditText editText = this.mNameEdt;
        editText.setSelection(editText.length());
    }

    public EditTextDialog(Context context, String text) {
        super(context);
        this.mMaxLength = 20;
        this.uiHandler = new Handler() { // from class: com.qcwireless.qcwatch.base.dialog.EditTextDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                EditTextDialog.this.mNameEdt.setFocusable(true);
                EditTextDialog.this.mNameEdt.setFocusableInTouchMode(true);
                EditTextDialog.this.mNameEdt.requestFocus();
                ((InputMethodManager) EditTextDialog.this.mNameEdt.getContext().getSystemService("input_method")).showSoftInput(EditTextDialog.this.mNameEdt, 0);
            }
        };
        this.mTextString = (text == null || text.length() == 0) ? "hello" : text;
    }

    public EditTextDialog(Context context) {
        super(context);
        this.mMaxLength = 20;
        this.uiHandler = new Handler() { // from class: com.qcwireless.qcwatch.base.dialog.EditTextDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                EditTextDialog.this.mNameEdt.setFocusable(true);
                EditTextDialog.this.mNameEdt.setFocusableInTouchMode(true);
                EditTextDialog.this.mNameEdt.requestFocus();
                ((InputMethodManager) EditTextDialog.this.mNameEdt.getContext().getSystemService("input_method")).showSoftInput(EditTextDialog.this.mNameEdt, 0);
            }
        };
    }

    public EditTextDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mMaxLength = 20;
        this.uiHandler = new Handler() { // from class: com.qcwireless.qcwatch.base.dialog.EditTextDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                EditTextDialog.this.mNameEdt.setFocusable(true);
                EditTextDialog.this.mNameEdt.setFocusableInTouchMode(true);
                EditTextDialog.this.mNameEdt.requestFocus();
                ((InputMethodManager) EditTextDialog.this.mNameEdt.getContext().getSystemService("input_method")).showSoftInput(EditTextDialog.this.mNameEdt, 0);
            }
        };
    }

    protected EditTextDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mMaxLength = 20;
        this.uiHandler = new Handler() { // from class: com.qcwireless.qcwatch.base.dialog.EditTextDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                EditTextDialog.this.mNameEdt.setFocusable(true);
                EditTextDialog.this.mNameEdt.setFocusableInTouchMode(true);
                EditTextDialog.this.mNameEdt.requestFocus();
                ((InputMethodManager) EditTextDialog.this.mNameEdt.getContext().getSystemService("input_method")).showSoftInput(EditTextDialog.this.mNameEdt, 0);
            }
        };
    }

    public void setOnTextConfirmListener(OnTextConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String strTrim = this.mNameEdt.getText().toString().trim();
            this.mTextString = strTrim;
            String strReplaceAll = strTrim.replaceAll("\n", "");
            this.mTextString = strReplaceAll;
            if (strReplaceAll.length() <= 0) {
                return;
            }
            OnTextConfirmListener onTextConfirmListener = this.onConfirmListener;
            if (onTextConfirmListener != null) {
                onTextConfirmListener.OnConfirm(this.mTextString);
            }
            dismiss();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            this.uiHandler.sendEmptyMessageDelayed(0, 500L);
        }
    }
}
