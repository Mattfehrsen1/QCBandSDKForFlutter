package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001d A[Catch: IOException -> 0x0061, Exception -> 0x008c, PHI: r4
      0x001d: PHI (r4v5 android.graphics.fonts.FontFamily$Builder) = (r4v3 android.graphics.fonts.FontFamily$Builder), (r4v1 android.graphics.fonts.FontFamily$Builder) binds: [B:20:0x0052, B:9:0x001b] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {Exception -> 0x008c, blocks: (B:3:0x0005, B:6:0x000c, B:7:0x000e, B:10:0x001d, B:28:0x0060, B:27:0x005d, B:32:0x0067, B:36:0x0072, B:39:0x0077), top: B:43:0x0005 }] */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r11, android.os.CancellationSignal r12, androidx.core.provider.FontsContractCompat.FontInfo[] r13, int r14) throws java.io.IOException {
        /*
            r10 = this;
            android.content.ContentResolver r11 = r11.getContentResolver()
            r0 = 0
            int r1 = r13.length     // Catch: java.lang.Exception -> L8c
            r2 = 0
            r4 = r0
            r3 = 0
        L9:
            r5 = 1
            if (r3 >= r1) goto L64
            r6 = r13[r3]     // Catch: java.lang.Exception -> L8c
            android.net.Uri r7 = r6.getUri()     // Catch: java.io.IOException -> L61 java.lang.Exception -> L8c
            java.lang.String r8 = "r"
            android.os.ParcelFileDescriptor r7 = r11.openFileDescriptor(r7, r8, r12)     // Catch: java.io.IOException -> L61 java.lang.Exception -> L8c
            if (r7 != 0) goto L21
            if (r7 == 0) goto L61
        L1d:
            r7.close()     // Catch: java.io.IOException -> L61 java.lang.Exception -> L8c
            goto L61
        L21:
            android.graphics.fonts.Font$Builder r8 = new android.graphics.fonts.Font$Builder     // Catch: java.lang.Throwable -> L55
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L55
            int r9 = r6.getWeight()     // Catch: java.lang.Throwable -> L55
            android.graphics.fonts.Font$Builder r8 = r8.setWeight(r9)     // Catch: java.lang.Throwable -> L55
            boolean r9 = r6.isItalic()     // Catch: java.lang.Throwable -> L55
            if (r9 == 0) goto L35
            goto L36
        L35:
            r5 = 0
        L36:
            android.graphics.fonts.Font$Builder r5 = r8.setSlant(r5)     // Catch: java.lang.Throwable -> L55
            int r6 = r6.getTtcIndex()     // Catch: java.lang.Throwable -> L55
            android.graphics.fonts.Font$Builder r5 = r5.setTtcIndex(r6)     // Catch: java.lang.Throwable -> L55
            android.graphics.fonts.Font r5 = r5.build()     // Catch: java.lang.Throwable -> L55
            if (r4 != 0) goto L4f
            android.graphics.fonts.FontFamily$Builder r6 = new android.graphics.fonts.FontFamily$Builder     // Catch: java.lang.Throwable -> L55
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L55
            r4 = r6
            goto L52
        L4f:
            r4.addFont(r5)     // Catch: java.lang.Throwable -> L55
        L52:
            if (r7 == 0) goto L61
            goto L1d
        L55:
            r5 = move-exception
            if (r7 == 0) goto L60
            r7.close()     // Catch: java.lang.Throwable -> L5c
            goto L60
        L5c:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch: java.io.IOException -> L61 java.lang.Exception -> L8c
        L60:
            throw r5     // Catch: java.io.IOException -> L61 java.lang.Exception -> L8c
        L61:
            int r3 = r3 + 1
            goto L9
        L64:
            if (r4 != 0) goto L67
            return r0
        L67:
            android.graphics.fonts.FontStyle r11 = new android.graphics.fonts.FontStyle     // Catch: java.lang.Exception -> L8c
            r12 = r14 & 1
            if (r12 == 0) goto L70
            r12 = 700(0x2bc, float:9.81E-43)
            goto L72
        L70:
            r12 = 400(0x190, float:5.6E-43)
        L72:
            r13 = r14 & 2
            if (r13 == 0) goto L77
            r2 = 1
        L77:
            r11.<init>(r12, r2)     // Catch: java.lang.Exception -> L8c
            android.graphics.Typeface$CustomFallbackBuilder r12 = new android.graphics.Typeface$CustomFallbackBuilder     // Catch: java.lang.Exception -> L8c
            android.graphics.fonts.FontFamily r13 = r4.build()     // Catch: java.lang.Exception -> L8c
            r12.<init>(r13)     // Catch: java.lang.Exception -> L8c
            android.graphics.Typeface$CustomFallbackBuilder r11 = r12.setStyle(r11)     // Catch: java.lang.Exception -> L8c
            android.graphics.Typeface r11 = r11.build()     // Catch: java.lang.Exception -> L8c
            return r11
        L8c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) throws IOException {
        try {
            FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
            int length = entries.length;
            FontFamily.Builder builder = null;
            int i2 = 0;
            while (true) {
                int i3 = 1;
                if (i2 >= length) {
                    break;
                }
                FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = entries[i2];
                try {
                    Font.Builder weight = new Font.Builder(resources, fontFileResourceEntry.getResourceId()).setWeight(fontFileResourceEntry.getWeight());
                    if (!fontFileResourceEntry.isItalic()) {
                        i3 = 0;
                    }
                    Font fontBuild = weight.setSlant(i3).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (IOException unused) {
                }
                i2++;
            }
            if (builder == null) {
                return null;
            }
            return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? TypedValues.TransitionType.TYPE_DURATION : 400, (i & 2) != 0 ? 1 : 0)).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) throws IOException {
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
