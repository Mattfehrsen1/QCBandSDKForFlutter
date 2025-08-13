package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.qcwireless.qcwatch.R;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class MenstruationWeekBarBinding implements ViewBinding {
    private final View rootView;

    private MenstruationWeekBarBinding(View rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static MenstruationWeekBarBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        Objects.requireNonNull(parent, "parent");
        inflater.inflate(R.layout.menstruation_week_bar, parent);
        return bind(parent);
    }

    public static MenstruationWeekBarBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new MenstruationWeekBarBinding(rootView);
    }
}
