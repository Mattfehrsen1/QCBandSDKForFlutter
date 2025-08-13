package com.qcwireless.qcwatch.ui.plate.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes3.dex */
public class BitmapUtils {

    public interface CompressFileCallback {
        void onCompressFileFailed(String errorMsg);

        void onCompressFileFinished(File file, Bitmap bitmap);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 > reqHeight && i5 / i3 > reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, float hRatioW) {
        int width = bitmap.getWidth();
        bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, 0, 0, width, (int) (width * hRatioW), (Matrix) null, false);
    }

    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        return Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
    }

    public static void compressFile(CompressFileBean compressFileBean, CompressFileCallback compressFileCallback) {
        new CompressFileThread(compressFileBean, compressFileCallback).start();
    }

    public static Bitmap decodeBitmapFromFilePath(String path, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File createFile(String pathFile) throws IOException {
        File file = new File(pathFile.substring(0, pathFile.lastIndexOf(File.separator)));
        File file2 = new File(pathFile);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        return file2;
    }

    public static int getBitmapDegree(String path) {
        try {
            int attributeInt = new ExifInterface(path).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap bitmapCreateBitmap;
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = bm;
        }
        if (bm != bitmapCreateBitmap) {
            bm.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Object[] captureViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        view.layout(0, 0, measuredWidth, measuredHeight);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444);
        view.draw(new Canvas(bitmapCreateBitmap));
        return new Object[]{bitmapCreateBitmap, Integer.valueOf(measuredWidth), Integer.valueOf(measuredHeight)};
    }

    public static Bitmap captureViewToBitmap(View view, int reqWidth, int reqHeight) {
        view.layout(0, 0, reqWidth, reqHeight);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(reqWidth, reqHeight, Bitmap.Config.ARGB_4444);
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        return Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true);
    }

    private static class CompressFileThread extends Thread {
        private CompressFileBean compressFileBean;
        private CompressFileCallback compressFileCallback;
        private Handler handler_deliver = new Handler(Looper.getMainLooper());

        public CompressFileThread(CompressFileBean compressFileBean, CompressFileCallback compressFileCallback) {
            this.compressFileBean = compressFileBean;
            this.compressFileCallback = compressFileCallback;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            super.run();
            final Bitmap bitmapDecodeBitmapFromFilePath = BitmapUtils.decodeBitmapFromFilePath(this.compressFileBean.getPathSource(), this.compressFileBean.getReqWidth(), this.compressFileBean.getReqHeight());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 80;
            bitmapDecodeBitmapFromFilePath.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            while (byteArrayOutputStream.toByteArray().length / 1024 > this.compressFileBean.getKb_max() && i > this.compressFileBean.getQuality_max()) {
                byteArrayOutputStream.reset();
                i -= 10;
                bitmapDecodeBitmapFromFilePath.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            }
            try {
                final File fileCreateFile = BitmapUtils.createFile(this.compressFileBean.getPathCompressed());
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateFile);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();
                byteArrayOutputStream.close();
                if (fileCreateFile == null || fileCreateFile.length() <= 0) {
                    return;
                }
                runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.plate.util.BitmapUtils.CompressFileThread.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CompressFileThread.this.compressFileCallback.onCompressFileFinished(fileCreateFile, bitmapDecodeBitmapFromFilePath);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() { // from class: com.qcwireless.qcwatch.ui.plate.util.BitmapUtils.CompressFileThread.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CompressFileThread.this.compressFileCallback.onCompressFileFailed("压缩图片文件失败" + e.getMessage());
                    }
                });
            }
        }

        private void runOnUiThread(Runnable run) {
            this.handler_deliver.post(run);
        }
    }

    public static class CompressFileBean {
        private int kb_max;
        private String pathCompressed;
        private String pathSource;
        private int quality_max;
        private int reqHeight;
        private int reqWidth;

        private CompressFileBean(Builder builder) {
            this.kb_max = 1000;
            this.quality_max = 80;
            this.reqWidth = 1000;
            this.reqHeight = 1000;
            this.pathSource = builder.getFileSource();
            this.pathCompressed = builder.getFileCompressed();
            this.kb_max = builder.getKb_max();
            this.quality_max = builder.getQuality_max();
            this.reqWidth = builder.getReqWidth();
            this.reqHeight = builder.getReqHeight();
        }

        public static class Builder {
            private String pathCompressed;
            private String pathSource;
            private int kb_max = 1000;
            private int quality_max = 80;
            private int reqWidth = 1000;
            private int reqHeight = 1000;

            public String getFileSource() {
                return this.pathSource;
            }

            public Builder setFileSource(String pathSource) {
                this.pathSource = pathSource;
                return this;
            }

            public String getFileCompressed() {
                return this.pathCompressed;
            }

            public Builder setFileCompressed(String pathCompressed) {
                this.pathCompressed = pathCompressed;
                return this;
            }

            public int getKb_max() {
                return this.kb_max;
            }

            public Builder setKb_max(int kb_max) {
                this.kb_max = kb_max;
                return this;
            }

            public int getQuality_max() {
                return this.quality_max;
            }

            public Builder setQuality_max(int quality_max) {
                this.quality_max = quality_max;
                return this;
            }

            public int getReqWidth() {
                return this.reqWidth;
            }

            public Builder setReqWidth(int reqWidth) {
                this.reqWidth = reqWidth;
                return this;
            }

            public int getReqHeight() {
                return this.reqHeight;
            }

            public Builder setReqHeight(int reqHeight) {
                this.reqHeight = reqHeight;
                return this;
            }

            public CompressFileBean build() {
                return new CompressFileBean(this);
            }
        }

        public String getPathSource() {
            return this.pathSource;
        }

        public String getPathCompressed() {
            return this.pathCompressed;
        }

        public int getKb_max() {
            return this.kb_max;
        }

        public int getQuality_max() {
            return this.quality_max;
        }

        public int getReqWidth() {
            return this.reqWidth;
        }

        public int getReqHeight() {
            return this.reqHeight;
        }
    }
}
