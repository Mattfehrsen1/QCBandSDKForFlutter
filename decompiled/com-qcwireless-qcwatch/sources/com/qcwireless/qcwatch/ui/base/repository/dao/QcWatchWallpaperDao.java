package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcWatchWallpaperDao.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H'J\u001a\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H'¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchWallpaperDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchWallpaperFace;", "queryAll", "", "version", "", "queryWatchFaceByNameAndHdVersion", "name", "hdVersion", "queryWatchWallpaperByName", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcWatchWallpaperDao extends BaseDao<WatchWallpaperFace> {
    List<WatchWallpaperFace> queryAll(String version);

    WatchWallpaperFace queryWatchFaceByNameAndHdVersion(String name, String hdVersion);

    WatchWallpaperFace queryWatchWallpaperByName(String name, String hdVersion);
}
