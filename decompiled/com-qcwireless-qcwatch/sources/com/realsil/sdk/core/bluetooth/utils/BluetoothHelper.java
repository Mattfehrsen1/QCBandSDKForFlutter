package com.realsil.sdk.core.bluetooth.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.ParcelUuid;
import android.provider.Settings;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class BluetoothHelper {
    public static final int BD_ADDR_LEN = 6;
    public static final int BD_UUID_LEN = 16;

    public static String convertMac(byte[] bArr) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        if ((bArr[5] & 255) <= 15) {
            StringBuilder sbA = a.a("0");
            sbA.append(Integer.toHexString(bArr[5] & 255).toUpperCase());
            sbA.append(":");
            string = sbA.toString();
        } else {
            string = Integer.toHexString(bArr[5] & 255).toUpperCase() + ":";
        }
        if ((bArr[4] & 255) <= 15) {
            string2 = string + "0" + Integer.toHexString(bArr[4] & 255).toUpperCase() + ":";
        } else {
            StringBuilder sbA2 = a.a(string);
            sbA2.append(Integer.toHexString(bArr[4] & 255).toUpperCase());
            sbA2.append(":");
            string2 = sbA2.toString();
        }
        if ((bArr[3] & 255) <= 15) {
            string3 = string2 + "0" + Integer.toHexString(bArr[3] & 255).toUpperCase() + ":";
        } else {
            StringBuilder sbA3 = a.a(string2);
            sbA3.append(Integer.toHexString(bArr[3] & 255).toUpperCase());
            sbA3.append(":");
            string3 = sbA3.toString();
        }
        if ((bArr[2] & 255) <= 15) {
            string4 = string3 + "0" + Integer.toHexString(bArr[2] & 255).toUpperCase() + ":";
        } else {
            StringBuilder sbA4 = a.a(string3);
            sbA4.append(Integer.toHexString(bArr[2] & 255).toUpperCase());
            sbA4.append(":");
            string4 = sbA4.toString();
        }
        if ((bArr[1] & 255) <= 15) {
            string5 = string4 + "0" + Integer.toHexString(bArr[1] & 255).toUpperCase() + ":";
        } else {
            StringBuilder sbA5 = a.a(string4);
            sbA5.append(Integer.toHexString(bArr[1] & 255).toUpperCase());
            sbA5.append(":");
            string5 = sbA5.toString();
        }
        if ((bArr[0] & 255) > 15) {
            StringBuilder sbA6 = a.a(string5);
            sbA6.append(Integer.toHexString(bArr[0] & 255).toUpperCase());
            return sbA6.toString();
        }
        return string5 + "0" + Integer.toHexString(bArr[0] & 255).toUpperCase();
    }

    public static String dumpBluetoothDevice(BluetoothDevice bluetoothDevice) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BluetoothDevice{ %s/%s", formatAddress(bluetoothDevice.getAddress(), true), bluetoothDevice.getName()));
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tbondState=%d, type=0x%02X", Integer.valueOf(bluetoothDevice.getBondState()), Integer.valueOf(bluetoothDevice.getType())));
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            sb.append(String.format(locale, ", majorDeviceClass=0x%04X", Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
            sb.append(String.format(locale, ", deviceClass=0x%04X", Integer.valueOf(bluetoothClass.getDeviceClass())));
        }
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids != null && uuids.length > 0) {
            sb.append("\n\tsupportedFeaturesUuids");
            for (ParcelUuid parcelUuid : uuids) {
                sb.append(String.format("\n\t\t%s", parcelUuid.toString()));
            }
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static String dumpBluetoothGattService(BluetoothGatt bluetoothGatt) {
        return dumpBluetoothGattService(bluetoothGatt.getServices());
    }

    public static void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    public static String formatAddress(String str, boolean z) {
        if (!z) {
            return str;
        }
        if (str == null || str.length() != 17) {
            return "";
        }
        byte[] bArrConvertAddress = convertAddress(str);
        return bArrConvertAddress.length < 6 ? "" : String.format("%02X:%02X:**:**:**:%02X", Byte.valueOf(bArrConvertAddress[5]), Byte.valueOf(bArrConvertAddress[4]), Byte.valueOf(bArrConvertAddress[0]));
    }

    public static String formatAddressNegatitive(byte[] bArr) {
        return formatAddress(bArr, true, true);
    }

    public static String formatAddressPositive(byte[] bArr) {
        return formatAddress(bArr, false, false);
    }

    public static String getBluetoothAddress(Context context, BluetoothAdapter bluetoothAdapter) throws NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, SecurityException {
        if (bluetoothAdapter != null) {
            Class<?> cls = bluetoothAdapter.getClass();
            try {
                Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
                Field declaredField = cls.getDeclaredField("mService");
                declaredField.setAccessible(true);
                Method method = cls2.getMethod("getAddress", new Class[0]);
                method.setAccessible(true);
                return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
            } catch (Exception e) {
                ZLogger.w(e.toString());
            }
        } else if (context != null) {
            return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
        }
        return null;
    }

    public static List<BluetoothDevice> getBondedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        try {
            for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
                if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                    ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                    arrayList.add(bluetoothDevice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String getCharPermission(int i) {
        return BluetoothGattImpl.getCharPermission(i);
    }

    public static String getCharPropertie(int i) {
        return BluetoothGattImpl.getCharPropertie(i);
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices() throws NoSuchMethodException, SecurityException {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String getDescPermission(int i) {
        return BluetoothGattImpl.getDescPermission(i);
    }

    public static boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static boolean isBleSupported(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static String parseDeviceType(int i) {
        return BluetoothDeviceImpl.parseDeviceType(i);
    }

    public static String parseProfile(int i) {
        if (i == 16) {
            return "HEADSET_CLIENT";
        }
        if (i == 17) {
            return "PBAP_CLIENT";
        }
        switch (i) {
            case 1:
                return "HEADSET";
            case 2:
                return "A2DP";
            case 3:
                return "HEALTH";
            case 4:
                return "HID_HOST";
            case 5:
                return "PAN";
            case 6:
                return "PBAP";
            case 7:
                return "GATT";
            case 8:
                return "GATT_SERVER";
            case 9:
                return "MAP";
            case 10:
                return "SAP";
            case 11:
                return "A2DP_SINK";
            case 12:
                return "AVRCP_CONTROLLER";
            default:
                return "Unknown";
        }
    }

    public static String parseProfileState(int i) {
        if (i == 0) {
            return "0-BluetoothProfile.STATE_DISCONNECTED";
        }
        if (i == 1) {
            return "1-BluetoothProfile.STATE_CONNECTING";
        }
        if (i == 2) {
            return "2-BluetoothProfile.STATE_CONNECTED";
        }
        if (i == 3) {
            return "3-BluetoothProfile.STATE_DISCONNECTING";
        }
        return "UNKNOWN (" + i + ")";
    }

    public static List<String> parseProperty(int i) {
        return BluetoothGattImpl.parseProperty(i);
    }

    public static String parseProperty2(int i) {
        return BluetoothGattImpl.parseProperty2(i);
    }

    public static String dumpBluetoothGattService(List<BluetoothGattService> list) {
        StringBuilder sb = new StringBuilder();
        for (BluetoothGattService bluetoothGattService : list) {
            sb.append(String.format(Locale.US, "service: type=%d, %d/%s\n", Integer.valueOf(bluetoothGattService.getType()), Integer.valueOf(bluetoothGattService.getInstanceId()), bluetoothGattService.getUuid().toString()));
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                sb.append(String.format(Locale.US, "\tcharacteristic: %d/%s\n", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
            }
        }
        return sb.toString();
    }

    public static byte[] convertAddress(String str) {
        byte[] bArr = new byte[6];
        if (str != null) {
            bArr[0] = (byte) (Character.digit(str.charAt(16), 16) + (Character.digit(str.charAt(15), 16) * 16));
            bArr[1] = (byte) (Character.digit(str.charAt(13), 16) + (Character.digit(str.charAt(12), 16) * 16));
            bArr[2] = (byte) (Character.digit(str.charAt(10), 16) + (Character.digit(str.charAt(9), 16) * 16));
            bArr[3] = (byte) (Character.digit(str.charAt(7), 16) + (Character.digit(str.charAt(6), 16) * 16));
            bArr[4] = (byte) (Character.digit(str.charAt(4), 16) + (Character.digit(str.charAt(3), 16) * 16));
            bArr[5] = (byte) (Character.digit(str.charAt(1), 16) + (Character.digit(str.charAt(0), 16) * 16));
        } else {
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            bArr[3] = 0;
            bArr[4] = 0;
            bArr[5] = 0;
        }
        return bArr;
    }

    public static String formatAddress(byte[] bArr, boolean z) {
        return formatAddress(bArr, false, z);
    }

    public static String formatAddress(byte[] bArr, boolean z, boolean z2) {
        return (bArr == null || bArr.length < 6) ? "" : z ? z2 ? String.format("%02X:%02X:**:**:**:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[0])) : String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0])) : z2 ? String.format("%02X:%02X:**:**:**:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[5])) : String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices(int i) {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (defaultAdapter.getProfileConnectionState(i) == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
