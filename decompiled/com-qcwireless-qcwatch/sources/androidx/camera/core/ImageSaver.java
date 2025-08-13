package androidx.camera.core;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
final class ImageSaver implements Runnable {
    private static final int COPY_BUFFER_SIZE = 1024;
    private static final int NOT_PENDING = 0;
    private static final int PENDING = 1;
    private static final String TAG = "ImageSaver";
    private static final String TEMP_FILE_PREFIX = "CameraX";
    private static final String TEMP_FILE_SUFFIX = ".tmp";
    private final OnImageSavedCallback mCallback;
    private final ImageProxy mImage;
    private final int mOrientation;
    private final ImageCapture.OutputFileOptions mOutputFileOptions;
    private final Executor mSequentialIoExecutor;
    private final Executor mUserCallbackExecutor;

    public interface OnImageSavedCallback {
        void onError(SaveError saveError, String message, Throwable cause);

        void onImageSaved(ImageCapture.OutputFileResults outputFileResults);
    }

    public enum SaveError {
        FILE_IO_FAILED,
        ENCODE_FAILED,
        CROP_FAILED,
        UNKNOWN
    }

    ImageSaver(ImageProxy image, ImageCapture.OutputFileOptions outputFileOptions, int orientation, Executor userCallbackExecutor, Executor sequentialIoExecutor, OnImageSavedCallback callback) {
        this.mImage = image;
        this.mOutputFileOptions = outputFileOptions;
        this.mOrientation = orientation;
        this.mCallback = callback;
        this.mUserCallbackExecutor = userCallbackExecutor;
        this.mSequentialIoExecutor = sequentialIoExecutor;
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        final File fileSaveImageToTempFile = saveImageToTempFile();
        if (fileSaveImageToTempFile != null) {
            this.mSequentialIoExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m90lambda$run$0$androidxcameracoreImageSaver(fileSaveImageToTempFile);
                }
            });
        }
    }

    private File saveImageToTempFile() throws IOException {
        File fileCreateTempFile;
        SaveError saveError;
        String str;
        Throwable th;
        try {
            if (isSaveToFile()) {
                fileCreateTempFile = new File(this.mOutputFileOptions.getFile().getParent(), TEMP_FILE_PREFIX + UUID.randomUUID().toString() + TEMP_FILE_SUFFIX);
            } else {
                fileCreateTempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
            }
            try {
                ImageProxy imageProxy = this.mImage;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                    try {
                        fileOutputStream.write(ImageUtil.imageToJpegByteArray(this.mImage));
                        Exif exifCreateFromFile = Exif.createFromFile(fileCreateTempFile);
                        exifCreateFromFile.attachTimestamp();
                        if (new ExifRotationAvailability().shouldUseExifOrientation(this.mImage)) {
                            ByteBuffer buffer = this.mImage.getPlanes()[0].getBuffer();
                            buffer.rewind();
                            byte[] bArr = new byte[buffer.capacity()];
                            buffer.get(bArr);
                            exifCreateFromFile.setOrientation(Exif.createFromInputStream(new ByteArrayInputStream(bArr)).getOrientation());
                        } else {
                            exifCreateFromFile.rotate(this.mOrientation);
                        }
                        ImageCapture.Metadata metadata = this.mOutputFileOptions.getMetadata();
                        if (metadata.isReversedHorizontal()) {
                            exifCreateFromFile.flipHorizontally();
                        }
                        if (metadata.isReversedVertical()) {
                            exifCreateFromFile.flipVertically();
                        }
                        if (metadata.getLocation() != null) {
                            exifCreateFromFile.attachLocation(this.mOutputFileOptions.getMetadata().getLocation());
                        }
                        exifCreateFromFile.save();
                        fileOutputStream.close();
                        if (imageProxy != null) {
                            imageProxy.close();
                        }
                        th = null;
                        saveError = null;
                        str = null;
                    } finally {
                    }
                } catch (Throwable th2) {
                    if (imageProxy != null) {
                        try {
                            imageProxy.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (ImageUtil.CodecFailedException e) {
                int i = AnonymousClass1.$SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[e.getFailureType().ordinal()];
                if (i == 1) {
                    saveError = SaveError.ENCODE_FAILED;
                    str = "Failed to encode mImage";
                    th = e;
                } else if (i == 2) {
                    saveError = SaveError.CROP_FAILED;
                    str = "Failed to crop mImage";
                    th = e;
                } else {
                    saveError = SaveError.UNKNOWN;
                    str = "Failed to transcode mImage";
                    th = e;
                }
            } catch (IOException e2) {
                e = e2;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                th = e;
            } catch (IllegalArgumentException e3) {
                e = e3;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                th = e;
            }
            if (saveError == null) {
                return fileCreateTempFile;
            }
            postError(saveError, str, th);
            fileCreateTempFile.delete();
            return null;
        } catch (IOException e4) {
            postError(SaveError.FILE_IO_FAILED, "Failed to create temp file", e4);
            return null;
        }
    }

    /* renamed from: androidx.camera.core.ImageSaver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType;

        static {
            int[] iArr = new int[ImageUtil.CodecFailedException.FailureType.values().length];
            $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType = iArr;
            try {
                iArr[ImageUtil.CodecFailedException.FailureType.ENCODE_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[ImageUtil.CodecFailedException.FailureType.DECODE_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[ImageUtil.CodecFailedException.FailureType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009e  */
    /* renamed from: copyTempFileToDestination, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m90lambda$run$0$androidxcameracoreImageSaver(java.io.File r6) {
        /*
            r5 = this;
            androidx.core.util.Preconditions.checkNotNull(r6)
            r0 = 0
            boolean r1 = r5.isSaveToMediaStore()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L54
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentValues r1 = r1.getContentValues()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L1e
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentValues r2 = r2.getContentValues()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            goto L23
        L1e:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r1.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
        L23:
            r2 = 1
            r5.setContentValuePending(r1, r2)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r3 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.net.Uri r3 = r3.getSaveCollection()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            android.net.Uri r1 = r2.insert(r3, r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 != 0) goto L3f
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to insert URI."
            goto L95
        L3f:
            boolean r2 = r5.copyTempFileToUri(r6, r1)     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            if (r2 != 0) goto L4a
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to save to URI."
            goto L4c
        L4a:
            r2 = r0
            r3 = r2
        L4c:
            r5.setUriNotPending(r1)     // Catch: java.lang.IllegalArgumentException -> L50 java.io.IOException -> L52 java.lang.Throwable -> L89
            goto L95
        L50:
            r0 = move-exception
            goto L91
        L52:
            r0 = move-exception
            goto L91
        L54:
            boolean r1 = r5.isSaveToOutputStream()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L64
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.io.OutputStream r1 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            r5.copyTempFileToOutputStream(r6, r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            goto L85
        L64:
            boolean r1 = r5.isSaveToFile()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 == 0) goto L85
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.io.File r1 = r1.getFile()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r2 == 0) goto L79
            r1.delete()     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
        L79:
            boolean r1 = r6.renameTo(r1)     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            if (r1 != 0) goto L85
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L89 java.lang.IllegalArgumentException -> L8b java.io.IOException -> L8d
            java.lang.String r3 = "Failed to rename file."
            r1 = r0
            goto L95
        L85:
            r1 = r0
            r2 = r1
            r3 = r2
            goto L95
        L89:
            r0 = move-exception
            goto La2
        L8b:
            r1 = move-exception
            goto L8e
        L8d:
            r1 = move-exception
        L8e:
            r4 = r1
            r1 = r0
            r0 = r4
        L91:
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L89
            java.lang.String r3 = "Failed to write destination file."
        L95:
            r6.delete()
            if (r2 == 0) goto L9e
            r5.postError(r2, r3, r0)
            goto La1
        L9e:
            r5.postSuccess(r1)
        La1:
            return
        La2:
            r6.delete()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageSaver.m90lambda$run$0$androidxcameracoreImageSaver(java.io.File):void");
    }

    private boolean isSaveToMediaStore() {
        return (this.mOutputFileOptions.getSaveCollection() == null || this.mOutputFileOptions.getContentResolver() == null || this.mOutputFileOptions.getContentValues() == null) ? false : true;
    }

    private boolean isSaveToFile() {
        return this.mOutputFileOptions.getFile() != null;
    }

    private boolean isSaveToOutputStream() {
        return this.mOutputFileOptions.getOutputStream() != null;
    }

    private void setUriNotPending(Uri outputUri) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            setContentValuePending(contentValues, 0);
            this.mOutputFileOptions.getContentResolver().update(outputUri, contentValues, null, null);
        }
    }

    private void setContentValuePending(ContentValues values, int isPending) {
        if (Build.VERSION.SDK_INT >= 29) {
            values.put("is_pending", Integer.valueOf(isPending));
        }
    }

    private boolean copyTempFileToUri(File tempFile, Uri uri) throws IOException {
        OutputStream outputStreamOpenOutputStream = this.mOutputFileOptions.getContentResolver().openOutputStream(uri);
        if (outputStreamOpenOutputStream == null) {
            if (outputStreamOpenOutputStream != null) {
                outputStreamOpenOutputStream.close();
            }
            return false;
        }
        try {
            copyTempFileToOutputStream(tempFile, outputStreamOpenOutputStream);
            if (outputStreamOpenOutputStream == null) {
                return true;
            }
            outputStreamOpenOutputStream.close();
            return true;
        } catch (Throwable th) {
            if (outputStreamOpenOutputStream != null) {
                try {
                    outputStreamOpenOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void copyTempFileToOutputStream(File tempFile, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(tempFile);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i > 0) {
                    outputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void postSuccess(final Uri outputUri) {
        try {
            this.mUserCallbackExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m89lambda$postSuccess$1$androidxcameracoreImageSaver(outputUri);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "Application executor rejected executing OnImageSavedCallback.onImageSaved callback. Skipping.");
        }
    }

    /* renamed from: lambda$postSuccess$1$androidx-camera-core-ImageSaver, reason: not valid java name */
    public /* synthetic */ void m89lambda$postSuccess$1$androidxcameracoreImageSaver(Uri uri) {
        this.mCallback.onImageSaved(new ImageCapture.OutputFileResults(uri));
    }

    private void postError(final SaveError saveError, final String message, final Throwable cause) {
        try {
            this.mUserCallbackExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m88lambda$postError$2$androidxcameracoreImageSaver(saveError, message, cause);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "Application executor rejected executing OnImageSavedCallback.onError callback. Skipping.");
        }
    }

    /* renamed from: lambda$postError$2$androidx-camera-core-ImageSaver, reason: not valid java name */
    public /* synthetic */ void m88lambda$postError$2$androidxcameracoreImageSaver(SaveError saveError, String str, Throwable th) {
        this.mCallback.onError(saveError, str, th);
    }
}
