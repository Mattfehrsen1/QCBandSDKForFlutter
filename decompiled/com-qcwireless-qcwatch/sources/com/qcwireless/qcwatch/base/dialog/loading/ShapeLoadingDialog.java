package com.qcwireless.qcwatch.base.dialog.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.LoadingView;

/* loaded from: classes3.dex */
public class ShapeLoadingDialog extends AlertDialog {
    private Builder mBuilder;
    private LoadingView mLoadingView;

    private ShapeLoadingDialog(Builder builder) {
        super(builder.mContext, R.style.loading_dialog);
        this.mBuilder = builder;
        setCancelable(builder.mCancelable);
        setCanceledOnTouchOutside(this.mBuilder.mCanceledOnTouchOutside);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
        LoadingView loadingView = (LoadingView) findViewById(R.id.loadView);
        this.mLoadingView = loadingView;
        loadingView.setLoadingText(this.mBuilder.mLoadText);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.qcwireless.qcwatch.base.dialog.loading.ShapeLoadingDialog.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                ShapeLoadingDialog.this.mLoadingView.setVisibility(8);
            }
        });
    }

    public void setLoadingText(String text) {
        LoadingView loadingView = this.mLoadingView;
        if (loadingView != null) {
            loadingView.setLoadingText(text);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.mLoadingView.setVisibility(0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = this.mBuilder.screenHeight - this.mBuilder.statusBar;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(attributes);
    }

    public Builder getBuilder() {
        return this.mBuilder;
    }

    public static class Builder {
        private Context mContext;
        private CharSequence mLoadText;
        private int screenHeight;
        private int statusBar;
        private int mDelay = 1;
        private boolean mCancelable = true;
        private boolean mCanceledOnTouchOutside = true;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder delay(int delay) {
            this.mDelay = delay;
            return this;
        }

        public Builder loadText(CharSequence loadText) {
            this.mLoadText = loadText;
            return this;
        }

        public Builder loadText(int resId) {
            this.mLoadText = this.mContext.getString(resId);
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            this.mCanceledOnTouchOutside = cancelable;
            return this;
        }

        public Builder setScreenAndStatus(int height, int bar) {
            this.screenHeight = height;
            this.statusBar = bar;
            return this;
        }

        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public ShapeLoadingDialog build() {
            return new ShapeLoadingDialog(this);
        }

        public ShapeLoadingDialog show() {
            ShapeLoadingDialog shapeLoadingDialogBuild = build();
            shapeLoadingDialogBuild.show();
            return shapeLoadingDialogBuild;
        }
    }
}
