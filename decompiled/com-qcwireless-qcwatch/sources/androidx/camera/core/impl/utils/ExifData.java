package androidx.camera.core.impl.utils;

import android.os.Build;
import android.util.Pair;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.core.util.Preconditions;
import androidx.exifinterface.media.ExifInterface;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes.dex */
public class ExifData {
    private static final boolean DEBUG = false;
    static final ExifTag[] EXIF_POINTER_TAGS;
    static final ExifTag[][] EXIF_TAGS;
    private static final ExifTag[] IFD_EXIF_TAGS;
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    private static final ExifTag[] IFD_GPS_TAGS;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
    static final int IFD_TYPE_EXIF = 1;
    static final int IFD_TYPE_GPS = 2;
    static final int IFD_TYPE_INTEROPERABILITY = 3;
    static final int IFD_TYPE_PRIMARY = 0;
    private static final int MM_IN_MICRONS = 1000;
    private static final String TAG = "ExifData";
    static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    static final HashSet<String> sTagSetForCompatibility;
    private final List<Map<String, ExifAttribute>> mAttributes;
    private final ByteOrder mByteOrder;

    public enum WhiteBalanceMode {
        AUTO,
        MANUAL
    }

    static {
        ExifTag[] exifTagArr = {new ExifTag(ExifInterface.TAG_IMAGE_WIDTH, 256, 3, 4), new ExifTag(ExifInterface.TAG_IMAGE_LENGTH, 257, 3, 4), new ExifTag(ExifInterface.TAG_MAKE, 271, 2), new ExifTag(ExifInterface.TAG_MODEL, 272, 2), new ExifTag(ExifInterface.TAG_ORIENTATION, 274, 3), new ExifTag(ExifInterface.TAG_X_RESOLUTION, 282, 5), new ExifTag(ExifInterface.TAG_Y_RESOLUTION, 283, 5), new ExifTag(ExifInterface.TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(ExifInterface.TAG_SOFTWARE, 305, 2), new ExifTag(ExifInterface.TAG_DATETIME, 306, 2), new ExifTag(ExifInterface.TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4)};
        IFD_TIFF_TAGS = exifTagArr;
        ExifTag[] exifTagArr2 = {new ExifTag(ExifInterface.TAG_EXPOSURE_TIME, 33434, 5), new ExifTag(ExifInterface.TAG_F_NUMBER, 33437, 5), new ExifTag(ExifInterface.TAG_EXPOSURE_PROGRAM, 34850, 3), new ExifTag(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new ExifTag(ExifInterface.TAG_SENSITIVITY_TYPE, 34864, 3), new ExifTag(ExifInterface.TAG_EXIF_VERSION, 36864, 2), new ExifTag(ExifInterface.TAG_DATETIME_ORIGINAL, 36867, 2), new ExifTag(ExifInterface.TAG_DATETIME_DIGITIZED, 36868, 2), new ExifTag(ExifInterface.TAG_COMPONENTS_CONFIGURATION, 37121, 7), new ExifTag(ExifInterface.TAG_SHUTTER_SPEED_VALUE, 37377, 10), new ExifTag(ExifInterface.TAG_APERTURE_VALUE, 37378, 5), new ExifTag(ExifInterface.TAG_BRIGHTNESS_VALUE, 37379, 10), new ExifTag(ExifInterface.TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new ExifTag(ExifInterface.TAG_MAX_APERTURE_VALUE, 37381, 5), new ExifTag(ExifInterface.TAG_METERING_MODE, 37383, 3), new ExifTag(ExifInterface.TAG_LIGHT_SOURCE, 37384, 3), new ExifTag(ExifInterface.TAG_FLASH, 37385, 3), new ExifTag(ExifInterface.TAG_FOCAL_LENGTH, 37386, 5), new ExifTag(ExifInterface.TAG_SUBSEC_TIME, 37520, 2), new ExifTag(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new ExifTag(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new ExifTag(ExifInterface.TAG_FLASHPIX_VERSION, 40960, 7), new ExifTag(ExifInterface.TAG_COLOR_SPACE, 40961, 3), new ExifTag(ExifInterface.TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new ExifTag(ExifInterface.TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new ExifTag(ExifInterface.TAG_SENSING_METHOD, 41495, 3), new ExifTag(ExifInterface.TAG_FILE_SOURCE, 41728, 7), new ExifTag(ExifInterface.TAG_SCENE_TYPE, 41729, 7), new ExifTag(ExifInterface.TAG_CUSTOM_RENDERED, 41985, 3), new ExifTag(ExifInterface.TAG_EXPOSURE_MODE, 41986, 3), new ExifTag(ExifInterface.TAG_WHITE_BALANCE, 41987, 3), new ExifTag(ExifInterface.TAG_SCENE_CAPTURE_TYPE, 41990, 3), new ExifTag(ExifInterface.TAG_CONTRAST, 41992, 3), new ExifTag(ExifInterface.TAG_SATURATION, 41993, 3), new ExifTag(ExifInterface.TAG_SHARPNESS, 41994, 3)};
        IFD_EXIF_TAGS = exifTagArr2;
        ExifTag[] exifTagArr3 = {new ExifTag(ExifInterface.TAG_GPS_VERSION_ID, 0, 1), new ExifTag(ExifInterface.TAG_GPS_LATITUDE_REF, 1, 2), new ExifTag(ExifInterface.TAG_GPS_LATITUDE, 2, 5, 10), new ExifTag(ExifInterface.TAG_GPS_LONGITUDE_REF, 3, 2), new ExifTag(ExifInterface.TAG_GPS_LONGITUDE, 4, 5, 10), new ExifTag(ExifInterface.TAG_GPS_ALTITUDE_REF, 5, 1), new ExifTag(ExifInterface.TAG_GPS_ALTITUDE, 6, 5), new ExifTag(ExifInterface.TAG_GPS_TIMESTAMP, 7, 5), new ExifTag(ExifInterface.TAG_GPS_SPEED_REF, 12, 2), new ExifTag(ExifInterface.TAG_GPS_TRACK_REF, 14, 2), new ExifTag(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, 16, 2), new ExifTag(ExifInterface.TAG_GPS_DEST_BEARING_REF, 23, 2), new ExifTag(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, 25, 2)};
        IFD_GPS_TAGS = exifTagArr3;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4)};
        ExifTag[] exifTagArr4 = {new ExifTag(ExifInterface.TAG_INTEROPERABILITY_INDEX, 1, 2)};
        IFD_INTEROPERABILITY_TAGS = exifTagArr4;
        EXIF_TAGS = new ExifTag[][]{exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4};
        sTagSetForCompatibility = new HashSet<>(Arrays.asList(ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_GPS_TIMESTAMP));
    }

    ExifData(ByteOrder order, List<Map<String, ExifAttribute>> attributes) {
        Preconditions.checkState(attributes.size() == EXIF_TAGS.length, "Malformed attributes list. Number of IFDs mismatch.");
        this.mByteOrder = order;
        this.mAttributes = attributes;
    }

    public ByteOrder getByteOrder() {
        return this.mByteOrder;
    }

    Map<String, ExifAttribute> getAttributes(int ifdIndex) {
        Preconditions.checkArgumentInRange(ifdIndex, 0, EXIF_TAGS.length, "Invalid IFD index: " + ifdIndex + ". Index should be between [0, EXIF_TAGS.length] ");
        return this.mAttributes.get(ifdIndex);
    }

    public String getAttribute(String tag) {
        ExifAttribute exifAttribute = getExifAttribute(tag);
        if (exifAttribute != null) {
            if (!sTagSetForCompatibility.contains(tag)) {
                return exifAttribute.getStringValue(this.mByteOrder);
            }
            if (tag.equals(ExifInterface.TAG_GPS_TIMESTAMP)) {
                if (exifAttribute.format != 5 && exifAttribute.format != 10) {
                    Logger.w(TAG, "GPS Timestamp format is not rational. format=" + exifAttribute.format);
                    return null;
                }
                LongRational[] longRationalArr = (LongRational[]) exifAttribute.getValue(this.mByteOrder);
                if (longRationalArr == null || longRationalArr.length != 3) {
                    Logger.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(longRationalArr));
                    return null;
                }
                return String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf((int) (longRationalArr[0].getNumerator() / longRationalArr[0].getDenominator())), Integer.valueOf((int) (longRationalArr[1].getNumerator() / longRationalArr[1].getDenominator())), Integer.valueOf((int) (longRationalArr[2].getNumerator() / longRationalArr[2].getDenominator())));
            }
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mByteOrder));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    private ExifAttribute getExifAttribute(String tag) {
        if (ExifInterface.TAG_ISO_SPEED_RATINGS.equals(tag)) {
            tag = ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY;
        }
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            ExifAttribute exifAttribute = this.mAttributes.get(i).get(tag);
            if (exifAttribute != null) {
                return exifAttribute;
            }
        }
        return null;
    }

    public static Builder builderForDevice() {
        return new Builder(ByteOrder.BIG_ENDIAN).setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(1)).setAttribute(ExifInterface.TAG_X_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_Y_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_RESOLUTION_UNIT, String.valueOf(2)).setAttribute(ExifInterface.TAG_Y_CB_CR_POSITIONING, String.valueOf(1)).setAttribute(ExifInterface.TAG_MAKE, Build.MANUFACTURER).setAttribute(ExifInterface.TAG_MODEL, Build.MODEL);
    }

    public static final class Builder {
        private static final int DATETIME_VALUE_STRING_LENGTH = 19;
        final List<Map<String, ExifAttribute>> mAttributes = Collections.list(new Enumeration<Map<String, ExifAttribute>>() { // from class: androidx.camera.core.impl.utils.ExifData.Builder.2
            int mIfdIndex = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.mIfdIndex < ExifData.EXIF_TAGS.length;
            }

            @Override // java.util.Enumeration
            public Map<String, ExifAttribute> nextElement() {
                this.mIfdIndex++;
                return new HashMap();
            }
        });
        private final ByteOrder mByteOrder;
        private static final Pattern GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        static final List<HashMap<String, ExifTag>> sExifTagMapsForWriting = Collections.list(new Enumeration<HashMap<String, ExifTag>>() { // from class: androidx.camera.core.impl.utils.ExifData.Builder.1
            int mIfdIndex = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.mIfdIndex < ExifData.EXIF_TAGS.length;
            }

            @Override // java.util.Enumeration
            public HashMap<String, ExifTag> nextElement() {
                HashMap<String, ExifTag> map = new HashMap<>();
                for (ExifTag exifTag : ExifData.EXIF_TAGS[this.mIfdIndex]) {
                    map.put(exifTag.name, exifTag);
                }
                this.mIfdIndex++;
                return map;
            }
        });

        Builder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public Builder setImageWidth(int width) {
            return setAttribute(ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(width));
        }

        public Builder setImageHeight(int height) {
            return setAttribute(ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(height));
        }

        public Builder setOrientationDegrees(int orientationDegrees) {
            int i;
            if (orientationDegrees == 0) {
                i = 1;
            } else if (orientationDegrees == 90) {
                i = 6;
            } else if (orientationDegrees == 180) {
                i = 3;
            } else if (orientationDegrees != 270) {
                Logger.w(ExifData.TAG, "Unexpected orientation value: " + orientationDegrees + ". Must be one of 0, 90, 180, 270.");
                i = 0;
            } else {
                i = 8;
            }
            return setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
        }

        public Builder setFlashState(CameraCaptureMetaData.FlashState flashState) {
            int i;
            if (flashState == CameraCaptureMetaData.FlashState.UNKNOWN) {
                return this;
            }
            int i2 = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[flashState.ordinal()];
            if (i2 == 1) {
                i = 0;
            } else if (i2 == 2) {
                i = 32;
            } else {
                if (i2 != 3) {
                    Logger.w(ExifData.TAG, "Unknown flash state: " + flashState);
                    return this;
                }
                i = 1;
            }
            if ((i & 1) == 1) {
                setAttribute(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(4));
            }
            return setAttribute(ExifInterface.TAG_FLASH, String.valueOf(i));
        }

        public Builder setExposureTimeNanos(long exposureTimeNs) {
            return setAttribute(ExifInterface.TAG_EXPOSURE_TIME, String.valueOf(exposureTimeNs / TimeUnit.SECONDS.toNanos(1L)));
        }

        public Builder setLensFNumber(float fNumber) {
            return setAttribute(ExifInterface.TAG_F_NUMBER, String.valueOf(fNumber));
        }

        public Builder setIso(int iso) {
            return setAttribute(ExifInterface.TAG_SENSITIVITY_TYPE, String.valueOf(3)).setAttribute(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, String.valueOf(Math.min(65535, iso)));
        }

        public Builder setFocalLength(float focalLength) {
            return setAttribute(ExifInterface.TAG_FOCAL_LENGTH, new LongRational((long) (focalLength * 1000.0f), 1000L).toString());
        }

        public Builder setWhiteBalanceMode(WhiteBalanceMode whiteBalanceMode) {
            String strValueOf;
            int i = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$utils$ExifData$WhiteBalanceMode[whiteBalanceMode.ordinal()];
            if (i == 1) {
                strValueOf = String.valueOf(0);
            } else {
                strValueOf = i != 2 ? null : String.valueOf(1);
            }
            return setAttribute(ExifInterface.TAG_WHITE_BALANCE, strValueOf);
        }

        public Builder setAttribute(String tag, String value) throws NumberFormatException {
            setAttributeInternal(tag, value, this.mAttributes);
            return this;
        }

        public Builder removeAttribute(String tag) throws NumberFormatException {
            setAttributeInternal(tag, null, this.mAttributes);
            return this;
        }

        private void setAttributeIfMissing(String tag, String value, List<Map<String, ExifAttribute>> attributes) throws NumberFormatException {
            Iterator<Map<String, ExifAttribute>> it = attributes.iterator();
            while (it.hasNext()) {
                if (it.next().containsKey(tag)) {
                    return;
                }
            }
            setAttributeInternal(tag, value, attributes);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
            	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
            	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
            	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
            */
        private void setAttributeInternal(java.lang.String r18, java.lang.String r19, java.util.List<java.util.Map<java.lang.String, androidx.camera.core.impl.utils.ExifAttribute>> r20) throws java.lang.NumberFormatException {
            /*
                Method dump skipped, instructions count: 780
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.ExifData.Builder.setAttributeInternal(java.lang.String, java.lang.String, java.util.List):void");
        }

        public ExifData build() throws NumberFormatException {
            ArrayList list = Collections.list(new Enumeration<Map<String, ExifAttribute>>() { // from class: androidx.camera.core.impl.utils.ExifData.Builder.3
                final Enumeration<Map<String, ExifAttribute>> mMapEnumeration;

                {
                    this.mMapEnumeration = Collections.enumeration(Builder.this.mAttributes);
                }

                @Override // java.util.Enumeration
                public boolean hasMoreElements() {
                    return this.mMapEnumeration.hasMoreElements();
                }

                @Override // java.util.Enumeration
                public Map<String, ExifAttribute> nextElement() {
                    return new HashMap(this.mMapEnumeration.nextElement());
                }
            });
            if (!list.get(1).isEmpty()) {
                setAttributeIfMissing(ExifInterface.TAG_EXPOSURE_PROGRAM, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_EXIF_VERSION, "0230", list);
                setAttributeIfMissing(ExifInterface.TAG_COMPONENTS_CONFIGURATION, "1,2,3,0", list);
                setAttributeIfMissing(ExifInterface.TAG_METERING_MODE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_FLASHPIX_VERSION, "0100", list);
                setAttributeIfMissing(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, String.valueOf(2), list);
                setAttributeIfMissing(ExifInterface.TAG_FILE_SOURCE, String.valueOf(3), list);
                setAttributeIfMissing(ExifInterface.TAG_SCENE_TYPE, String.valueOf(1), list);
                setAttributeIfMissing(ExifInterface.TAG_CUSTOM_RENDERED, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SCENE_CAPTURE_TYPE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_CONTRAST, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SATURATION, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SHARPNESS, String.valueOf(0), list);
            }
            if (!list.get(2).isEmpty()) {
                setAttributeIfMissing(ExifInterface.TAG_GPS_VERSION_ID, "2300", list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_SPEED_REF, "K", list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_TRACK_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_DEST_BEARING_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, "K", list);
            }
            return new ExifData(this.mByteOrder, list);
        }

        private static Pair<Integer, Integer> guessDataFormat(String entryValue) throws NumberFormatException {
            if (entryValue.contains(",")) {
                String[] strArrSplit = entryValue.split(",", -1);
                Pair<Integer, Integer> pairGuessDataFormat = guessDataFormat(strArrSplit[0]);
                if (((Integer) pairGuessDataFormat.first).intValue() == 2) {
                    return pairGuessDataFormat;
                }
                for (int i = 1; i < strArrSplit.length; i++) {
                    Pair<Integer, Integer> pairGuessDataFormat2 = guessDataFormat(strArrSplit[i]);
                    int iIntValue = (((Integer) pairGuessDataFormat2.first).equals(pairGuessDataFormat.first) || ((Integer) pairGuessDataFormat2.second).equals(pairGuessDataFormat.first)) ? ((Integer) pairGuessDataFormat.first).intValue() : -1;
                    int iIntValue2 = (((Integer) pairGuessDataFormat.second).intValue() == -1 || !(((Integer) pairGuessDataFormat2.first).equals(pairGuessDataFormat.second) || ((Integer) pairGuessDataFormat2.second).equals(pairGuessDataFormat.second))) ? -1 : ((Integer) pairGuessDataFormat.second).intValue();
                    if (iIntValue == -1 && iIntValue2 == -1) {
                        return new Pair<>(2, -1);
                    }
                    if (iIntValue == -1) {
                        pairGuessDataFormat = new Pair<>(Integer.valueOf(iIntValue2), -1);
                    } else if (iIntValue2 == -1) {
                        pairGuessDataFormat = new Pair<>(Integer.valueOf(iIntValue), -1);
                    }
                }
                return pairGuessDataFormat;
            }
            if (entryValue.contains("/")) {
                String[] strArrSplit2 = entryValue.split("/", -1);
                if (strArrSplit2.length == 2) {
                    try {
                        long j = (long) Double.parseDouble(strArrSplit2[0]);
                        long j2 = (long) Double.parseDouble(strArrSplit2[1]);
                        if (j >= 0 && j2 >= 0) {
                            if (j <= 2147483647L && j2 <= 2147483647L) {
                                return new Pair<>(10, 5);
                            }
                            return new Pair<>(5, -1);
                        }
                        return new Pair<>(10, -1);
                    } catch (NumberFormatException unused) {
                    }
                }
                return new Pair<>(2, -1);
            }
            try {
                try {
                    long j3 = Long.parseLong(entryValue);
                    if (j3 >= 0 && j3 <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                        return new Pair<>(3, 4);
                    }
                    if (j3 < 0) {
                        return new Pair<>(9, -1);
                    }
                    return new Pair<>(4, -1);
                } catch (NumberFormatException unused2) {
                    return new Pair<>(2, -1);
                }
            } catch (NumberFormatException unused3) {
                Double.parseDouble(entryValue);
                return new Pair<>(12, -1);
            }
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.ExifData$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState;
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$utils$ExifData$WhiteBalanceMode;

        static {
            int[] iArr = new int[WhiteBalanceMode.values().length];
            $SwitchMap$androidx$camera$core$impl$utils$ExifData$WhiteBalanceMode = iArr;
            try {
                iArr[WhiteBalanceMode.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$core$impl$utils$ExifData$WhiteBalanceMode[WhiteBalanceMode.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[CameraCaptureMetaData.FlashState.values().length];
            $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState = iArr2;
            try {
                iArr2[CameraCaptureMetaData.FlashState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[CameraCaptureMetaData.FlashState.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$camera$core$impl$CameraCaptureMetaData$FlashState[CameraCaptureMetaData.FlashState.FIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
