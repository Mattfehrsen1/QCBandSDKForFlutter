package com.app.watch.keeping.pix;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ManagerExtKt;
import com.google.android.material.badge.BadgeDrawable;
import kotlin.Metadata;

/* compiled from: OnePixActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014J\b\u0010\t\u001a\u00020\u0004H\u0014¨\u0006\n"}, d2 = {"Lcom/app/watch/keeping/pix/OnePixActivity;", "Landroid/app/Activity;", "()V", "finish", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OnePixActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CactusExtKt.log("one pix is created");
        overridePendingTransition(0, 0);
        getWindow().setGravity(BadgeDrawable.TOP_START);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = 1;
        attributes.width = 1;
        window.setAttributes(attributes);
        CactusExtKt.setOnePix(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (ManagerExtKt.isScreenOn(this)) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CactusExtKt.finishOnePix();
        CactusExtKt.log("one pix is destroyed");
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
