package com.qcwireless.qcwatch.ui.activity.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class TestActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        this.btn1 = (Button) findViewById(R.id.btn_1);
        this.btn2 = (Button) findViewById(R.id.btn_2);
        this.btn1.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.test.TestActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                XLog.i("====");
            }
        });
        this.btn2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.test.TestActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}
