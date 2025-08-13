package io.fotoapparat.selector;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: Selectors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\t\u001a^\u0010\u0000\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u0004\"\b\b\u0000\u0010\u0003*\u00020\u00052\u001f\u0010\u0006\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\b0\u0001\u001af\u0010\t\u001a\u0015\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\u0001¢\u0006\u0002\b\u0004\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b28\u0010\f\u001a\u001d\u0012\u0019\b\u0001\u0012\u0015\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\u0001¢\u0006\u0002\b\u00040\r\"\u0015\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0002\u0010\u000e\u001a/\u0010\u000f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u0004\"\u000e\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0010\u001a/\u0010\u0011\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u0004\"\u000e\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0010\u001a%\u0010\u0012\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u0004\"\u0004\b\u0000\u0010\u0003\u001a2\u0010\u0013\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001¢\u0006\u0002\b\u0004\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0014\u001a\u0002H\u0003¢\u0006\u0002\u0010\u0015\u001a?\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0003*\u00020\u0005\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00030\r2\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u00170\u0001H\u0002¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"filtered", "Lkotlin/Function1;", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/ExtensionFunctionType;", "", "selector", "predicate", "", "firstAvailable", "Input", "Output", "functions", "", "([Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "highest", "", "lowest", "nothing", "single", "preference", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "findNonNull", "R", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class SelectorsKt {
    public static final <T> Function1<Iterable<? extends T>, T> nothing() {
        return new Function1() { // from class: io.fotoapparat.selector.SelectorsKt.nothing.1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Iterable<? extends T> receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                return null;
            }
        };
    }

    public static final <T> Function1<Iterable<? extends T>, T> single(final T t) {
        return new Function1<Iterable<? extends T>, T>() { // from class: io.fotoapparat.selector.SelectorsKt.single.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Iterable<? extends T> receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                for (T t2 : receiver$0) {
                    if (Intrinsics.areEqual(t2, t)) {
                        return t2;
                    }
                }
                return null;
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Selectors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "", "p1", "", "invoke", "(Ljava/lang/Iterable;)Ljava/lang/Comparable;"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.selector.SelectorsKt$highest$1, reason: invalid class name and case insensitive filesystem */
    static final class C07061<T> extends FunctionReference implements Function1<Iterable<? extends T>, T> {
        public static final C07061 INSTANCE = new C07061();

        C07061() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "max";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinPackage(CollectionsKt.class, "fotoapparat_release");
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "max(Ljava/lang/Iterable;)Ljava/lang/Comparable;";
        }

        /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Iterable<+TT;>;)TT; */
        @Override // kotlin.jvm.functions.Function1
        public final Comparable invoke(Iterable p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            return CollectionsKt.max(p1);
        }
    }

    public static final <T extends Comparable<? super T>> Function1<Iterable<? extends T>, T> highest() {
        return C07061.INSTANCE;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Selectors.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "", "p1", "", "invoke", "(Ljava/lang/Iterable;)Ljava/lang/Comparable;"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.selector.SelectorsKt$lowest$1, reason: invalid class name and case insensitive filesystem */
    static final class C07071<T> extends FunctionReference implements Function1<Iterable<? extends T>, T> {
        public static final C07071 INSTANCE = new C07071();

        C07071() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "min";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinPackage(CollectionsKt.class, "fotoapparat_release");
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "min(Ljava/lang/Iterable;)Ljava/lang/Comparable;";
        }

        /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Iterable<+TT;>;)TT; */
        @Override // kotlin.jvm.functions.Function1
        public final Comparable invoke(Iterable p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            return CollectionsKt.min(p1);
        }
    }

    public static final <T extends Comparable<? super T>> Function1<Iterable<? extends T>, T> lowest() {
        return C07071.INSTANCE;
    }

    @SafeVarargs
    public static final <Input, Output> Function1<Input, Output> firstAvailable(final Function1<? super Input, ? extends Output>... functions) {
        Intrinsics.checkParameterIsNotNull(functions, "functions");
        return new Function1<Input, Output>() { // from class: io.fotoapparat.selector.SelectorsKt.firstAvailable.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Output invoke(final Input input) {
                return (Output) SelectorsKt.findNonNull(functions, new Function1<Function1<? super Input, ? extends Output>, Output>() { // from class: io.fotoapparat.selector.SelectorsKt.firstAvailable.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Output invoke(Function1<? super Input, ? extends Output> it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it.invoke((Object) input);
                    }
                });
            }
        };
    }

    public static final <T> Function1<Iterable<? extends T>, T> filtered(final Function1<? super Iterable<? extends T>, ? extends T> selector, final Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return new Function1<Iterable<? extends T>, T>() { // from class: io.fotoapparat.selector.SelectorsKt.filtered.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Iterable<? extends T> receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                Function1 function1 = selector;
                Function1 function12 = predicate;
                ArrayList arrayList = new ArrayList();
                for (T t : receiver$0) {
                    if (((Boolean) function12.invoke(t)).booleanValue()) {
                        arrayList.add(t);
                    }
                }
                return (T) function1.invoke(arrayList);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, R> R findNonNull(T[] tArr, Function1<? super T, ? extends R> function1) {
        for (T t : tArr) {
            R rInvoke = function1.invoke(t);
            if (rInvoke != null) {
                return rInvoke;
            }
        }
        return null;
    }
}
