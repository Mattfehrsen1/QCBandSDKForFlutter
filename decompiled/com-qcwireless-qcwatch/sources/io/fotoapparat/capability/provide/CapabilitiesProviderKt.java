package io.fotoapparat.capability.provide;

import android.hardware.Camera;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.SupportedParameters;
import io.fotoapparat.parameter.Zoom;
import io.fotoapparat.parameter.camera.convert.AntiBandingConverterKt;
import io.fotoapparat.parameter.camera.convert.FlashConverterKt;
import io.fotoapparat.parameter.camera.convert.FocusModeConverterKt;
import io.fotoapparat.parameter.camera.convert.FpsRangeConverterKt;
import io.fotoapparat.parameter.camera.convert.ResolutionConverterKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: CapabilitiesProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u0002H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0007H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u000bH\u0002\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\f\u0012\b\u0012\u00060\u000fR\u00020\n0\u000eH\u0002¨\u0006\u0010"}, d2 = {"extract", "", "Parameter", "", "Code", "", "converter", "Lkotlin/Function1;", "getCapabilities", "Lio/fotoapparat/capability/Capabilities;", "Landroid/hardware/Camera;", "Lio/fotoapparat/parameter/SupportedParameters;", "mapSizes", "Lio/fotoapparat/parameter/Resolution;", "", "Landroid/hardware/Camera$Size;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CapabilitiesProviderKt {
    public static final Capabilities getCapabilities(Camera receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Camera.Parameters parameters = receiver$0.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "parameters");
        return getCapabilities(new SupportedParameters(parameters));
    }

    private static final Capabilities getCapabilities(SupportedParameters supportedParameters) {
        Zoom supportedZoom = supportedParameters.getSupportedZoom();
        Set setExtract = extract(supportedParameters.getFlashModes(), new Function1<String, Flash>() { // from class: io.fotoapparat.capability.provide.CapabilitiesProviderKt.getCapabilities.1
            @Override // kotlin.jvm.functions.Function1
            public final Flash invoke(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return FlashConverterKt.toFlash(it);
            }
        });
        Set setExtract2 = extract(supportedParameters.getFocusModes(), new Function1<String, FocusMode>() { // from class: io.fotoapparat.capability.provide.CapabilitiesProviderKt.getCapabilities.2
            @Override // kotlin.jvm.functions.Function1
            public final FocusMode invoke(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return FocusModeConverterKt.toFocusMode(it);
            }
        });
        int maxNumFocusAreas = supportedParameters.getMaxNumFocusAreas();
        return new Capabilities(supportedZoom, setExtract, setExtract2, supportedParameters.getSupportedSmoothZoom(), maxNumFocusAreas, supportedParameters.getMaxNumMeteringAreas(), supportedParameters.getJpegQualityRange(), supportedParameters.getExposureCompensationRange(), extract(supportedParameters.getSupportedPreviewFpsRanges(), new Function1<int[], FpsRange>() { // from class: io.fotoapparat.capability.provide.CapabilitiesProviderKt.getCapabilities.4
            @Override // kotlin.jvm.functions.Function1
            public final FpsRange invoke(int[] it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return FpsRangeConverterKt.toFpsRange(it);
            }
        }), extract(supportedParameters.getSupportedAutoBandingModes(), AnonymousClass3.INSTANCE), mapSizes(supportedParameters.getPictureResolutions()), mapSizes(supportedParameters.getPreviewResolutions()), CollectionsKt.toSet(supportedParameters.getSensorSensitivities()));
    }

    /* compiled from: CapabilitiesProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/fotoapparat/parameter/AntiBandingMode;", "p1", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.capability.provide.CapabilitiesProviderKt$getCapabilities$3, reason: invalid class name */
    static final class AnonymousClass3 extends FunctionReference implements Function1<String, AntiBandingMode> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "toAntiBandingMode";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinPackage(AntiBandingConverterKt.class, "fotoapparat_release");
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "toAntiBandingMode(Ljava/lang/String;)Lio/fotoapparat/parameter/AntiBandingMode;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final AntiBandingMode invoke(String p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            return AntiBandingConverterKt.toAntiBandingMode(p1);
        }
    }

    private static final <Parameter, Code> Set<Parameter> extract(List<? extends Code> list, Function1<? super Code, ? extends Parameter> function1) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Parameter parameterInvoke = function1.invoke((Object) it.next());
            if (parameterInvoke != null) {
                arrayList.add(parameterInvoke);
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    private static final Set<Resolution> mapSizes(Collection<? extends Camera.Size> collection) {
        Collection<? extends Camera.Size> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(ResolutionConverterKt.toResolution((Camera.Size) it.next()));
        }
        return CollectionsKt.toSet(arrayList);
    }
}
