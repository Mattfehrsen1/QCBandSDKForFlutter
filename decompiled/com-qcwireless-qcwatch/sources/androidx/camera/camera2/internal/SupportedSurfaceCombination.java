package androidx.camera.camera2.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import android.view.WindowManager;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.workaround.ExcludedSupportedSizesContainer;
import androidx.camera.camera2.internal.compat.workaround.TargetAspectRatio;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.core.util.Preconditions;
import com.king.zxing.util.CodeUtils;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class SupportedSurfaceCombination {
    private static final int ALIGN16 = 16;
    private static final String TAG = "SupportedSurfaceCombination";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final String mCameraId;
    private final CameraCharacteristicsCompat mCharacteristics;
    private final ExcludedSupportedSizesContainer mExcludedSupportedSizesContainer;
    private final int mHardwareLevel;
    private final boolean mIsSensorLandscapeResolution;
    private SurfaceSizeDefinition mSurfaceSizeDefinition;
    private static final Size MAX_PREVIEW_SIZE = new Size(1920, 1080);
    private static final Size DEFAULT_SIZE = new Size(CodeUtils.DEFAULT_REQ_HEIGHT, CodeUtils.DEFAULT_REQ_WIDTH);
    private static final Size ZERO_SIZE = new Size(0, 0);
    private static final Size QUALITY_2160P_SIZE = new Size(3840, 2160);
    private static final Size QUALITY_1080P_SIZE = new Size(1920, 1080);
    private static final Size QUALITY_720P_SIZE = new Size(BluetoothClassImpl.Device.PERIPHERAL_NON_KEYBOARD_NON_POINTING, 720);
    private static final Size QUALITY_480P_SIZE = new Size(720, CodeUtils.DEFAULT_REQ_WIDTH);
    private static final Rational ASPECT_RATIO_4_3 = new Rational(4, 3);
    private static final Rational ASPECT_RATIO_3_4 = new Rational(3, 4);
    private static final Rational ASPECT_RATIO_16_9 = new Rational(16, 9);
    private static final Rational ASPECT_RATIO_9_16 = new Rational(9, 16);
    private final List<SurfaceCombination> mSurfaceCombinations = new ArrayList();
    private final Map<Integer, Size> mMaxSizeCache = new HashMap();
    private final Map<Integer, List<Size>> mExcludedSizeListCache = new HashMap();
    private boolean mIsRawSupported = false;
    private boolean mIsBurstCaptureSupported = false;
    private Map<Integer, Size[]> mOutputSizesCache = new HashMap();

    private void checkCustomization() {
    }

    SupportedSurfaceCombination(Context context, String cameraId, CameraManagerCompat cameraManagerCompat, CamcorderProfileHelper camcorderProfileHelper) throws CameraUnavailableException {
        String str = (String) Preconditions.checkNotNull(cameraId);
        this.mCameraId = str;
        this.mCamcorderProfileHelper = (CamcorderProfileHelper) Preconditions.checkNotNull(camcorderProfileHelper);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.mExcludedSupportedSizesContainer = new ExcludedSupportedSizesContainer(cameraId);
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str);
            this.mCharacteristics = cameraCharacteristicsCompat;
            Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.mHardwareLevel = num != null ? num.intValue() : 2;
            this.mIsSensorLandscapeResolution = isSensorLandscapeResolution();
            generateSupportedCombinationList();
            generateSurfaceSizeDefinition(windowManager);
            checkCustomization();
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    String getCameraId() {
        return this.mCameraId;
    }

    boolean isRawSupported() {
        return this.mIsRawSupported;
    }

    boolean isBurstCaptureSupported() {
        return this.mIsBurstCaptureSupported;
    }

    boolean checkSupported(List<SurfaceConfig> surfaceConfigList) {
        Iterator<SurfaceCombination> it = this.mSurfaceCombinations.iterator();
        boolean zIsSupported = false;
        while (it.hasNext() && !(zIsSupported = it.next().isSupported(surfaceConfigList))) {
        }
        return zIsSupported;
    }

    SurfaceConfig transformSurfaceConfig(int imageFormat, Size size) {
        SurfaceConfig.ConfigType configType;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.NOT_SUPPORT;
        if (imageFormat == 35) {
            configType = SurfaceConfig.ConfigType.YUV;
        } else if (imageFormat == 256) {
            configType = SurfaceConfig.ConfigType.JPEG;
        } else if (imageFormat == 32) {
            configType = SurfaceConfig.ConfigType.RAW;
        } else {
            configType = SurfaceConfig.ConfigType.PRIV;
        }
        Size sizeFetchMaxSize = fetchMaxSize(imageFormat);
        if (size.getWidth() * size.getHeight() <= this.mSurfaceSizeDefinition.getAnalysisSize().getWidth() * this.mSurfaceSizeDefinition.getAnalysisSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.ANALYSIS;
        } else if (size.getWidth() * size.getHeight() <= this.mSurfaceSizeDefinition.getPreviewSize().getWidth() * this.mSurfaceSizeDefinition.getPreviewSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.PREVIEW;
        } else if (size.getWidth() * size.getHeight() <= this.mSurfaceSizeDefinition.getRecordSize().getWidth() * this.mSurfaceSizeDefinition.getRecordSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.RECORD;
        } else if (size.getWidth() * size.getHeight() <= sizeFetchMaxSize.getWidth() * sizeFetchMaxSize.getHeight()) {
            configSize = SurfaceConfig.ConfigSize.MAXIMUM;
        }
        return SurfaceConfig.create(configType, configSize);
    }

    Map<UseCaseConfig<?>, Size> getSuggestedResolutions(List<SurfaceConfig> existingSurfaces, List<UseCaseConfig<?>> newUseCaseConfigs) {
        HashMap map = new HashMap();
        List<Integer> useCasesPriorityOrder = getUseCasesPriorityOrder(newUseCaseConfigs);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = useCasesPriorityOrder.iterator();
        while (it.hasNext()) {
            arrayList.add(getSupportedOutputSizes(newUseCaseConfigs.get(it.next().intValue())));
        }
        Iterator<List<Size>> it2 = getAllPossibleSizeArrangements(arrayList).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            List<Size> next = it2.next();
            ArrayList arrayList2 = new ArrayList(existingSurfaces);
            for (int i = 0; i < next.size(); i++) {
                arrayList2.add(transformSurfaceConfig(newUseCaseConfigs.get(useCasesPriorityOrder.get(i).intValue()).getInputFormat(), next.get(i)));
            }
            if (checkSupported(arrayList2)) {
                for (UseCaseConfig<?> useCaseConfig : newUseCaseConfigs) {
                    map.put(useCaseConfig, next.get(useCasesPriorityOrder.indexOf(Integer.valueOf(newUseCaseConfigs.indexOf(useCaseConfig)))));
                }
            }
        }
        return map;
    }

    private Rational getTargetAspectRatio(ImageOutputConfig imageOutputConfig) {
        Rational rational;
        int i = new TargetAspectRatio().get(imageOutputConfig, this.mCameraId, this.mCharacteristics);
        if (i == 0) {
            rational = this.mIsSensorLandscapeResolution ? ASPECT_RATIO_4_3 : ASPECT_RATIO_3_4;
        } else if (i == 1) {
            rational = this.mIsSensorLandscapeResolution ? ASPECT_RATIO_16_9 : ASPECT_RATIO_9_16;
        } else {
            if (i == 2) {
                Size sizeFetchMaxSize = fetchMaxSize(256);
                return new Rational(sizeFetchMaxSize.getWidth(), sizeFetchMaxSize.getHeight());
            }
            if (i != 3) {
                return null;
            }
            Size targetSize = getTargetSize(imageOutputConfig);
            if (!imageOutputConfig.hasTargetAspectRatio()) {
                if (targetSize != null) {
                    return new Rational(targetSize.getWidth(), targetSize.getHeight());
                }
                return null;
            }
            int targetAspectRatio = imageOutputConfig.getTargetAspectRatio();
            if (targetAspectRatio == 0) {
                rational = this.mIsSensorLandscapeResolution ? ASPECT_RATIO_4_3 : ASPECT_RATIO_3_4;
            } else if (targetAspectRatio == 1) {
                rational = this.mIsSensorLandscapeResolution ? ASPECT_RATIO_16_9 : ASPECT_RATIO_9_16;
            } else {
                Logger.e(TAG, "Undefined target aspect ratio: " + targetAspectRatio);
                return null;
            }
        }
        return rational;
    }

    SurfaceSizeDefinition getSurfaceSizeDefinition() {
        return this.mSurfaceSizeDefinition;
    }

    private Size fetchMaxSize(int imageFormat) {
        Size size = this.mMaxSizeCache.get(Integer.valueOf(imageFormat));
        if (size != null) {
            return size;
        }
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(imageFormat);
        this.mMaxSizeCache.put(Integer.valueOf(imageFormat), maxOutputSizeByFormat);
        return maxOutputSizeByFormat;
    }

    private List<Integer> getUseCasesPriorityOrder(List<UseCaseConfig<?>> newUseCaseConfigs) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<UseCaseConfig<?>> it = newUseCaseConfigs.iterator();
        while (it.hasNext()) {
            int surfaceOccupancyPriority = it.next().getSurfaceOccupancyPriority(0);
            if (!arrayList2.contains(Integer.valueOf(surfaceOccupancyPriority))) {
                arrayList2.add(Integer.valueOf(surfaceOccupancyPriority));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Integer) it2.next()).intValue();
            for (UseCaseConfig<?> useCaseConfig : newUseCaseConfigs) {
                if (iIntValue == useCaseConfig.getSurfaceOccupancyPriority(0)) {
                    arrayList.add(Integer.valueOf(newUseCaseConfigs.indexOf(useCaseConfig)));
                }
            }
        }
        return arrayList;
    }

    List<Size> getSupportedOutputSizes(UseCaseConfig<?> config) {
        int inputFormat = config.getInputFormat();
        ImageOutputConfig imageOutputConfig = (ImageOutputConfig) config;
        Size[] customizedSupportSizesFromConfig = getCustomizedSupportSizesFromConfig(inputFormat, imageOutputConfig);
        if (customizedSupportSizesFromConfig == null) {
            customizedSupportSizesFromConfig = getAllOutputSizesByFormat(inputFormat);
        }
        ArrayList arrayList = new ArrayList();
        Size maxResolution = imageOutputConfig.getMaxResolution(null);
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(inputFormat);
        if (maxResolution == null || getArea(maxOutputSizeByFormat) < getArea(maxResolution)) {
            maxResolution = maxOutputSizeByFormat;
        }
        Arrays.sort(customizedSupportSizesFromConfig, new CompareSizesByArea(true));
        Size targetSize = getTargetSize(imageOutputConfig);
        Size size = DEFAULT_SIZE;
        int area = getArea(size);
        if (getArea(maxResolution) < area) {
            size = ZERO_SIZE;
        } else if (targetSize != null && getArea(targetSize) < area) {
            size = targetSize;
        }
        for (Size size2 : customizedSupportSizesFromConfig) {
            if (getArea(size2) <= getArea(maxResolution) && getArea(size2) >= getArea(size) && !arrayList.contains(size2)) {
                arrayList.add(size2);
            }
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("Can not get supported output size under supported maximum for the format: " + inputFormat);
        }
        Rational targetAspectRatio = getTargetAspectRatio(imageOutputConfig);
        if (targetSize == null) {
            targetSize = imageOutputConfig.getDefaultResolution(null);
        }
        ArrayList arrayList2 = new ArrayList();
        new HashMap();
        if (targetAspectRatio == null) {
            arrayList2.addAll(arrayList);
            if (targetSize != null) {
                removeSupportedSizesByTargetSize(arrayList2, targetSize);
            }
        } else {
            Map<Rational, List<Size>> mapGroupSizesByAspectRatio = groupSizesByAspectRatio(arrayList);
            if (targetSize != null) {
                Iterator<Rational> it = mapGroupSizesByAspectRatio.keySet().iterator();
                while (it.hasNext()) {
                    removeSupportedSizesByTargetSize(mapGroupSizesByAspectRatio.get(it.next()), targetSize);
                }
            }
            ArrayList arrayList3 = new ArrayList(mapGroupSizesByAspectRatio.keySet());
            Collections.sort(arrayList3, new CompareAspectRatiosByDistanceToTargetRatio(targetAspectRatio));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                for (Size size3 : mapGroupSizesByAspectRatio.get((Rational) it2.next())) {
                    if (!arrayList2.contains(size3)) {
                        arrayList2.add(size3);
                    }
                }
            }
        }
        return arrayList2;
    }

    private Size getTargetSize(ImageOutputConfig imageOutputConfig) {
        return flipSizeByRotation(imageOutputConfig.getTargetResolution(null), imageOutputConfig.getTargetRotation(0));
    }

    private Size flipSizeByRotation(Size size, int targetRotation) {
        return (size == null || !isRotationNeeded(targetRotation)) ? size : new Size(size.getHeight(), size.getWidth());
    }

    private boolean isRotationNeeded(int targetRotation) {
        Integer num = (Integer) this.mCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(num, "Camera HAL in bad state, unable to retrieve the SENSOR_ORIENTATION");
        int iSurfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(targetRotation);
        Integer num2 = (Integer) this.mCharacteristics.get(CameraCharacteristics.LENS_FACING);
        Preconditions.checkNotNull(num2, "Camera HAL in bad state, unable to retrieve the LENS_FACING");
        int relativeImageRotation = CameraOrientationUtil.getRelativeImageRotation(iSurfaceRotationToDegrees, num.intValue(), 1 == num2.intValue());
        return relativeImageRotation == 90 || relativeImageRotation == 270;
    }

    private boolean isSensorLandscapeResolution() {
        Size size = (Size) this.mCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        return size == null || size.getWidth() >= size.getHeight();
    }

    static boolean hasMatchingAspectRatio(Size resolution, Rational aspectRatio) {
        if (aspectRatio == null) {
            return false;
        }
        if (aspectRatio.equals(new Rational(resolution.getWidth(), resolution.getHeight()))) {
            return true;
        }
        if (getArea(resolution) >= getArea(DEFAULT_SIZE)) {
            return isPossibleMod16FromAspectRatio(resolution, aspectRatio);
        }
        return false;
    }

    private static boolean isPossibleMod16FromAspectRatio(Size resolution, Rational aspectRatio) {
        int width = resolution.getWidth();
        int height = resolution.getHeight();
        Rational rational = new Rational(aspectRatio.getDenominator(), aspectRatio.getNumerator());
        int i = width % 16;
        if (i == 0 && height % 16 == 0) {
            return ratioIntersectsMod16Segment(Math.max(0, height + (-16)), width, aspectRatio) || ratioIntersectsMod16Segment(Math.max(0, width + (-16)), height, rational);
        }
        if (i == 0) {
            return ratioIntersectsMod16Segment(height, width, aspectRatio);
        }
        if (height % 16 == 0) {
            return ratioIntersectsMod16Segment(width, height, rational);
        }
        return false;
    }

    private static int getArea(Size size) {
        return size.getWidth() * size.getHeight();
    }

    private static boolean ratioIntersectsMod16Segment(int height, int mod16Width, Rational aspectRatio) {
        Preconditions.checkArgument(mod16Width % 16 == 0);
        double numerator = (height * aspectRatio.getNumerator()) / aspectRatio.getDenominator();
        return numerator > ((double) Math.max(0, mod16Width + (-16))) && numerator < ((double) (mod16Width + 16));
    }

    private Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> sizes) {
        HashMap map = new HashMap();
        map.put(ASPECT_RATIO_4_3, new ArrayList());
        map.put(ASPECT_RATIO_16_9, new ArrayList());
        for (Size size : sizes) {
            Rational rational = null;
            for (Rational rational2 : map.keySet()) {
                if (hasMatchingAspectRatio(size, rational2)) {
                    List list = (List) map.get(rational2);
                    if (!list.contains(size)) {
                        list.add(size);
                    }
                    rational = rational2;
                }
            }
            if (rational == null) {
                map.put(new Rational(size.getWidth(), size.getHeight()), new ArrayList(Collections.singleton(size)));
            }
        }
        return map;
    }

    private void removeSupportedSizesByTargetSize(List<Size> supportedSizesList, Size targetSize) {
        if (supportedSizesList == null || supportedSizesList.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (int i2 = 0; i2 < supportedSizesList.size(); i2++) {
            Size size = supportedSizesList.get(i2);
            if (size.getWidth() < targetSize.getWidth() || size.getHeight() < targetSize.getHeight()) {
                break;
            }
            if (i >= 0) {
                arrayList.add(supportedSizesList.get(i));
            }
            i = i2;
        }
        supportedSizesList.removeAll(arrayList);
    }

    private List<List<Size>> getAllPossibleSizeArrangements(List<List<Size>> supportedOutputSizesList) {
        Iterator<List<Size>> it = supportedOutputSizesList.iterator();
        int size = 1;
        while (it.hasNext()) {
            size *= it.next().size();
        }
        if (size == 0) {
            throw new IllegalArgumentException("Failed to find supported resolutions.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(new ArrayList());
        }
        int size2 = size / supportedOutputSizesList.get(0).size();
        int i2 = size;
        for (int i3 = 0; i3 < supportedOutputSizesList.size(); i3++) {
            List<Size> list = supportedOutputSizesList.get(i3);
            for (int i4 = 0; i4 < size; i4++) {
                ((List) arrayList.get(i4)).add(list.get((i4 % i2) / size2));
            }
            if (i3 < supportedOutputSizesList.size() - 1) {
                i2 = size2;
                size2 /= supportedOutputSizesList.get(i3 + 1).size();
            }
        }
        return arrayList;
    }

    private Size[] excludeProblematicSizes(Size[] outputSizes, int imageFormat) {
        List<Size> listFetchExcludedSizes = fetchExcludedSizes(imageFormat);
        ArrayList arrayList = new ArrayList(Arrays.asList(outputSizes));
        arrayList.removeAll(listFetchExcludedSizes);
        return (Size[]) arrayList.toArray(new Size[0]);
    }

    private Size[] getCustomizedSupportSizesFromConfig(int imageFormat, ImageOutputConfig config) {
        Size[] sizeArr = null;
        List<Pair<Integer, Size[]>> supportedResolutions = config.getSupportedResolutions(null);
        if (supportedResolutions != null) {
            Iterator<Pair<Integer, Size[]>> it = supportedResolutions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair<Integer, Size[]> next = it.next();
                if (((Integer) next.first).intValue() == imageFormat) {
                    sizeArr = (Size[]) next.second;
                    break;
                }
            }
        }
        if (sizeArr == null) {
            return sizeArr;
        }
        Size[] sizeArrExcludeProblematicSizes = excludeProblematicSizes(sizeArr, imageFormat);
        Arrays.sort(sizeArrExcludeProblematicSizes, new CompareSizesByArea(true));
        return sizeArrExcludeProblematicSizes;
    }

    private Size[] getAllOutputSizesByFormat(int imageFormat) {
        Size[] sizeArr = this.mOutputSizesCache.get(Integer.valueOf(imageFormat));
        if (sizeArr != null) {
            return sizeArr;
        }
        Size[] sizeArrDoGetAllOutputSizesByFormat = doGetAllOutputSizesByFormat(imageFormat);
        this.mOutputSizesCache.put(Integer.valueOf(imageFormat), sizeArrDoGetAllOutputSizesByFormat);
        return sizeArrDoGetAllOutputSizesByFormat;
    }

    private Size[] doGetAllOutputSizesByFormat(int imageFormat) {
        Size[] outputSizes;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
        }
        if (Build.VERSION.SDK_INT < 23 && imageFormat == 34) {
            outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
        } else {
            outputSizes = streamConfigurationMap.getOutputSizes(imageFormat);
        }
        if (outputSizes == null) {
            throw new IllegalArgumentException("Can not get supported output size for the format: " + imageFormat);
        }
        Size[] sizeArrExcludeProblematicSizes = excludeProblematicSizes(outputSizes, imageFormat);
        Arrays.sort(sizeArrExcludeProblematicSizes, new CompareSizesByArea(true));
        return sizeArrExcludeProblematicSizes;
    }

    Size getMaxOutputSizeByFormat(int imageFormat) {
        return (Size) Collections.max(Arrays.asList(getAllOutputSizesByFormat(imageFormat)), new CompareSizesByArea());
    }

    List<SurfaceCombination> getLegacySupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        arrayList.add(surfaceCombination6);
        SurfaceCombination surfaceCombination7 = new SurfaceCombination();
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        arrayList.add(surfaceCombination7);
        SurfaceCombination surfaceCombination8 = new SurfaceCombination();
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination8);
        return arrayList;
    }

    List<SurfaceCombination> getLimitedSupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.RECORD));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.RECORD));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.RECORD));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.RECORD));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.RECORD));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.RECORD));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.RECORD));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination6);
        return arrayList;
    }

    List<SurfaceCombination> getFullSupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.ANALYSIS));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.ANALYSIS));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination6);
        return arrayList;
    }

    List<SurfaceCombination> getRAWSupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination6);
        SurfaceCombination surfaceCombination7 = new SurfaceCombination();
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination7);
        SurfaceCombination surfaceCombination8 = new SurfaceCombination();
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination8);
        return arrayList;
    }

    List<SurfaceCombination> getBurstSupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination3);
        return arrayList;
    }

    List<SurfaceCombination> getLevel3SupportedCombinationList() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.ANALYSIS));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM));
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.ANALYSIS));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, SurfaceConfig.ConfigSize.MAXIMUM));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.RAW, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination2);
        return arrayList;
    }

    private void generateSupportedCombinationList() {
        this.mSurfaceCombinations.addAll(getLegacySupportedCombinationList());
        int i = this.mHardwareLevel;
        if (i == 0 || i == 1 || i == 3) {
            this.mSurfaceCombinations.addAll(getLimitedSupportedCombinationList());
        }
        int i2 = this.mHardwareLevel;
        if (i2 == 1 || i2 == 3) {
            this.mSurfaceCombinations.addAll(getFullSupportedCombinationList());
        }
        int[] iArr = (int[]) this.mCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (iArr != null) {
            for (int i3 : iArr) {
                if (i3 == 3) {
                    this.mIsRawSupported = true;
                } else if (i3 == 6) {
                    this.mIsBurstCaptureSupported = true;
                }
            }
        }
        if (this.mIsRawSupported) {
            this.mSurfaceCombinations.addAll(getRAWSupportedCombinationList());
        }
        if (this.mIsBurstCaptureSupported && this.mHardwareLevel == 0) {
            this.mSurfaceCombinations.addAll(getBurstSupportedCombinationList());
        }
        if (this.mHardwareLevel == 3) {
            this.mSurfaceCombinations.addAll(getLevel3SupportedCombinationList());
        }
    }

    private void generateSurfaceSizeDefinition(WindowManager windowManager) {
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(new Size(CodeUtils.DEFAULT_REQ_HEIGHT, CodeUtils.DEFAULT_REQ_WIDTH), getPreviewSize(windowManager), getRecordSize());
    }

    public static Size getPreviewSize(WindowManager windowManager) {
        Size size;
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        if (point.x > point.y) {
            size = new Size(point.x, point.y);
        } else {
            size = new Size(point.y, point.x);
        }
        return (Size) Collections.min(Arrays.asList(new Size(size.getWidth(), size.getHeight()), MAX_PREVIEW_SIZE), new CompareSizesByArea());
    }

    private Size getRecordSize() throws NumberFormatException {
        Size size = QUALITY_480P_SIZE;
        try {
            int i = Integer.parseInt(this.mCameraId);
            if (this.mCamcorderProfileHelper.hasProfile(i, 8)) {
                size = QUALITY_2160P_SIZE;
            } else if (this.mCamcorderProfileHelper.hasProfile(i, 6)) {
                size = QUALITY_1080P_SIZE;
            } else if (this.mCamcorderProfileHelper.hasProfile(i, 5)) {
                size = QUALITY_720P_SIZE;
            } else {
                this.mCamcorderProfileHelper.hasProfile(i, 4);
            }
            return size;
        } catch (NumberFormatException unused) {
            return getRecordSizeFromStreamConfigurationMap();
        }
    }

    private Size getRecordSizeFromStreamConfigurationMap() {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
        }
        Size[] outputSizes = streamConfigurationMap.getOutputSizes(MediaRecorder.class);
        if (outputSizes == null) {
            return QUALITY_480P_SIZE;
        }
        Arrays.sort(outputSizes, new CompareSizesByArea(true));
        for (Size size : outputSizes) {
            int width = size.getWidth();
            Size size2 = QUALITY_1080P_SIZE;
            if (width <= size2.getWidth() && size.getHeight() <= size2.getHeight()) {
                return size;
            }
        }
        return QUALITY_480P_SIZE;
    }

    private List<Size> fetchExcludedSizes(int imageFormat) {
        List<Size> list = this.mExcludedSizeListCache.get(Integer.valueOf(imageFormat));
        if (list != null) {
            return list;
        }
        List<Size> list2 = this.mExcludedSupportedSizesContainer.get(imageFormat);
        this.mExcludedSizeListCache.put(Integer.valueOf(imageFormat), list2);
        return list2;
    }

    static final class CompareSizesByArea implements Comparator<Size> {
        private boolean mReverse;

        CompareSizesByArea() {
            this.mReverse = false;
        }

        CompareSizesByArea(boolean reverse) {
            this.mReverse = false;
            this.mReverse = reverse;
        }

        @Override // java.util.Comparator
        public int compare(Size lhs, Size rhs) {
            int iSignum = Long.signum((lhs.getWidth() * lhs.getHeight()) - (rhs.getWidth() * rhs.getHeight()));
            return this.mReverse ? iSignum * (-1) : iSignum;
        }
    }

    static final class CompareAspectRatiosByDistanceToTargetRatio implements Comparator<Rational> {
        private Rational mTargetRatio;

        CompareAspectRatiosByDistanceToTargetRatio(Rational targetRatio) {
            this.mTargetRatio = targetRatio;
        }

        @Override // java.util.Comparator
        public int compare(Rational lhs, Rational rhs) {
            if (lhs.equals(rhs)) {
                return 0;
            }
            return (int) Math.signum(Float.valueOf(Math.abs(lhs.floatValue() - this.mTargetRatio.floatValue())).floatValue() - Float.valueOf(Math.abs(rhs.floatValue() - this.mTargetRatio.floatValue())).floatValue());
        }
    }
}
