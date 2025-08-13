package io.fotoapparat.view;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.fotoapparat.hardware.metering.FocalRequest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: FocusPointSelector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005H&¨\u0006\u0007"}, d2 = {"Lio/fotoapparat/view/FocalPointSelector;", "", "setFocalPointListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Lio/fotoapparat/hardware/metering/FocalRequest;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface FocalPointSelector {
    void setFocalPointListener(Function1<? super FocalRequest, Unit> listener);
}
