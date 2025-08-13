package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.HeartRateDetail;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcHeartRateDetailDao.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H'J\u0018\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H'J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H'¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcHeartRateDetailDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/HeartRateDetail;", "queryBySync", "", "queryDaysSyncDate", "deviceAddress", "", "queryHeartByDate", "date", "queryLastSyncDate", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcHeartRateDetailDao extends BaseDao<HeartRateDetail> {
    List<HeartRateDetail> queryBySync();

    List<HeartRateDetail> queryDaysSyncDate(String deviceAddress);

    HeartRateDetail queryHeartByDate(String deviceAddress, String date);

    HeartRateDetail queryLastSyncDate(String deviceAddress);
}
