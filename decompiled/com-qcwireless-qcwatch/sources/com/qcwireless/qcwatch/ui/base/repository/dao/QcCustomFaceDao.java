package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcCustomFaceDao.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H'¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcCustomFaceDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "queryWatchFaceCustom", "hdVersion", "", "queryWatchFaceList", "", "address", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcCustomFaceDao extends BaseDao<CustomFaceEntity> {
    CustomFaceEntity queryWatchFaceCustom(String hdVersion);

    List<CustomFaceEntity> queryWatchFaceList(String address);
}
