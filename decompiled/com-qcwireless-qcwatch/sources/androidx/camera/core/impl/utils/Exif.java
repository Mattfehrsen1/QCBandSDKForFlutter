package androidx.camera.core.impl.utils;

import android.location.Location;
import androidx.camera.core.Logger;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public final class Exif {
    public static final long INVALID_TIMESTAMP = -1;
    private static final String KILOMETERS_PER_HOUR = "K";
    private static final String KNOTS = "N";
    private static final String MILES_PER_HOUR = "M";
    private static final String TAG = "Exif";
    private final ExifInterface mExifInterface;
    private boolean mRemoveTimestamp = false;
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() { // from class: androidx.camera.core.impl.utils.Exif.1
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() { // from class: androidx.camera.core.impl.utils.Exif.2
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.US);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() { // from class: androidx.camera.core.impl.utils.Exif.3
        @Override // java.lang.ThreadLocal
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        }
    };

    private Exif(ExifInterface exifInterface) {
        this.mExifInterface = exifInterface;
    }

    public static Exif createFromFile(File file) throws IOException {
        return createFromFileString(file.toString());
    }

    public static Exif createFromFileString(String filePath) throws IOException {
        return new Exif(new ExifInterface(filePath));
    }

    public static Exif createFromInputStream(InputStream is) throws IOException {
        return new Exif(new ExifInterface(is));
    }

    private static String convertToExifDateTime(long timestamp) {
        return DATETIME_FORMAT.get().format(new Date(timestamp));
    }

    private static Date convertFromExifDateTime(String dateTime) throws ParseException {
        return DATETIME_FORMAT.get().parse(dateTime);
    }

    private static Date convertFromExifDate(String date) throws ParseException {
        return DATE_FORMAT.get().parse(date);
    }

    private static Date convertFromExifTime(String time) throws ParseException {
        return TIME_FORMAT.get().parse(time);
    }

    public void save() throws Throwable {
        if (!this.mRemoveTimestamp) {
            attachLastModifiedTimestamp();
        }
        this.mExifInterface.saveAttributes();
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "Exif{width=%s, height=%s, rotation=%d, isFlippedVertically=%s, isFlippedHorizontally=%s, location=%s, timestamp=%s, description=%s}", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(getRotation()), Boolean.valueOf(isFlippedVertically()), Boolean.valueOf(isFlippedHorizontally()), getLocation(), Long.valueOf(getTimestamp()), getDescription());
    }

    public int getOrientation() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
    }

    public void setOrientation(int orientation) throws NumberFormatException {
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(orientation));
    }

    public int getWidth() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
    }

    public int getHeight() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
    }

    public String getDescription() {
        return this.mExifInterface.getAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION);
    }

    public void setDescription(String description) throws NumberFormatException {
        this.mExifInterface.setAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION, description);
    }

    public int getRotation() {
        switch (getOrientation()) {
            case 3:
            case 4:
                return 180;
            case 5:
                return 270;
            case 6:
            case 7:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public boolean isFlippedVertically() {
        int orientation = getOrientation();
        return orientation == 4 || orientation == 5 || orientation == 7;
    }

    public boolean isFlippedHorizontally() {
        return getOrientation() == 2;
    }

    private void attachLastModifiedTimestamp() throws NumberFormatException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strConvertToExifDateTime = convertToExifDateTime(jCurrentTimeMillis);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME, strConvertToExifDateTime);
        try {
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME, Long.toString(jCurrentTimeMillis - convertFromExifDateTime(strConvertToExifDateTime).getTime()));
        } catch (ParseException unused) {
        }
    }

    public long getLastModifiedTimestamp() throws NumberFormatException {
        long timestamp = parseTimestamp(this.mExifInterface.getAttribute(ExifInterface.TAG_DATETIME));
        if (timestamp == -1) {
            return -1L;
        }
        String attribute = this.mExifInterface.getAttribute(ExifInterface.TAG_SUBSEC_TIME);
        if (attribute == null) {
            return timestamp;
        }
        try {
            long j = Long.parseLong(attribute);
            while (j > 1000) {
                j /= 10;
            }
            return timestamp + j;
        } catch (NumberFormatException unused) {
            return timestamp;
        }
    }

    public long getTimestamp() throws NumberFormatException {
        long timestamp = parseTimestamp(this.mExifInterface.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL));
        if (timestamp == -1) {
            return -1L;
        }
        String attribute = this.mExifInterface.getAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL);
        if (attribute == null) {
            return timestamp;
        }
        try {
            long j = Long.parseLong(attribute);
            while (j > 1000) {
                j /= 10;
            }
            return timestamp + j;
        } catch (NumberFormatException unused) {
            return timestamp;
        }
    }

    public Location getLocation() {
        double metersPerSecond;
        String attribute = this.mExifInterface.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD);
        double[] latLong = this.mExifInterface.getLatLong();
        double altitude = this.mExifInterface.getAltitude(0.0d);
        double attributeDouble = this.mExifInterface.getAttributeDouble(ExifInterface.TAG_GPS_SPEED, 0.0d);
        String attribute2 = this.mExifInterface.getAttribute(ExifInterface.TAG_GPS_SPEED_REF);
        if (attribute2 == null) {
            attribute2 = "K";
        }
        long timestamp = parseTimestamp(this.mExifInterface.getAttribute(ExifInterface.TAG_GPS_DATESTAMP), this.mExifInterface.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP));
        if (latLong == null) {
            return null;
        }
        if (attribute == null) {
            attribute = TAG;
        }
        Location location = new Location(attribute);
        location.setLatitude(latLong[0]);
        location.setLongitude(latLong[1]);
        if (altitude != 0.0d) {
            location.setAltitude(altitude);
        }
        if (attributeDouble != 0.0d) {
            char c = 65535;
            int iHashCode = attribute2.hashCode();
            if (iHashCode != 75) {
                if (iHashCode != 77) {
                    if (iHashCode == 78 && attribute2.equals("N")) {
                        c = 1;
                    }
                } else if (attribute2.equals("M")) {
                    c = 0;
                }
            } else if (attribute2.equals("K")) {
                c = 2;
            }
            if (c == 0) {
                metersPerSecond = Speed.fromMilesPerHour(attributeDouble).toMetersPerSecond();
            } else if (c == 1) {
                metersPerSecond = Speed.fromKnots(attributeDouble).toMetersPerSecond();
            } else {
                metersPerSecond = Speed.fromKilometersPerHour(attributeDouble).toMetersPerSecond();
            }
            location.setSpeed((float) metersPerSecond);
        }
        if (timestamp != -1) {
            location.setTime(timestamp);
        }
        return location;
    }

    public void rotate(int degrees) throws NumberFormatException {
        if (degrees % 90 != 0) {
            Logger.w(TAG, String.format(Locale.US, "Can only rotate in right angles (eg. 0, 90, 180, 270). %d is unsupported.", Integer.valueOf(degrees)));
            this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(0));
            return;
        }
        int i = degrees % 360;
        int orientation = getOrientation();
        while (i < 0) {
            i += 90;
            switch (orientation) {
                case 2:
                    orientation = 5;
                    break;
                case 3:
                case 8:
                    orientation = 6;
                    break;
                case 4:
                    orientation = 7;
                    break;
                case 5:
                    orientation = 4;
                    break;
                case 6:
                    orientation = 1;
                    break;
                case 7:
                    orientation = 2;
                    break;
                default:
                    orientation = 8;
                    break;
            }
        }
        while (i > 0) {
            i -= 90;
            switch (orientation) {
                case 2:
                    orientation = 7;
                    break;
                case 3:
                    orientation = 8;
                    break;
                case 4:
                    orientation = 5;
                    break;
                case 5:
                    orientation = 2;
                    break;
                case 6:
                    orientation = 3;
                    break;
                case 7:
                    orientation = 4;
                    break;
                case 8:
                    orientation = 1;
                    break;
                default:
                    orientation = 6;
                    break;
            }
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(orientation));
    }

    public void flipVertically() throws NumberFormatException {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 3;
                break;
            case 3:
                i = 2;
                break;
            case 4:
                i = 1;
                break;
            case 5:
                i = 8;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 6;
                break;
            case 8:
                i = 5;
                break;
            default:
                i = 4;
                break;
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public void flipHorizontally() throws NumberFormatException {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 1;
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 3;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 5;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 7;
                break;
            default:
                i = 2;
                break;
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public void attachTimestamp() throws NumberFormatException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strConvertToExifDateTime = convertToExifDateTime(jCurrentTimeMillis);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, strConvertToExifDateTime);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, strConvertToExifDateTime);
        try {
            String string = Long.toString(jCurrentTimeMillis - convertFromExifDateTime(strConvertToExifDateTime).getTime());
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, string);
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, string);
        } catch (ParseException unused) {
        }
        this.mRemoveTimestamp = false;
    }

    public void removeTimestamp() throws NumberFormatException {
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, null);
        this.mRemoveTimestamp = true;
    }

    public void attachLocation(Location location) throws NumberFormatException {
        this.mExifInterface.setGpsInfo(location);
    }

    public void removeLocation() throws NumberFormatException {
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_SPEED, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_SPEED_REF, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_DATESTAMP, null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_TIMESTAMP, null);
    }

    private long parseTimestamp(String date, String time) {
        if (date == null && time == null) {
            return -1L;
        }
        if (time == null) {
            try {
                return convertFromExifDate(date).getTime();
            } catch (ParseException unused) {
                return -1L;
            }
        }
        if (date == null) {
            try {
                return convertFromExifTime(time).getTime();
            } catch (ParseException unused2) {
                return -1L;
            }
        }
        return parseTimestamp(date + " " + time);
    }

    private long parseTimestamp(String datetime) {
        if (datetime == null) {
            return -1L;
        }
        try {
            return convertFromExifDateTime(datetime).getTime();
        } catch (ParseException unused) {
            return -1L;
        }
    }

    private static final class Speed {
        private Speed() {
        }

        static Converter fromKilometersPerHour(double kph) {
            return new Converter(kph * 0.621371d);
        }

        static Converter fromMetersPerSecond(double mps) {
            return new Converter(mps * 2.23694d);
        }

        static Converter fromMilesPerHour(double mph) {
            return new Converter(mph);
        }

        static Converter fromKnots(double knots) {
            return new Converter(knots * 1.15078d);
        }

        static final class Converter {
            final double mMph;

            Converter(double mph) {
                this.mMph = mph;
            }

            double toKilometersPerHour() {
                return this.mMph / 0.621371d;
            }

            double toMilesPerHour() {
                return this.mMph;
            }

            double toKnots() {
                return this.mMph / 1.15078d;
            }

            double toMetersPerSecond() {
                return this.mMph / 2.23694d;
            }
        }
    }
}
