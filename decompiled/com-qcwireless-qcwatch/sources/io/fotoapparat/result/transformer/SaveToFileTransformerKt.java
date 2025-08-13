package io.fotoapparat.result.transformer;

import io.fotoapparat.result.Photo;
import java.io.BufferedOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;

/* compiled from: SaveToFileTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"saveImage", "", "input", "Lio/fotoapparat/result/Photo;", "outputStream", "Ljava/io/BufferedOutputStream;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class SaveToFileTransformerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void saveImage(Photo photo, BufferedOutputStream bufferedOutputStream) throws IOException {
        BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
        try {
            BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream2;
            bufferedOutputStream3.write(photo.encodedImage);
            bufferedOutputStream3.flush();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedOutputStream2, null);
        } finally {
        }
    }
}
