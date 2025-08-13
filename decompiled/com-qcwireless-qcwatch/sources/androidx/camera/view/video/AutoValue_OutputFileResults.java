package androidx.camera.view.video;

import android.net.Uri;

/* loaded from: classes.dex */
final class AutoValue_OutputFileResults extends OutputFileResults {
    private final Uri savedUri;

    AutoValue_OutputFileResults(Uri savedUri) {
        this.savedUri = savedUri;
    }

    @Override // androidx.camera.view.video.OutputFileResults
    public Uri getSavedUri() {
        return this.savedUri;
    }

    public String toString() {
        return "OutputFileResults{savedUri=" + this.savedUri + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutputFileResults)) {
            return false;
        }
        Uri uri = this.savedUri;
        Uri savedUri = ((OutputFileResults) o).getSavedUri();
        return uri == null ? savedUri == null : uri.equals(savedUri);
    }

    public int hashCode() {
        Uri uri = this.savedUri;
        return (uri == null ? 0 : uri.hashCode()) ^ 1000003;
    }
}
