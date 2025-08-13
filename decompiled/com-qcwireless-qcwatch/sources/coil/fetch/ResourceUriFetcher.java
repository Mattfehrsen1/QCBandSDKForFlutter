package coil.fetch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.decode.DataSource;
import coil.decode.ImageSources;
import coil.decode.ResourceMetadata;
import coil.fetch.Fetcher;
import coil.request.Options;
import coil.util.Contexts;
import coil.util.DrawableUtils;
import coil.util.Utils;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Okio;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: ResourceUriFetcher.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcoil/fetch/ResourceUriFetcher;", "Lcoil/fetch/Fetcher;", "data", "Landroid/net/Uri;", "options", "Lcoil/request/Options;", "(Landroid/net/Uri;Lcoil/request/Options;)V", "fetch", "Lcoil/fetch/FetchResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "throwInvalidUriException", "", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ResourceUriFetcher implements Fetcher {
    private static final String MIME_TYPE_XML = "text/xml";
    private final Uri data;
    private final Options options;

    public ResourceUriFetcher(Uri uri, Options options) {
        this.data = uri;
        this.options = options;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(Continuation<? super FetchResult> continuation) throws XmlPullParserException, Resources.NotFoundException, PackageManager.NameNotFoundException {
        Resources resourcesForApplication;
        Drawable xmlDrawableCompat;
        String authority = this.data.getAuthority();
        if (authority == null || !(!StringsKt.isBlank(authority))) {
            authority = null;
        }
        if (authority == null) {
            throwInvalidUriException(this.data);
            throw new KotlinNothingValueException();
        }
        String str = (String) CollectionsKt.lastOrNull((List) this.data.getPathSegments());
        Integer intOrNull = str != null ? StringsKt.toIntOrNull(str) : null;
        if (intOrNull == null) {
            throwInvalidUriException(this.data);
            throw new KotlinNothingValueException();
        }
        int iIntValue = intOrNull.intValue();
        Context context = this.options.getContext();
        if (Intrinsics.areEqual(authority, context.getPackageName())) {
            resourcesForApplication = context.getResources();
        } else {
            resourcesForApplication = context.getPackageManager().getResourcesForApplication(authority);
        }
        TypedValue typedValue = new TypedValue();
        resourcesForApplication.getValue(iIntValue, typedValue, true);
        CharSequence charSequence = typedValue.string;
        String mimeTypeFromUrl = Utils.getMimeTypeFromUrl(MimeTypeMap.getSingleton(), charSequence.subSequence(StringsKt.lastIndexOf$default(charSequence, '/', 0, false, 6, (Object) null), charSequence.length()).toString());
        if (Intrinsics.areEqual(mimeTypeFromUrl, MIME_TYPE_XML)) {
            if (Intrinsics.areEqual(authority, context.getPackageName())) {
                xmlDrawableCompat = Contexts.getDrawableCompat(context, iIntValue);
            } else {
                xmlDrawableCompat = Contexts.getXmlDrawableCompat(context, resourcesForApplication, iIntValue);
            }
            BitmapDrawable bitmapDrawable = xmlDrawableCompat;
            boolean zIsVector = Utils.isVector(bitmapDrawable);
            if (zIsVector) {
                bitmapDrawable = new BitmapDrawable(context.getResources(), DrawableUtils.INSTANCE.convertToBitmap(bitmapDrawable, this.options.getConfig(), this.options.getSize(), this.options.getScale(), this.options.getAllowInexactSize()));
            }
            return new DrawableResult(bitmapDrawable, zIsVector, DataSource.DISK);
        }
        TypedValue typedValue2 = new TypedValue();
        return new SourceResult(ImageSources.create(Okio.buffer(Okio.source(resourcesForApplication.openRawResource(iIntValue, typedValue2))), context, new ResourceMetadata(authority, iIntValue, typedValue2.density)), mimeTypeFromUrl, DataSource.DISK);
    }

    private final Void throwInvalidUriException(Uri data) {
        throw new IllegalStateException(Intrinsics.stringPlus("Invalid android.resource URI: ", data));
    }

    /* compiled from: ResourceUriFetcher.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0002H\u0002¨\u0006\r"}, d2 = {"Lcoil/fetch/ResourceUriFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Landroid/net/Uri;", "()V", "create", "Lcoil/fetch/Fetcher;", "data", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "isApplicable", "", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<Uri> {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri data, Options options, ImageLoader imageLoader) {
            if (isApplicable(data)) {
                return new ResourceUriFetcher(data, options);
            }
            return null;
        }

        private final boolean isApplicable(Uri data) {
            return Intrinsics.areEqual(data.getScheme(), "android.resource");
        }
    }
}
