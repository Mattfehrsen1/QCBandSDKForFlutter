package io.fotoapparat.exception.camera;

import io.fotoapparat.parameter.Parameter;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;

/* compiled from: UnsupportedConfigurationException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\b\u0016\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007B\u001b\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lio/fotoapparat/exception/camera/UnsupportedConfigurationException;", "Lio/fotoapparat/exception/camera/CameraException;", "klass", "Ljava/lang/Class;", "Lio/fotoapparat/parameter/Parameter;", "supportedParameters", "", "(Ljava/lang/Class;Ljava/util/Collection;)V", "configurationName", "", "supportedRange", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/String;Lkotlin/ranges/ClosedRange;)V", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UnsupportedConfigurationException extends CameraException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedConfigurationException(Class<? extends Parameter> klass, Collection<? extends Parameter> supportedParameters) {
        super(klass.getSimpleName() + " configuration selector couldn't select a value. Supported parameters: " + supportedParameters, null, 2, null);
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        Intrinsics.checkParameterIsNotNull(supportedParameters, "supportedParameters");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedConfigurationException(String configurationName, ClosedRange<?> supportedRange) {
        super(configurationName + " configuration selector couldn't select a value. Supported parameters from: " + supportedRange.getStart() + " to " + supportedRange.getEndInclusive() + '.', null, 2, null);
        Intrinsics.checkParameterIsNotNull(configurationName, "configurationName");
        Intrinsics.checkParameterIsNotNull(supportedRange, "supportedRange");
    }
}
