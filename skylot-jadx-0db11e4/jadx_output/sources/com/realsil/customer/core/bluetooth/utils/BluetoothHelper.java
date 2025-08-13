package com.realsil.customer.core.bluetooth.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.ParcelUuid;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.customer.core.logger.ZLogger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/utils/BluetoothHelper.class */
public final class BluetoothHelper {
    public static final int BD_ADDR_LEN = 6;
    public static final int BD_UUID_LEN = 16;

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

    public static List<String> parseProperty(int i) {
        return BluetoothGattImpl.parseProperty(i);
    }

    public static String parseProperty2(int i) {
        return BluetoothGattImpl.parseProperty2(i);
    }

    public static String getCharPermission(int i) {
        return BluetoothGattImpl.getCharPermission(i);
    }

    public static String getCharPropertie(int i) {
        return BluetoothGattImpl.getCharPropertie(i);
    }

    public static String getDescPermission(int i) {
        return BluetoothGattImpl.getDescPermission(i);
    }

    public static String convertMac(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return "";
        }
        String str = (bArr[5] & 255) <= 15 ? "0" + Integer.toHexString(bArr[5] & 255).toUpperCase() + ":" : Integer.toHexString(bArr[5] & 255).toUpperCase() + ":";
        String str2 = (bArr[4] & 255) <= 15 ? str + "0" + Integer.toHexString(bArr[4] & 255).toUpperCase() + ":" : str + Integer.toHexString(bArr[4] & 255).toUpperCase() + ":";
        String str3 = (bArr[3] & 255) <= 15 ? str2 + "0" + Integer.toHexString(bArr[3] & 255).toUpperCase() + ":" : str2 + Integer.toHexString(bArr[3] & 255).toUpperCase() + ":";
        String str4 = (bArr[2] & 255) <= 15 ? str3 + "0" + Integer.toHexString(bArr[2] & 255).toUpperCase() + ":" : str3 + Integer.toHexString(bArr[2] & 255).toUpperCase() + ":";
        String str5 = (bArr[1] & 255) <= 15 ? str4 + "0" + Integer.toHexString(bArr[1] & 255).toUpperCase() + ":" : str4 + Integer.toHexString(bArr[1] & 255).toUpperCase() + ":";
        if ((bArr[0] & 255) <= 15) {
            return str5 + "0" + Integer.toHexString(bArr[0] & 255).toUpperCase();
        }
        return str5 + Integer.toHexString(bArr[0] & 255).toUpperCase();
    }

    public static String formatAddressNegatitive(byte[] bArr) {
        return formatAddress(bArr, true, true);
    }

    public static String formatAddressPositive(byte[] bArr) {
        return formatAddress(bArr, false, false);
    }

    public static byte[] convertAddress(String str) {
        byte[] bArr = new byte[6];
        if (str == null || str.length() < 17) {
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            bArr[3] = 0;
            bArr[4] = 0;
            bArr[5] = 0;
        } else {
            bArr[0] = (byte) (Character.digit(str.charAt(16), 16) + (Character.digit(str.charAt(15), 16) * 16));
            bArr[1] = (byte) (Character.digit(str.charAt(13), 16) + (Character.digit(str.charAt(12), 16) * 16));
            bArr[2] = (byte) (Character.digit(str.charAt(10), 16) + (Character.digit(str.charAt(9), 16) * 16));
            bArr[3] = (byte) (Character.digit(str.charAt(7), 16) + (Character.digit(str.charAt(6), 16) * 16));
            bArr[4] = (byte) (Character.digit(str.charAt(4), 16) + (Character.digit(str.charAt(3), 16) * 16));
            bArr[5] = (byte) (Character.digit(str.charAt(1), 16) + (Character.digit(str.charAt(0), 16) * 16));
        }
        return bArr;
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

    public static String parseProfile(int i) {
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
            case 13:
            case 14:
            case 15:
            default:
                return "Unknown";
            case 16:
                return "HEADSET_CLIENT";
            case 17:
                return "PBAP_CLIENT";
        }
    }

    public static String parseProfileState(int i) {
        switch (i) {
            case 0:
                return "0-BluetoothProfile.STATE_DISCONNECTED";
            case 1:
                return "1-BluetoothProfile.STATE_CONNECTING";
            case 2:
                return "2-BluetoothProfile.STATE_CONNECTED";
            case 3:
                return "3-BluetoothProfile.STATE_DISCONNECTING";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean] */
    public static List<BluetoothDevice> getBondedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        ?? defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Iterator<BluetoothDevice> it = defaultAdapter.getBondedDevices().iterator();
            while (true) {
                defaultAdapter = it.hasNext();
                if (defaultAdapter == 0) {
                    break;
                }
                BluetoothDevice next = it.next();
                if (BluetoothDeviceImpl.isConnected(next)) {
                    ZLogger.v("connected: " + formatAddress(next.getAddress(), true));
                    arrayList.add(next);
                }
            }
        } catch (Exception unused) {
            defaultAdapter.printStackTrace();
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class, java.lang.Class<android.bluetooth.BluetoothAdapter>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    public static List<BluetoothDevice> getConnectedBluetoothDevices() throws NoSuchMethodException, SecurityException {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        ?? HasNext = BluetoothAdapter.class;
        try {
            Method declaredMethod = HasNext.getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2) {
                Iterator<BluetoothDevice> it = defaultAdapter.getBondedDevices().iterator();
                while (true) {
                    HasNext = it.hasNext();
                    if (HasNext == 0) {
                        break;
                    }
                    BluetoothDevice next = it.next();
                    if (BluetoothDeviceImpl.isConnected(next)) {
                        ZLogger.v("connected: " + formatAddress(next.getAddress(), true));
                        arrayList.add(next);
                    }
                }
            }
        } catch (Exception unused) {
            HasNext.printStackTrace();
        }
        return arrayList;
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

    public static String dumpService(BluetoothGattService bluetoothGattService, int i) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                str = str + "╎";
            }
            str = str + "--";
        }
        sb.append("\n" + str + "[service]");
        sb.append("\n" + str + "UUID: " + bluetoothGattService.getUuid());
        sb.append("\n" + str + "type: " + bluetoothGattService.getType());
        sb.append("\n" + str + "instanceId: " + bluetoothGattService.getInstanceId());
        sb.append(String.format("\n%s  [characteristics]", str));
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
            sb.append("\n" + str + "    UUID: " + bluetoothGattCharacteristic.getUuid());
            sb.append("\n" + str + "      prop= " + String.format("0x%02X (%s)", Integer.valueOf(bluetoothGattCharacteristic.getProperties()), BluetoothGattImpl.parseProperty2(bluetoothGattCharacteristic.getProperties())));
            sb.append("\n" + str + "      perms= " + String.format("0x%02X (%s)", Integer.valueOf(bluetoothGattCharacteristic.getPermissions()), BluetoothGattImpl.getCharPermission(bluetoothGattCharacteristic.getPermissions())));
            List<BluetoothGattDescriptor> descriptors = bluetoothGattCharacteristic.getDescriptors();
            if (descriptors != null && descriptors.size() > 0) {
                sb.append("\n" + str + "      [descriptors]");
                for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
                    sb.append("\n" + str + "        UUID: " + bluetoothGattDescriptor.getUuid());
                    sb.append("\n" + str + "          perms= " + String.format("0x%02X (%s)", Integer.valueOf(bluetoothGattDescriptor.getPermissions()), BluetoothGattImpl.getCharPermission(bluetoothGattDescriptor.getPermissions())));
                }
            }
        }
        if (bluetoothGattService.getIncludedServices() != null) {
            sb.append("\n" + str + "  [included services]");
            Iterator<BluetoothGattService> it = bluetoothGattService.getIncludedServices().iterator();
            while (it.hasNext()) {
                dumpService(it.next(), i + 1);
            }
        }
        return sb.toString();
    }

    public static String dumpBluetoothGattService(List<BluetoothGattService> list) {
        StringBuilder sb = new StringBuilder();
        for (BluetoothGattService bluetoothGattService : list) {
            sb.append(String.format(Locale.US, "\nservice: type=%d, %02X/%s", Integer.valueOf(bluetoothGattService.getType()), Integer.valueOf(bluetoothGattService.getInstanceId()), bluetoothGattService.getUuid().toString()));
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                sb.append(String.format(Locale.US, "\n\tcharacteristic: %02X/%s", Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getUuid().toString()));
                sb.append(String.format("\n\t\tprop= 0x%02X (%s)", Integer.valueOf(bluetoothGattCharacteristic.getProperties()), BluetoothGattImpl.parseProperty2(bluetoothGattCharacteristic.getProperties())));
                sb.append(String.format("\n\t\tperms= 0x%02X (%s)", Integer.valueOf(bluetoothGattCharacteristic.getPermissions()), BluetoothGattImpl.getCharPermission(bluetoothGattCharacteristic.getPermissions())));
                List<BluetoothGattDescriptor> descriptors = bluetoothGattCharacteristic.getDescriptors();
                if (descriptors != null && descriptors.size() > 0) {
                    for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
                        sb.append(String.format(Locale.US, "\n\t\t\tdescriptor: UUID: %s", bluetoothGattDescriptor.getUuid().toString()));
                        sb.append(String.format("\n\t\t\t\tperms= 0x%02X (%s)", Integer.valueOf(bluetoothGattDescriptor.getPermissions()), BluetoothGattImpl.getCharPermission(bluetoothGattDescriptor.getPermissions())));
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String formatAddress(byte[] bArr, boolean z) {
        return formatAddress(bArr, false, z);
    }

    public static String formatAddress(byte[] bArr, boolean z, boolean z2) {
        if (bArr == null || bArr.length < 6) {
            return "";
        }
        return z ? z2 ? String.format("%02X:%02X:**:**:**:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[0])) : String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0])) : z2 ? String.format("%02X:%02X:**:**:**:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[5])) : String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean] */
    public static List<BluetoothDevice> getConnectedBluetoothDevices(int i) {
        ArrayList arrayList = new ArrayList();
        ?? defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (defaultAdapter.getProfileConnectionState(i) == 2) {
                Iterator<BluetoothDevice> it = defaultAdapter.getBondedDevices().iterator();
                while (true) {
                    defaultAdapter = it.hasNext();
                    if (defaultAdapter == 0) {
                        break;
                    }
                    BluetoothDevice next = it.next();
                    if (BluetoothDeviceImpl.isConnected(next)) {
                        ZLogger.v("connected: " + formatAddress(next.getAddress(), true));
                        arrayList.add(next);
                    }
                }
            }
        } catch (Exception unused) {
            defaultAdapter.printStackTrace();
        }
        return arrayList;
    }
}
