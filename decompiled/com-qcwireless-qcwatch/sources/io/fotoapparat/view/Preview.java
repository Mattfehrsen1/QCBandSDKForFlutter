package io.fotoapparat.view;

import android.graphics.SurfaceTexture;
import android.view.SurfaceHolder;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Preview.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/fotoapparat/view/Preview;", "", "()V", "Surface", "Texture", "Lio/fotoapparat/view/Preview$Texture;", "Lio/fotoapparat/view/Preview$Surface;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class Preview {
    private Preview() {
    }

    public /* synthetic */ Preview(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: Preview.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/fotoapparat/view/Preview$Texture;", "Lio/fotoapparat/view/Preview;", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "(Landroid/graphics/SurfaceTexture;)V", "getSurfaceTexture", "()Landroid/graphics/SurfaceTexture;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final /* data */ class Texture extends Preview {
        private final SurfaceTexture surfaceTexture;

        public static /* synthetic */ Texture copy$default(Texture texture, SurfaceTexture surfaceTexture, int i, Object obj) {
            if ((i & 1) != 0) {
                surfaceTexture = texture.surfaceTexture;
            }
            return texture.copy(surfaceTexture);
        }

        /* renamed from: component1, reason: from getter */
        public final SurfaceTexture getSurfaceTexture() {
            return this.surfaceTexture;
        }

        public final Texture copy(SurfaceTexture surfaceTexture) {
            Intrinsics.checkParameterIsNotNull(surfaceTexture, "surfaceTexture");
            return new Texture(surfaceTexture);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Texture) && Intrinsics.areEqual(this.surfaceTexture, ((Texture) other).surfaceTexture);
            }
            return true;
        }

        public int hashCode() {
            SurfaceTexture surfaceTexture = this.surfaceTexture;
            if (surfaceTexture != null) {
                return surfaceTexture.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Texture(surfaceTexture=" + this.surfaceTexture + ")";
        }

        public final SurfaceTexture getSurfaceTexture() {
            return this.surfaceTexture;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Texture(SurfaceTexture surfaceTexture) {
            super(null);
            Intrinsics.checkParameterIsNotNull(surfaceTexture, "surfaceTexture");
            this.surfaceTexture = surfaceTexture;
        }
    }

    /* compiled from: Preview.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/fotoapparat/view/Preview$Surface;", "Lio/fotoapparat/view/Preview;", "surfaceHolder", "Landroid/view/SurfaceHolder;", "(Landroid/view/SurfaceHolder;)V", "getSurfaceHolder", "()Landroid/view/SurfaceHolder;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final /* data */ class Surface extends Preview {
        private final SurfaceHolder surfaceHolder;

        public static /* synthetic */ Surface copy$default(Surface surface, SurfaceHolder surfaceHolder, int i, Object obj) {
            if ((i & 1) != 0) {
                surfaceHolder = surface.surfaceHolder;
            }
            return surface.copy(surfaceHolder);
        }

        /* renamed from: component1, reason: from getter */
        public final SurfaceHolder getSurfaceHolder() {
            return this.surfaceHolder;
        }

        public final Surface copy(SurfaceHolder surfaceHolder) {
            Intrinsics.checkParameterIsNotNull(surfaceHolder, "surfaceHolder");
            return new Surface(surfaceHolder);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Surface) && Intrinsics.areEqual(this.surfaceHolder, ((Surface) other).surfaceHolder);
            }
            return true;
        }

        public int hashCode() {
            SurfaceHolder surfaceHolder = this.surfaceHolder;
            if (surfaceHolder != null) {
                return surfaceHolder.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Surface(surfaceHolder=" + this.surfaceHolder + ")";
        }

        public final SurfaceHolder getSurfaceHolder() {
            return this.surfaceHolder;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Surface(SurfaceHolder surfaceHolder) {
            super(null);
            Intrinsics.checkParameterIsNotNull(surfaceHolder, "surfaceHolder");
            this.surfaceHolder = surfaceHolder;
        }
    }
}
