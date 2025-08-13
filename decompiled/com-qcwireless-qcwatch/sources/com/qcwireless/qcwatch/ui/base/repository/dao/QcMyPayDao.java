package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.PayWatchFace;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcMyPayDao.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J \u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH'J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\bH'J\"\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH'¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMyPayDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/PayWatchFace;", "queryWatchFaceByHdNameAndUid", "", "uid", "", "hdVersion", "", "queryWatchFaceByOrderId", "orderId", "queryWatchFacePayStatus", "watchFaceName", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcMyPayDao extends BaseDao<PayWatchFace> {
    List<PayWatchFace> queryWatchFaceByHdNameAndUid(long uid, String hdVersion);

    PayWatchFace queryWatchFaceByOrderId(String orderId);

    PayWatchFace queryWatchFacePayStatus(long uid, String hdVersion, String watchFaceName);
}
